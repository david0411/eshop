package fsse2305.eshop.utility;

import fsse2305.eshop.user.FirebaseUserData;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class JwtUtil {
    public static FirebaseUserData getFirebaseUserData(JwtAuthenticationToken jwtToken)    {
        return new FirebaseUserData(jwtToken);
    }
}
