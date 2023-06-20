package fsse2305.eshop.service;

import fsse2305.eshop.user.FirebaseUserData;
import fsse2305.eshop.UserEntity;

public interface UserService {
    UserEntity getEntityByFirebaseUserData(FirebaseUserData firebaseUserData);
}
