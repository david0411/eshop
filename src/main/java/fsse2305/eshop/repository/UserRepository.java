package fsse2305.eshop.repository;

import fsse2305.eshop.data.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByFirebaseUid(String firebaseUid);
}
