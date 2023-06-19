package fsse2305.eshop.api;

import fsse2305.eshop.data.dto.response.UserDetailsResponseDto;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserApi {
      @GetMapping("/me/details")
      public UserDetailsResponseDto getMyUserDetails(JwtAuthenticationToken
                                                               jwtToken) {
            return new UserDetailsResponseDto(jwtToken);
      }
}