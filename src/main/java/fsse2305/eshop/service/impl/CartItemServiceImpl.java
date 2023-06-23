package fsse2305.eshop.service.impl;

import fsse2305.eshop.data.data.DeleteCartItemResponseData;
import fsse2305.eshop.data.data.GetCartItemResponseData;
import fsse2305.eshop.data.data.PutCartItemResponseData;
import fsse2305.eshop.data.data.UpdateCartItemQtyResponseData;
import fsse2305.eshop.data.entity.CartItemEntity;
import fsse2305.eshop.data.entity.ProductEntity;
import fsse2305.eshop.exception.cart.CART_ITEM_EXCEED_STOCK_QTY_EXCEPTION;
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
    final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final UserService userService;
    private final ProductService productService;
    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl(UserService userService, ProductService productService, CartItemRepository cartItemRepository) {
        this.userService = userService;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
    }

    public PutCartItemResponseData putCartItem(String pid, Integer quantity, FirebaseUserData firebaseUserData) throws Exception {
        try {
            if(pid == null || quantity == null || firebaseUserData == null) {
                throw new DATA_INSUFFICIENT_EXCEPTION();
            }
            String uid = getUid(firebaseUserData);
            logger.info("Check item in Cart");
            ProductEntity productEntity = productService.getProductEntityByPid(pid);
            CartItemEntity cartItemEntity = cartItemRepository.findByUidAndPid(uid, pid);
            if(cartItemEntity != null)    {
                logger.info("Cart Item already exist, update quantity instead");
                logger.info("Update cart item. uid:" + uid + " pid:" + pid + " Qty:" + quantity);
                cartItemEntity.setQuantity(cartItemEntity.getQuantity() + quantity);
                cartItemRepository.save(cartItemEntity);
                return new PutCartItemResponseData("SUCCESS");
            }
            if(productEntity.getStockQty() < quantity)    {
                throw new CART_ITEM_EXCEED_STOCK_QTY_EXCEPTION(pid, productEntity.getStockQty(), quantity);
            }
            logger.info("Add item to cart. uid:" + uid + " pid:" + pid + " Qty:" + quantity);
            CartItemEntity newCartItemEntity = new CartItemEntity(pid, uid, quantity);
            cartItemRepository.save(newCartItemEntity);
            return new PutCartItemResponseData("SUCCESS");
        }   catch (Exception e) {
            logger.warn(e.toString());
            throw e;
        }
    }

    public List<GetCartItemResponseData> getCartItem(FirebaseUserData firebaseUserData) throws Exception {
        try {
            if(firebaseUserData == null) {
                throw new DATA_INSUFFICIENT_EXCEPTION();
            }
            String uid = getUid(firebaseUserData);
            List<GetCartItemResponseData> getCartItemResponseDataList = new ArrayList<>();
            logger.info("Get cart item. uid:" + uid);
            for(CartItemEntity cartItemEntity: cartItemRepository.findByUid(uid))   {
                getCartItemResponseDataList.add(new GetCartItemResponseData(productService.getProductEntityByPid(cartItemEntity.getPid()), cartItemEntity));
            }
            return getCartItemResponseDataList;
        }   catch (Exception e) {
            logger.warn(e.toString());
            throw e;
        }
    }

    public UpdateCartItemQtyResponseData updateCartItemQty(String pid, Integer quantity, FirebaseUserData firebaseUserData) throws Exception {
        try {
            if(pid == null || quantity == null || firebaseUserData == null) {
                throw new DATA_INSUFFICIENT_EXCEPTION();
            }
            String uid = getUid(firebaseUserData);
            ProductEntity productEntity = productService.getProductEntityByPid(pid);
            CartItemEntity cartItemEntity = cartItemRepository.findByUidAndPid(uid, pid);
            if(cartItemEntity == null)    {
                throw new CART_ITEM_NOT_FOUND_EXCEPTION(pid);
            }
            if(productEntity.getStockQty() < quantity)    {
                throw new CART_ITEM_EXCEED_STOCK_QTY_EXCEPTION(pid, productEntity.getStockQty(), quantity);
            }
            logger.info("Update cart item. uid:" + uid + " pid:" + pid + " Qty:" + quantity);
            cartItemEntity.setQuantity(quantity);
            cartItemRepository.save(cartItemEntity);
            return new UpdateCartItemQtyResponseData(productEntity, quantity);
        }   catch (Exception e) {
            logger.warn(e.toString());
            throw e;
        }
    }

    public DeleteCartItemResponseData deleteCartItem(String pid, FirebaseUserData firebaseUserData) throws Exception   {
        try {
            if(pid == null || firebaseUserData == null) {
                throw new DATA_INSUFFICIENT_EXCEPTION();
            }
            String uid = getUid(firebaseUserData);
            productService.getProductEntityByPid(pid);
            if(cartItemRepository.findByUidAndPid(uid, pid) == null)    {
                throw new CART_ITEM_NOT_FOUND_EXCEPTION(pid);
            }
            logger.info("Delete cart item. uid:" + uid + " pid:" + pid);
            Integer result = cartItemRepository.deleteByUidAndPid(uid, pid);
            if (result==1) {
                return new DeleteCartItemResponseData("SUCCESS");
            }
            return new DeleteCartItemResponseData("FAIL");
        } catch (Exception e)   {
            logger.warn(e.toString());
            throw e;
        }
    }

    public List<CartItemEntity> getCartItemByUid(String uid) throws Exception  {
        try {
            if(uid == null) {
                throw new DATA_INSUFFICIENT_EXCEPTION();
            }
            logger.info("Get cart item. uid:" + uid);
            return cartItemRepository.findByUid(uid);
        } catch (Exception e)   {
            logger.warn(e.toString());
            throw e;
        }
    }

    public Integer deleteCartItemByUid(String uid) throws Exception  {
        try {
            if(uid == null) {
                throw new DATA_INSUFFICIENT_EXCEPTION();
            }
            logger.info("Delete cart item. uid:" + uid);
            return cartItemRepository.deleteByUid(uid);
        } catch (Exception e)   {
            logger.warn(e.toString());
            throw e;
        }
    }

    private String getUid(FirebaseUserData firebaseUserData)   {
        logger.info("Get User");
        return userService.getEntityByFirebaseUserData(firebaseUserData).getUid();
    }
}
