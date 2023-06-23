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
      ProductByIdResponseData getProductById(String pid) throws Exception;
      UpdateProductResponseData updateProductById(String pid, UpdateProductRequestData updateProductRequestData) throws Exception;

      ProductEntity getProductEntityByPid(String pid)  throws Exception;

      Integer deductProductQtyById(String pid, Integer quantity) throws Exception;
}
