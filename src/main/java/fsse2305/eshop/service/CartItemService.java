package fsse2305.eshop.service;

import fsse2305.eshop.data.PutCartItemResponseData;
import fsse2305.eshop.user.FirebaseUserData;

public interface CartItemService {

    PutCartItemResponseData putCartItem(Integer pid, Integer quantity, FirebaseUserData firebaseUserData);
}
