package fsse2305.eshop.data.entity;

import fsse2305.eshop.user.FirebaseUserData;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;
    @Column(name = "firebase_uid", unique = true)
    private String firebaseUid;
    private String email;
    @OneToMany
    @JoinColumn(name = "uid")
    private List<CartItemEntity> cartItemEntityList = new ArrayList<>();

    public UserEntity(FirebaseUserData firebaseUserData)    {
        this.firebaseUid = firebaseUserData.getFirebaseUid();
        this.email = firebaseUserData.getEmail();
    }

    public UserEntity() {
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
