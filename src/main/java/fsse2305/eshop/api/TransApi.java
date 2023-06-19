package fsse2305.eshop.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransApi {

    @PostMapping("/transaction/prepare")

    @GetMapping("/transaction/{tid}")

    @PatchMapping("/transaction/{tid}/pay")

    @PatchMapping("/transaction/{tid}/finish")


}
