package fsse2305.eshop.repository;

import fsse2305.eshop.data.entity.CartItemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CartItemRepository extends MongoRepository<CartItemEntity, String> {

    List<CartItemEntity> findByUid(String uid);

    CartItemEntity findByUidAndPid(String uid, String pid);

    Integer deleteByUid(String uid);

    Integer deleteByUidAndPid(String uid, String pid);
}
