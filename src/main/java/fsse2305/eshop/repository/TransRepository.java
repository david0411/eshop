package fsse2305.eshop.repository;

import fsse2305.eshop.data.entity.TransactionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransRepository extends MongoRepository<TransactionEntity, String> {
    TransactionEntity findByBuyerUidAndTid(String uid, String tid);
}
