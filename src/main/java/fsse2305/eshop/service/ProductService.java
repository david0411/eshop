package fsse2305.eshop.service;

import fsse2305.eshop.data.data.AllProductResponseData;
import fsse2305.eshop.data.data.ProductByIdResponseData;
import fsse2305.eshop.data.data.UpdateProductRequestData;
import fsse2305.eshop.data.data.UpdateProductResponseData;
import fsse2305.eshop.data.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
      List<AllProductResponseData> getAllProduct();
      ProductByIdResponseData getProductById(Integer pid) throws Exception;
      UpdateProductResponseData updateProductById(Integer pid, UpdateProductRequestData updateProductRequestData) throws Exception;

      ProductEntity getProductEntityByPid(Integer pid)  throws Exception;

      Integer deductProductQtyById(Integer pid, Integer quantity) throws Exception;
}
