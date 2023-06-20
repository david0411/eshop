package fsse2305.eshop.service.impl;

import fsse2305.eshop.data.PutCartItemResponseData;
import fsse2305.eshop.repository.CartItemRepository;
import fsse2305.eshop.service.CartItemService;
import fsse2305.eshop.service.UserService;
import fsse2305.eshop.user.FirebaseUserData;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final UserService userService;
    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl(UserService userService, CartItemRepository cartItemRepository) {
        this.userService = userService;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public PutCartItemResponseData putCartItem(Integer pid, Integer quantity, FirebaseUserData firebaseUserData)   {
        Integer success = cartItemRepository.addItem2Cart(userService.getEntityByFirebaseUserData(firebaseUserData).getUid(), pid, quantity);
        if (success==1) {
            return new PutCartItemResponseData("SUCCESS");
        }
        return new PutCartItemResponseData("FAIL");
    }
}
