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
    @Query(value = "INSERT INTO transaction set " +
            "buyer_uid=:uid," +
            "datetime=:datetime," +
            "status=:transStatus," +
            "total=0", nativeQuery = true)
    Integer createTransaction(Integer uid, Timestamp datetime, String transStatus);

    @Query(value = "select * from transaction where datetime=:timestamp", nativeQuery = true)
    TransactionEntity getTransactionEntityByTransTime(Timestamp timestamp);

    @Query(value = "select * from transaction where buyer_uid=:uid and tid=:tid", nativeQuery = true)
    TransactionEntity getTransactionEntityByUidAndTid(Integer uid, Integer tid);
    @Modifying
    @Transactional
    @Query(value = "update transaction set " +
            "status=:transStatus " +
            "where buyer_uid=:uid " +
            "and tid=:tid", nativeQuery = true)
    Integer updateTransStatusByUidAndTid(Integer uid, Integer tid, String transStatus);
}
