package fsse2305.eshop.api;

import fsse2305.eshop.data.data.AllProductResponseData;
import fsse2305.eshop.data.data.UpdateProductRequestData;
import fsse2305.eshop.data.dto.AllProductResponseDto;
import fsse2305.eshop.data.dto.ProductByIdResponseDto;
import fsse2305.eshop.data.dto.UpdateProductRequestDto;
import fsse2305.eshop.data.dto.UpdateProductResponseDto;
import fsse2305.eshop.service.ProductService;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductApi {

    private final ProductService productService;

    public ProductApi(ProductService productService)    {
        this.productService = productService;
    }
    @GetMapping("/public/product")
    public List<AllProductResponseDto> getAllProduct()   {
        List<AllProductResponseDto> allProductResponseDtoList = new ArrayList<>();
        List<AllProductResponseData> allProductResponseDataList = productService.getAllProduct();
        for(AllProductResponseData allProductResponseData: allProductResponseDataList)      {
            allProductResponseDtoList.add(new AllProductResponseDto(allProductResponseData));
        }
        return allProductResponseDtoList;
    }

    @GetMapping("/public/product/{id}")
    public ProductByIdResponseDto getProductById(@PathVariable Integer id) throws Exception {
        return new ProductByIdResponseDto(productService.getProductById(id));
    }

    @PutMapping("admin/product/{id}")
    public UpdateProductResponseDto updateProductById(@PathVariable Integer id, @RequestBody UpdateProductRequestDto updateProductRequestDto, JwtAuthenticationToken
            jwtToken) throws Exception    {
        return new UpdateProductResponseDto(productService.updateProductById(id, new UpdateProductRequestData(updateProductRequestDto)));
    }
}
