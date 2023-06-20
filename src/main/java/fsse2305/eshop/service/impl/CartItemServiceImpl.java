package fsse2305.eshop.service.impl;

import fsse2305.eshop.data.DeleteCartItemResponseData;
import fsse2305.eshop.data.GetCartItemResponseData;
import fsse2305.eshop.data.PutCartItemResponseData;
import fsse2305.eshop.data.UpdateCartItemQtyResponseData;
import fsse2305.eshop.data.entity.CartItemEntity;
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

    @Override
    public PutCartItemResponseData putCartItem(Integer pid, Integer quantity, FirebaseUserData firebaseUserData) throws Exception {
        try {
            Integer uid = userService.getEntityByFirebaseUserData(firebaseUserData).getUid();
            productService.getProductEntityById(pid);
            if(cartItemRepository.getCartItemByUidAAndPid(uid, pid) != null)    {
                //item already added exception
                return null;
            }
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
        List<GetCartItemResponseData> getCartItemResponseDataList = new ArrayList<>();
        for(CartItemEntity cartItemEntity: cartItemRepository.getCartItemByUid(userService.getEntityByFirebaseUserData(firebaseUserData).getUid()))   {
            getCartItemResponseDataList.add(new GetCartItemResponseData(productService.getProductEntityById(cartItemEntity.getPid()), cartItemEntity));
        }
        return getCartItemResponseDataList;
    }

    public UpdateCartItemQtyResponseData updateCartItemQty(Integer pid, Integer quantity, FirebaseUserData firebaseUserData) throws Exception {
        Integer uid = userService.getEntityByFirebaseUserData(firebaseUserData).getUid();
        Integer result = cartItemRepository.updateCartItemByPid(uid, pid, quantity);
        if(result > 0)  {
            CartItemEntity cartItemEntity = cartItemRepository.getCartItemByUidAAndPid(uid, pid);
            return new UpdateCartItemQtyResponseData(productService.getProductEntityById(cartItemEntity.getPid()), cartItemEntity);
        }
        //cart item not find exception
        return null;
    }

    public DeleteCartItemResponseData deleteCartItem(Integer pid, FirebaseUserData firebaseUserData) throws Exception   {
        try {
            Integer uid = userService.getEntityByFirebaseUserData(firebaseUserData).getUid();
            productService.getProductEntityById(pid);
            if(cartItemRepository.getCartItemByUidAAndPid(uid, pid) == null)    {
                //item not in cart exception
                return null;
            }
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
}
