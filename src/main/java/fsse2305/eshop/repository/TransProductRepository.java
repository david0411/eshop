package fsse2305.eshop.repository;

import fsse2305.eshop.data.entity.TransProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransProductRepository extends MongoRepository<TransProductEntity, String> {
    List<TransProductEntity> findAllByTid(String tid);
}
