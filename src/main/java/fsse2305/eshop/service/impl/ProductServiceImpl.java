package fsse2305.eshop.service.impl;

import fsse2305.eshop.repository.ProductRepository;
import fsse2305.eshop.data.data.AllProductResponseData;
import fsse2305.eshop.data.data.ProductByIdResponseData;
import fsse2305.eshop.data.data.UpdateProductRequestData;
import fsse2305.eshop.data.data.UpdateProductResponseData;
import fsse2305.eshop.data.entity.ProductEntity;
import fsse2305.eshop.exception.product.PRODUCT_ID_NOT_FOUND_EXCEPTION;
import fsse2305.eshop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    public ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository)  {
        this.productRepository = productRepository;
    }
    public List<AllProductResponseData> getAllProduct() {
        List<AllProductResponseData> allProductResponseDataArrayList = new ArrayList<>();
        for(ProductEntity productEntity: productRepository.getAllProduct()) {
            allProductResponseDataArrayList.add(new AllProductResponseData(productEntity));
        }
        return allProductResponseDataArrayList;
    }

    public ProductByIdResponseData getProductById(Integer pid) throws Exception {
        try{
            ProductEntity productEntity = productRepository.getProductById(pid);
            if(productEntity == null)   {
                throw  new PRODUCT_ID_NOT_FOUND_EXCEPTION(pid);
            }
            return new ProductByIdResponseData(productEntity);
        } catch (PRODUCT_ID_NOT_FOUND_EXCEPTION e)    {
            logger.warn(e.toString());
            throw e;
        }
    }

    public UpdateProductResponseData updateProductById(Integer pid, UpdateProductRequestData updateProductRequestData) throws Exception {
        try{
            ProductEntity productEntity = productRepository.getProductById(pid);
            if (productEntity == null)  {
                throw  new PRODUCT_ID_NOT_FOUND_EXCEPTION(pid);
            }
            return new UpdateProductResponseData(productRepository.updateProductById(
                      pid,
                      updateProductRequestData.getName(),
                      updateProductRequestData.getDescription(),
                      updateProductRequestData.getImageUrl(),
                      updateProductRequestData.getPrice(),
                      updateProductRequestData.getStockQty()
            ));
        } catch (PRODUCT_ID_NOT_FOUND_EXCEPTION e)    {
            logger.warn(e.toString());
            throw e;
        }
    }

    public ProductEntity getProductEntityByPid(Integer pid)  throws Exception    {
        try{
            ProductEntity productEntity = productRepository.getProductById(pid);
            if(productEntity == null)   {
                throw  new PRODUCT_ID_NOT_FOUND_EXCEPTION(pid);
            }
            return productEntity;
        } catch (PRODUCT_ID_NOT_FOUND_EXCEPTION e)    {
            logger.warn(e.toString());
            throw e;
        }
    }

    public Integer deductProductQtyById(Integer pid, Integer quantity) throws Exception {
        try{
            ProductEntity productEntity = productRepository.getProductById(pid);
            if (productEntity == null)  {
                throw  new PRODUCT_ID_NOT_FOUND_EXCEPTION(pid);
            }
            if(productEntity.getStockQty()<quantity)    {
                //stock not enough
                return null;
            }
            Integer deductedStockQty = productEntity.getStockQty() - quantity;
            Integer deductProductResult = productRepository.deductProductQtyById(pid, deductedStockQty);
            if(deductProductResult == 1)    {
                return deductProductResult;
            }
            //throw error
            return null;
        } catch (PRODUCT_ID_NOT_FOUND_EXCEPTION e)    {
            logger.warn(e.toString());
            throw e;
        }
    }
}
