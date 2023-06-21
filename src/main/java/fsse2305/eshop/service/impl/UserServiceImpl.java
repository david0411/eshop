package fsse2305.eshop.service.impl;

import fsse2305.eshop.data.entity.UserEntity;
import fsse2305.eshop.repository.UserRepository;
import fsse2305.eshop.service.UserService;
import fsse2305.eshop.user.FirebaseUserData;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl  implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository)   {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getEntityByFirebaseUserData(FirebaseUserData firebaseUserData) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByFirebaseUid(firebaseUserData.getFirebaseUid());
        return optionalUserEntity.orElseGet(() -> userRepository.save(new UserEntity(firebaseUserData)));
    }
}
