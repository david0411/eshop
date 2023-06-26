package fsse2305.eshop.repository;

import fsse2305.eshop.data.entity.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

    @Query(value = "SELECT * FROM product", nativeQuery = true)
    List<ProductEntity> getAllProduct();

    @Query(value = "SELECT * FROM product WHERE pid=:pid", nativeQuery = true)
    Optional<ProductEntity> getProductById(Integer pid);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product SET " +
              "name=:name," +
              "description=:description," +
              "image_url=:imageUrl," +
              "price=:price," +
              "stock_qty=:stockQty " +
              "WHERE pid=:pid", nativeQuery = true)
    Integer updateProductById(Integer pid, String name, String description, String imageUrl, BigDecimal price, Integer stockQty);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product SET " +
            "stock_qty=:updatedQty " +
            "WHERE pid=:pid", nativeQuery = true)
    Integer deductProductQtyById(Integer pid, Integer updatedQty);
}

