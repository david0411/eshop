package fsse2305.eshop.repository;

import fsse2305.eshop.data.entity.TransactionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;

public interface TransRepository extends CrudRepository<TransactionEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO transaction (buyer_uid,datetime,status,total) " +
            "VALUES (:uid,:datetime,:transStatus,0)", nativeQuery = true)
    Integer createTransaction(Integer uid, Timestamp datetime, String transStatus);

    @Query(value = "SELECT * FROM transaction WHERE datetime=:timestamp", nativeQuery = true)
    TransactionEntity getTransactionEntityByTransTime(Timestamp timestamp);

    @Query(value = "SELECT * FROM transaction WHERE buyer_uid=:uid AND tid=:tid", nativeQuery = true)
    TransactionEntity getTransactionEntityByUidAndTid(Integer uid, Integer tid);
    @Modifying
    @Transactional
    @Query(value = "UPDATE transaction SET " +
            "status=:transStatus " +
            "WHERE buyer_uid=:uid " +
            "AND tid=:tid", nativeQuery = true)
    Integer updateTransStatusByUidAndTid(Integer uid, Integer tid, String transStatus);
}
