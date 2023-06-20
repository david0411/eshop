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
    @Query(value = "insert into cart_item set " +
            "pid = :pid," +
            "uid = :uid," +
            "quantity = :quantity", nativeQuery = true
    )
    Integer addItem2Cart(Integer uid,Integer pid,Integer quantity);

    @Query(value = "select * from cart_item where uid=:uid and pid = :pid", nativeQuery = true)
    CartItemEntity getCartItemByUidAAndPid(Integer uid, Integer pid);

    @Query(value = "select * from cart_item where uid=:uid", nativeQuery = true)
    List<CartItemEntity> getCartItemByUid(Integer uid);

    @Modifying
    @Transactional
    @Query(value = "update cart_item set " +
            "quantity = :quantity " +
            "where pid = :pid " +
            "and uid = :uid", nativeQuery = true)
    Integer updateCartItemByPid(Integer uid, Integer pid,Integer quantity);
}
