package fsse2305.eshop.service.impl;

import fsse2305.eshop.data.data.*;
import fsse2305.eshop.data.entity.ProductEntity;
import fsse2305.eshop.exception.product.PRODUCT_ID_NOT_FOUND_EXCEPTION;
import fsse2305.eshop.exception.product.STOCK_QTY_DEDUCT_FAILED_EXCEPTION;
import fsse2305.eshop.exception.product.STOCK_QTY_NOT_ENOUGH_EXCEPTION;
import fsse2305.eshop.repository.CategoryRepository;
import fsse2305.eshop.repository.ProductCategoryRepository;
import fsse2305.eshop.repository.ProductRepository;
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
    public ProductCategoryRepository productCategoryRepository;
    public CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<AllProductResponseData> getAllProduct() {
        logger.info("Start get all product");
        List<AllProductResponseData> allProductResponseDataArrayList = new ArrayList<>();
        for(ProductEntity productEntity: productRepository.getAllProduct()) {
            allProductResponseDataArrayList.add(new AllProductResponseData(productEntity));
        }
        return allProductResponseDataArrayList;
    }

    public ProductByIdResponseData getProductByPid(Integer pid) throws Exception {
        try{
            ProductEntity productEntity = productExist(pid);
            return new ProductByIdResponseData(productEntity);
        } catch (PRODUCT_ID_NOT_FOUND_EXCEPTION e)    {
            logger.warn(e.toString());
            throw e;
        }
    }

    public UpdateProductResponseData updateProductById(Integer pid, UpdateProductRequestData updateProductRequestData) throws Exception {
        try{
            logger.info("Start update product");
            productRepository.updateProductById(
                    pid,
                    updateProductRequestData.getName(),
                    updateProductRequestData.getDescription(),
                    updateProductRequestData.getImageUrl(),
                    updateProductRequestData.getPrice(),
                    updateProductRequestData.getStockQty()
            );
            return new UpdateProductResponseData(productExist(pid));
        } catch (PRODUCT_ID_NOT_FOUND_EXCEPTION e)    {
            logger.warn(e.toString());
            throw e;
        }
    }

    public ProductEntity getProductEntityByPid(Integer pid)  throws Exception    {
        try{
            logger.info("Start get product.(Internal)");
            return productExist(pid);
        } catch (PRODUCT_ID_NOT_FOUND_EXCEPTION e)    {
            logger.warn(e.toString());
            throw e;
        }
    }

    public Integer deductProductQtyByPid(Integer pid, Integer quantity) throws Exception {
        try{
            logger.info("Start deduct product Qty.(Internal)");
            ProductEntity productEntity = productExist(pid);
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

    public ProductEntity productExist(Integer pid) throws PRODUCT_ID_NOT_FOUND_EXCEPTION {
        try {
            logger.info("Start get product Qty.(Internal)");
            logger.info("Get Product: " + pid);
            return productRepository.getProductById(pid).orElseThrow(() -> new PRODUCT_ID_NOT_FOUND_EXCEPTION(pid));
        } catch (Exception e) {
            logger.warn(e.toString());
            throw e;
        }
    }

    public List<ProductByCategoryResponseData> getProductByCategory(Integer catId)  {
        logger.info("Start get product by category.");
        if (getSubCat(catId).size()!=0) {
            //include sub cat item
        }
        return productCategoryRepository.getAllByCatId(catId).stream().map(x -> new ProductByCategoryResponseData(productRepository.getProductById(x).get())).toList();
    }

    public List<Integer> getSubCat(Integer catId) {
        logger.info("Start get sub category.");
        return categoryRepository.getSubCat(catId);
    }
}
