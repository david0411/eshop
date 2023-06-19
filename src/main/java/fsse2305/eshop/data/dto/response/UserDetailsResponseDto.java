package fsse2305.eshop.data.dto.response;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class UserDetailsResponseDto {
      private String firebaseUid;
      private String email;
      private String issuer;
      public UserDetailsResponseDto(JwtAuthenticationToken jwtToken) {
            this.firebaseUid = (String)
                      jwtToken.getTokenAttributes().get("user_id");
            this.email = (String) jwtToken.getTokenAttributes().get("email");
            this.issuer = (String) jwtToken.getTokenAttributes().get("iss");
      }

      public String getFirebaseUid() {
            return firebaseUid;
      }

      public void setFirebaseUid(String firebaseUid) {
            this.firebaseUid = firebaseUid;
      }

      public String getEmail() {
            return email;
      }

      public void setEmail(String email) {
            this.email = email;
      }

      public String getIssuer() {
            return issuer;
      }

      public void setIssuer(String issuer) {
            this.issuer = issuer;
      }
}