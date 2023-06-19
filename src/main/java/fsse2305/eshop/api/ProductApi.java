package fsse2305.eshop.api;

import fsse2305.eshop.data.AllProductResponseData;
import fsse2305.eshop.data.dto.AllProductResponseDto;
import fsse2305.eshop.data.dto.ProductByIdResponseDto;
import fsse2305.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductApi {

    public ProductService productService;
    @Autowired
    public ProductApi(ProductService productService)    {
        this.productService = productService;
    }
    @GetMapping("/public/product")
    public AllProductResponseDto getAllProduct()   {
        AllProductResponseData allProductResponseData = productService.getAllProduct();
    }

    @GetMapping("/public/product/{id}")
    public ProductByIdResponseDto getProductById(@PathVariable int id)  {

    }

}
