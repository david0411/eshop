package fsse2305.eshop.repository;

import fsse2305.eshop.data.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    @Query(value = "SELECT * FROM user WHERE firebase_uid=:firebaseUid", nativeQuery = true)
    Optional<UserEntity> findByFirebaseUid(String firebaseUid);
}
