package fsse2305.eshop.repository;

import fsse2305.eshop.data.entity.TransProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface TransProductRepository extends CrudRepository<TransProductEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value ="INSERT INTO transaction_product set " +
            "tid=:tid," +
            "pid=:pid," +
            "name=:name," +
            "description=:description," +
            "image_url=:imageUrl," +
            "price=:price," +
            "stock_qty=:stockQty," +
            "quantity=:quantity," +
            "subtotal=:subtotal", nativeQuery = true )
    Integer createTransProduct(Integer tid, Integer pid, String name, String description, String imageUrl, BigDecimal price, Integer stockQty, Integer quantity, BigDecimal subtotal);

    @Query(value = "select * from transaction_product where pid=:pid and tid=:tid", nativeQuery = true)
    TransProductEntity getTransactionEntityByPidAndTid(Integer pid, Integer tid);

    @Query(value = "select * from transaction_product where tid=:tid", nativeQuery = true)
    List<TransProductEntity> getTransactionEntityByTid(int tid);
}
