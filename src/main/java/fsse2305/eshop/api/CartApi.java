package fsse2305.eshop.api;

import org.springframework.web.bind.annotation.*;

@RestController
public class CartApi {

    @PutMapping("/cart/{pid}/{quantity}")

    @GetMapping("/cart")

    @PatchMapping("/cart/{pid}/{quantity}")

    @DeleteMapping("/cart/{pid}")
}
