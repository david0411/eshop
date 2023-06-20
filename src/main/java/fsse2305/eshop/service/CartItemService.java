package fsse2305.eshop.service;

import fsse2305.eshop.data.DeleteCartItemResponseData;
import fsse2305.eshop.data.GetCartItemResponseData;
import fsse2305.eshop.data.PutCartItemResponseData;
import fsse2305.eshop.data.UpdateCartItemQtyResponseData;
import fsse2305.eshop.user.FirebaseUserData;

import java.util.List;

public interface CartItemService {

    PutCartItemResponseData putCartItem(Integer pid, Integer quantity, FirebaseUserData firebaseUserData) throws Exception;

    List<GetCartItemResponseData> getCartItem(FirebaseUserData firebaseUserData) throws Exception;

    UpdateCartItemQtyResponseData updateCartItemQty(Integer pid, Integer quantity, FirebaseUserData firebaseUserData) throws Exception;

    DeleteCartItemResponseData deleteCartItem(Integer pid, FirebaseUserData firebaseUserData) throws Exception;
}
