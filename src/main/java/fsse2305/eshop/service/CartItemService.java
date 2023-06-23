package fsse2305.eshop.service;

import fsse2305.eshop.data.data.DeleteCartItemResponseData;
import fsse2305.eshop.data.data.GetCartItemResponseData;
import fsse2305.eshop.data.data.PutCartItemResponseData;
import fsse2305.eshop.data.data.UpdateCartItemQtyResponseData;
import fsse2305.eshop.data.entity.CartItemEntity;
import fsse2305.eshop.user.FirebaseUserData;

import java.util.List;

public interface CartItemService {

    PutCartItemResponseData putCartItem(String pid, Integer quantity, FirebaseUserData firebaseUserData) throws Exception;

    List<GetCartItemResponseData> getCartItem(FirebaseUserData firebaseUserData) throws Exception;

    UpdateCartItemQtyResponseData updateCartItemQty(String pid, Integer quantity, FirebaseUserData firebaseUserData) throws Exception;

    DeleteCartItemResponseData deleteCartItem(String pid, FirebaseUserData firebaseUserData) throws Exception;

    List<CartItemEntity> getCartItemByUid(String uid) throws Exception;

    Integer deleteCartItemByUid(String uid) throws Exception;
}
