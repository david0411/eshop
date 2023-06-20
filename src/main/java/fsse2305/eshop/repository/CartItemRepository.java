package fsse2305.eshop.repository;

import fsse2305.eshop.data.entity.CartItemEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItemEntity, Integer> {
    @Modifying
    @Transactional
    @Query(value = "insert into cart_item set " +
            "pid = :pid," +
            "uid = :uid," +
            "quantity = :quantity", nativeQuery = true
    )
    Integer addItem2Cart(Integer uid,Integer pid,Integer quantity);
}
