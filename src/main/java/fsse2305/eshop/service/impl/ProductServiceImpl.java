package fsse2305.eshop.service.impl;

import fsse2305.eshop.data.data.AllProductResponseData;
import fsse2305.eshop.data.data.ProductByIdResponseData;
import fsse2305.eshop.data.data.UpdateProductRequestData;
import fsse2305.eshop.data.data.UpdateProductResponseData;
import fsse2305.eshop.data.entity.ProductEntity;
import fsse2305.eshop.exception.product.PRODUCT_ID_NOT_FOUND_EXCEPTION;
import fsse2305.eshop.exception.product.STOCK_QTY_NOT_ENOUGH_EXCEPTION;
import fsse2305.eshop.repository.ProductRepository;
import fsse2305.eshop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    public final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository)  {
        this.productRepository = productRepository;
    }
    public List<AllProductResponseData> getAllProduct() {
        List<AllProductResponseData> allProductResponseDataArrayList = new ArrayList<>();
        for(ProductEntity productEntity: productRepository.findAll()) {
            allProductResponseDataArrayList.add(new AllProductResponseData(productEntity));
        }
        return allProductResponseDataArrayList;
    }

    public ProductByIdResponseData getProductById(String pid) throws Exception {
        try{
            Optional<ProductEntity> productEntity = productRepository.findById(pid);
            return new ProductByIdResponseData(
                    productEntity.orElseThrow(() ->
                            new PRODUCT_ID_NOT_FOUND_EXCEPTION(pid)));
        } catch (PRODUCT_ID_NOT_FOUND_EXCEPTION e)    {
            logger.warn(e.toString());
            throw e;
        }
    }

    public UpdateProductResponseData updateProductById(String pid, UpdateProductRequestData updateProductRequestData) throws Exception {
        try{
            Optional<ProductEntity> productEntity = productRepository.findById(pid);
            if(productEntity.isEmpty())   {
                throw  new PRODUCT_ID_NOT_FOUND_EXCEPTION(pid);
            }
            productEntity.get().setName(updateProductRequestData.getName());
            productEntity.get().setDescription(updateProductRequestData.getDescription());
            productEntity.get().setImageUrl(updateProductRequestData.getImageUrl());
            productEntity.get().setPrice(updateProductRequestData.getPrice());
            productEntity.get().setStockQty(updateProductRequestData.getStockQty());
            return new UpdateProductResponseData(productEntity.get());
        } catch (PRODUCT_ID_NOT_FOUND_EXCEPTION e)    {
            logger.warn(e.toString());
            throw e;
        }
    }

    public ProductEntity getProductEntityByPid(String pid)  throws Exception    {
        try{
            logger.info("Get Product: " + pid);
            Optional<ProductEntity> productEntity = productRepository.findById(pid);
            return productEntity.orElseThrow(() ->
                            new PRODUCT_ID_NOT_FOUND_EXCEPTION(pid));
        } catch (PRODUCT_ID_NOT_FOUND_EXCEPTION e)    {
            logger.warn(e.toString());
            throw e;
        }
    }

    public Integer deductProductQtyById(String pid, Integer quantity) throws Exception {
        try{
            logger.info("Get Product: " + pid);
            Optional<ProductEntity> productEntity = productRepository.findById(pid);
            productEntity.orElseThrow(() -> new PRODUCT_ID_NOT_FOUND_EXCEPTION(pid));
            if(productEntity.get().getStockQty()<quantity)    {
                throw new STOCK_QTY_NOT_ENOUGH_EXCEPTION(pid, productEntity.get().getStockQty(), quantity);
            }
            logger.info("Deduct qty PID: " + pid + ", updated qty:" + (productEntity.get().getStockQty() - quantity));
            productEntity.get().setStockQty(productEntity.get().getStockQty() - quantity);
            productRepository.save(productEntity.get());
            return 1;
        } catch (Exception e)    {
            logger.warn(e.toString());
            throw e;
        }
    }
}
