package fsse2305.eshop;

import fsse2305.eshop.config.EnvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class EshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/cart/**")
                        .allowedOrigins(EnvConfig.devConfig, EnvConfig.prodConfig)
                        .allowCredentials(true)
                        .allowedHeaders("Authorization")
                        .allowedMethods("*")
                        .maxAge(3600);
                registry.addMapping("/cart")
                        .allowedOrigins(EnvConfig.devConfig, EnvConfig.prodConfig)
                        .allowCredentials(true)
                        .allowedHeaders("Authorization")
                        .allowedMethods("*")
                        .maxAge(3600);
                registry.addMapping("/public/**")
                        .allowedOrigins(EnvConfig.devConfig, EnvConfig.prodConfig)
                        .allowCredentials(true)
                        .allowedHeaders("Authorization")
                        .allowedMethods("*")
                        .maxAge(3600);
                registry.addMapping("/transaction/**")
                        .allowedOrigins(EnvConfig.devConfig, EnvConfig.prodConfig)
                        .allowCredentials(true)
                        .allowedHeaders("Authorization")
                        .allowedMethods("*")
                        .maxAge(3600);
            }
        };
    }

}
