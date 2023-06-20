package fsse2305.eshop.service;

import fsse2305.eshop.data.AllProductResponseData;
import fsse2305.eshop.data.ProductByIdResponseData;
import fsse2305.eshop.data.UpdateProductRequestData;
import fsse2305.eshop.data.UpdateProductResponseData;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
      List<AllProductResponseData> getAllProduct();
      ProductByIdResponseData getProductById(Integer pid) throws Exception;
      UpdateProductResponseData updateProductById(Integer pid, UpdateProductRequestData updateProductRequestData) throws Exception;
}
