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
    @Query(value ="INSERT INTO transaction_product (tid,pid,name,description,image_url,price,stock_qty,quantity,subtotal) " +
            "VALUES (:tid,:pid,:name,:description,:imageUrl,:price,:stockQty,:quantity,:subtotal)", nativeQuery = true )
    Integer createTransProduct(Integer tid, Integer pid, String name, String description, String imageUrl, BigDecimal price, Integer stockQty, Integer quantity, BigDecimal subtotal);

    @Query(value = "SELECT * FROM transaction_product WHERE pid=:pid AND tid=:tid", nativeQuery = true)
    TransProductEntity getTransactionProductEntityByPidAndTid(Integer pid, Integer tid);

    @Query(value = "SELECT * FROM transaction_product WHERE tid=:tid", nativeQuery = true)
    List<TransProductEntity> getTransactionProductEntityByTid(int tid);
}
