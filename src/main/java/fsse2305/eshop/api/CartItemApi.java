package fsse2305.eshop.api;

import fsse2305.eshop.data.GetCartItemResponseData;
import fsse2305.eshop.data.dto.DeleteCartItemResponseDto;
import fsse2305.eshop.data.dto.GetCartItemResponseDto;
import fsse2305.eshop.data.dto.PutCartItemResponseDto;
import fsse2305.eshop.data.dto.UpdateCartItemQtyResponseDto;
import fsse2305.eshop.service.CartItemService;
import fsse2305.eshop.user.FirebaseUserData;
import fsse2305.eshop.utility.JwtUtil;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartItemApi {
    private final CartItemService cartItemService;

    public CartItemApi(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PutMapping("/{pid}/{quantity}")
    public PutCartItemResponseDto putCartItem(@PathVariable Integer pid, @PathVariable Integer quantity, JwtAuthenticationToken jwtToken) throws Exception {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        return new PutCartItemResponseDto(cartItemService.putCartItem(pid, quantity, firebaseUserData));
    }

    @GetMapping("")
    public List<GetCartItemResponseDto> getCartItem(JwtAuthenticationToken jwtToken) throws Exception {
        List<GetCartItemResponseDto> getCartItemResponseDtoList = new ArrayList<>();
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        for(GetCartItemResponseData getCartItemResponseData: cartItemService.getCartItem(firebaseUserData)) {
            getCartItemResponseDtoList.add(new GetCartItemResponseDto(getCartItemResponseData));
        }
        return getCartItemResponseDtoList;
    }

    @PatchMapping("/{pid}/{quantity}")
    public UpdateCartItemQtyResponseDto updateCartItemQty(@PathVariable Integer pid, @PathVariable Integer quantity, JwtAuthenticationToken jwtToken)  throws Exception    {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        return new UpdateCartItemQtyResponseDto(cartItemService.updateCartItemQty(pid, quantity, firebaseUserData));
    }

    @DeleteMapping("/{pid}")
    public DeleteCartItemResponseDto deleteCartItem(@PathVariable Integer pid, JwtAuthenticationToken jwtToken) throws Exception    {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        return new DeleteCartItemResponseDto(cartItemService.deleteCartItem(pid, firebaseUserData));
    }
}
