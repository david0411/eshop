package fsse2305.eshop.api;

import fsse2305.eshop.data.dto.PrepareTransResponseDto;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransApi {

    @PostMapping("/prepare")
    public PrepareTransResponseDto prepareTrans(JwtAuthenticationToken jwtToken)    {

        return null;
    }

//      @GetMapping("/{tid}")
//
//      @PatchMapping("/{tid}/pay")
//
//      @PatchMapping("/{tid}/finish")

}
