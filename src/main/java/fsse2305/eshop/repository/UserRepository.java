package fsse2305.eshop.repository;

import fsse2305.eshop.data.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByFirebaseUid(String firebaseUid);
}
