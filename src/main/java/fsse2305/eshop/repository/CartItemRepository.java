package fsse2305.eshop.repository;

import fsse2305.eshop.data.entity.CartItemEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartItemRepository extends CrudRepository<CartItemEntity, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cart_item (uid,pid,quantity)" +
            "VALUES (:uid,:pid,:quantity)", nativeQuery = true
    )
    Integer addItem2Cart(Integer uid,Integer pid,Integer quantity);

    @Query(value = "SELECT c.* FROM cart_item c Left Join product p ON c.pid=p.pid WHERE c.uid=:uid AND c.pid = :pid", nativeQuery = true)
    CartItemEntity getCartItemByUidAAndPid(Integer uid, Integer pid);

    @Query(value = "SELECT * FROM cart_item WHERE uid=:uid", nativeQuery = true)
    List<CartItemEntity> getCartItemByUid(Integer uid);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cart_item SET " +
            "quantity = :quantity " +
            "WHERE pid = :pid " +
            "AND uid = :uid", nativeQuery = true)
    Integer updateCartItemByPid(Integer uid, Integer pid,Integer quantity);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cart_item " +
            "WHERE pid = :pid " +
            "AND uid = :uid", nativeQuery = true)
    Integer deleteCartItemByPid(Integer uid, Integer pid);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cart_item WHERE uid=:uid", nativeQuery = true)
    Integer deleteCartItemByUid(Integer uid);
}
