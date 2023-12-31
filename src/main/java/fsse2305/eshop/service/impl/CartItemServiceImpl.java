package fsse2305.eshop.service.impl;

import fsse2305.eshop.data.data.DeleteCartItemResponseData;
import fsse2305.eshop.data.data.GetCartItemResponseData;
import fsse2305.eshop.data.data.PutCartItemResponseData;
import fsse2305.eshop.data.data.UpdateCartItemQtyResponseData;
import fsse2305.eshop.data.entity.CartItemEntity;
import fsse2305.eshop.data.entity.ProductEntity;
import fsse2305.eshop.exception.cart.CART_ITEM_EXCEED_STOCK_QTY_EXCEPTION;
import fsse2305.eshop.exception.cart.CART_ITEM_EXCEPTION;
import fsse2305.eshop.exception.cart.CART_ITEM_NOT_FOUND_EXCEPTION;
import fsse2305.eshop.exception.general.DATA_INSUFFICIENT_EXCEPTION;
import fsse2305.eshop.repository.CartItemRepository;
import fsse2305.eshop.service.CartItemService;
import fsse2305.eshop.service.ProductService;
import fsse2305.eshop.service.UserService;
import fsse2305.eshop.user.FirebaseUserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final UserService userService;
    private final ProductService productService;
    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl(UserService userService, ProductService productService, CartItemRepository cartItemRepository) {
        this.userService = userService;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
    }

    public PutCartItemResponseData putCartItem(Integer pid, Integer quantity, FirebaseUserData firebaseUserData) throws Exception {
        try {
            logger.info("Start put Cart item.");
            if(pid == null || quantity == null || firebaseUserData == null) {
                throw new DATA_INSUFFICIENT_EXCEPTION();
            }
            Integer uid = getUid(firebaseUserData);
            logger.info("Check item in Cart");
            ProductEntity productEntity = productService.getProductEntityByPid(pid);
            CartItemEntity cartItemEntity = cartItemRepository.getCartItemByUidAAndPid(uid, pid);
            if(cartItemEntity != null)    {
                logger.info("Cart Item already exist, update quantity instead");
                logger.info("Update cart item. uid:" + uid + " pid:" + pid + " Qty:" + quantity);
                cartItemRepository.updateCartItemByPid(uid, pid, cartItemEntity.getQuantity() + quantity);
                return new PutCartItemResponseData("SUCCESS");
            }
            if(productEntity.getStockQty() < quantity)    {
                throw new CART_ITEM_EXCEED_STOCK_QTY_EXCEPTION(pid, productEntity.getStockQty(), quantity);
            }
            logger.info("Add item to cart. uid:" + uid + " pid:" + pid + " Qty:" + quantity);
            Integer result = cartItemRepository.addItem2Cart(uid, pid, quantity);
            if (result==1) {
                return new PutCartItemResponseData("SUCCESS");
            }
            return new PutCartItemResponseData("FAIL");
        }   catch (Exception e) {
            logger.warn(e.toString());
            throw e;
        }
    }

    public List<GetCartItemResponseData> getCartItem(FirebaseUserData firebaseUserData) throws Exception {
        try {
            logger.info("Start get Cart item.");
            if(firebaseUserData == null) {
                throw new DATA_INSUFFICIENT_EXCEPTION();
            }
            Integer uid = getUid(firebaseUserData);
            List<GetCartItemResponseData> getCartItemResponseDataList = new ArrayList<>();
            logger.info("Get cart item. uid:" + uid);
            for(CartItemEntity cartItemEntity: cartItemRepository.getCartItemByUid(uid))   {
                getCartItemResponseDataList.add(new GetCartItemResponseData(productService.getProductEntityByPid(cartItemEntity.getPid()), cartItemEntity));
            }
            return getCartItemResponseDataList;
        }   catch (Exception e) {
            logger.warn(e.toString());
            throw e;
        }
    }

    public UpdateCartItemQtyResponseData updateCartItemQty(Integer pid, Integer quantity, FirebaseUserData firebaseUserData) throws Exception {
        try {
            logger.info("Start update Cart item Qty.");
            if(pid == null || quantity == null || firebaseUserData == null) {
                throw new DATA_INSUFFICIENT_EXCEPTION();
            }
            Integer uid = getUid(firebaseUserData);
            ProductEntity productEntity = productService.getProductEntityByPid(pid);
            CartItemEntity cartItemEntity = cartItemRepository.getCartItemByUidAAndPid(uid, pid);
            if(cartItemEntity == null)    {
                throw new CART_ITEM_NOT_FOUND_EXCEPTION(pid);
            }
            if(productEntity.getStockQty() < quantity)    {
                throw new CART_ITEM_EXCEED_STOCK_QTY_EXCEPTION(pid, productEntity.getStockQty(), quantity);
            }
            logger.info("Update cart item. uid:" + uid + " pid:" + pid + " Qty:" + quantity);
            Integer result = cartItemRepository.updateCartItemByPid(uid, pid, quantity);
            if(result > 0)  {
                return new UpdateCartItemQtyResponseData(productEntity, quantity);
            }
            throw new CART_ITEM_EXCEPTION();
        }   catch (Exception e) {
            logger.warn(e.toString());
            throw e;
        }
    }

    public DeleteCartItemResponseData deleteCartItem(Integer pid, FirebaseUserData firebaseUserData) throws Exception   {
        try {
            logger.info("Start delete Cart item.");
            if(pid == null || firebaseUserData == null) {
                throw new DATA_INSUFFICIENT_EXCEPTION();
            }
            Integer uid = getUid(firebaseUserData);
            productService.getProductEntityByPid(pid);
            if(cartItemRepository.getCartItemByUidAAndPid(uid, pid) == null)    {
                throw new CART_ITEM_NOT_FOUND_EXCEPTION(pid);
            }
            logger.info("Delete cart item. uid:" + uid + " pid:" + pid);
            Integer result = cartItemRepository.deleteCartItemByPid(uid, pid);
            if (result==1) {
                return new DeleteCartItemResponseData("SUCCESS");
            }
            return new DeleteCartItemResponseData("FAIL");
        } catch (Exception e)   {
            logger.warn(e.toString());
            throw e;
        }
    }

    public List<CartItemEntity> getCartItemByUid(Integer uid) throws Exception  {
        try {
            logger.info("Start get Cart item(Internal).");
            if(uid == null) {
                throw new DATA_INSUFFICIENT_EXCEPTION();
            }
            logger.info("Get cart item. uid:" + uid);
            return cartItemRepository.getCartItemByUid(uid);
        } catch (Exception e)   {
            logger.warn(e.toString());
            throw e;
        }
    }

    public Integer deleteCartItemByUid(Integer uid) throws Exception  {
        try {
            logger.info("Start delete Cart item(Internal).");
            if(uid == null) {
                throw new DATA_INSUFFICIENT_EXCEPTION();
            }
            logger.info("Delete cart item. uid:" + uid);
            return cartItemRepository.deleteCartItemByUid(uid);
        } catch (Exception e)   {
            logger.warn(e.toString());
            throw e;
        }
    }

    private Integer getUid(FirebaseUserData firebaseUserData)   {
        logger.info("Get User");
        return userService.getEntityByFirebaseUserData(firebaseUserData).getUid();
    }
}
