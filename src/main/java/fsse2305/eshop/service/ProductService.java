package fsse2305.eshop.service;

import fsse2305.eshop.data.data.*;
import fsse2305.eshop.data.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
      List<AllProductResponseData> getAllProduct();
      ProductByIdResponseData getProductByPid(Integer pid) throws Exception;
      UpdateProductResponseData updateProductById(Integer pid, UpdateProductRequestData updateProductRequestData) throws Exception;

      ProductEntity getProductEntityByPid(Integer pid)  throws Exception;

      Integer deductProductQtyByPid(Integer pid, Integer quantity) throws Exception;

      List<ProductByCategoryResponseData> getProductByCategory(Integer catId) throws Exception;
}
