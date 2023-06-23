package fsse2305.eshop.data.entity;

import fsse2305.eshop.user.FirebaseUserData;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String uid;
    private String firebaseUid;
    private String email;

    public UserEntity() {
    }

    public UserEntity(FirebaseUserData firebaseUserData)    {
        this.firebaseUid = firebaseUserData.getFirebaseUid();
        this.email = firebaseUserData.getEmail();
    }

    public UserEntity(String uid, String firebaseUid, String email) {
        this.uid = uid;
        this.firebaseUid = firebaseUid;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
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

    @Override
    public String toString() {
        return "UserEntity{" +
                "uid=" + uid +
                ", firebaseUid='" + firebaseUid + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
