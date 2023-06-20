package fsse2305.eshop.api;

import fsse2305.eshop.data.dto.PutCartItemResponseDto;
import fsse2305.eshop.service.CartItemService;
import fsse2305.eshop.user.FirebaseUserData;
import fsse2305.eshop.utility.JwtUtil;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartItemApi {
    private final CartItemService cartItemService;

    public CartItemApi(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PutMapping("/{pid}/{quantity}")
    public PutCartItemResponseDto putCartItem(@PathVariable Integer pid, @PathVariable Integer quantity, JwtAuthenticationToken jwtToken) {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        return new PutCartItemResponseDto(cartItemService.putCartItem(pid, quantity, firebaseUserData));
    }

    @GetMapping("/cart")
    public void getCartItem(JwtAuthenticationToken jwtToken)   {

    }

    /*@PatchMapping("/cart/{pid}/{quantity}")

    @DeleteMapping("/cart/{pid}")*/
}
