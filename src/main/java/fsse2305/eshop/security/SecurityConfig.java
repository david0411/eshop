package fsse2305.eshop.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${spring.security.oauth2.resource-server.jwt.issuer-uri}")
    private String issuer;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("public/**").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable);

        http
                .oauth2ResourceServer(
                        oauth2ResourceServer -> oauth2ResourceServer.jwt(
                                jwt -> jwt.decoder(
                                        JwtDecoders.fromIssuerLocation(issuer))
                        )
                );
        http.cors(Customizer.withDefaults());
        return http.build();
    }
}

