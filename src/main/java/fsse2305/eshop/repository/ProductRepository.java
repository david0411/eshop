package fsse2305.eshop.repository;

import fsse2305.eshop.data.entity.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

    @Query(value = "select * from product", nativeQuery = true)
    List<ProductEntity> getAllProduct();

    @Query(value = "select * from product where pid=:pid", nativeQuery = true)
    ProductEntity getProductById(Integer pid);

    @Query(value = "update product set " +
              "name=:name," +
              "description=:description," +
              "image_url=:imageUrl," +
              "price=:price," +
              "stock_qty=:stockQty " +
              "where pid=:pid", nativeQuery = true)
    ProductEntity updateProductById(Integer pid, String name, String description, String imageUrl, BigDecimal price, Integer stockQty);
    @Modifying
    @Transactional
    @Query(value = "update product set " +
            "stock_qty=:deductProductResult " +
            "where pid=:pid", nativeQuery = true)
    Integer deductProductQtyById(Integer pid, Integer deductProductResult);
}

