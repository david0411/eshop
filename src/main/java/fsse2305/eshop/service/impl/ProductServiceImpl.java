package fsse2305.eshop.service.impl;

import fsse2305.eshop.exception.product.STOCK_QTY_DEDUCT_FAILED_EXCEPTION;
import fsse2305.eshop.exception.product.STOCK_QTY_NOT_ENOUGH_EXCEPTION;
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
            logger.info("Get Product: " + pid);
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
            logger.info("Get Product: " + pid);
            ProductEntity productEntity = productRepository.getProductById(pid);
            if (productEntity == null)  {
                throw new PRODUCT_ID_NOT_FOUND_EXCEPTION(pid);
            }
            if(productEntity.getStockQty()<quantity)    {
                throw new STOCK_QTY_NOT_ENOUGH_EXCEPTION(pid, productEntity.getStockQty(), quantity);
            }
            logger.info("Deduct qty PID: " + pid + ", updated qty:" + (productEntity.getStockQty() - quantity));
            Integer deductProductResult = productRepository.deductProductQtyById(pid, productEntity.getStockQty() - quantity);
            if(deductProductResult == 1)    {
                return deductProductResult;
            }
            throw new STOCK_QTY_DEDUCT_FAILED_EXCEPTION(pid);
        } catch (Exception e)    {
            logger.warn(e.toString());
            throw e;
        }
    }
}
