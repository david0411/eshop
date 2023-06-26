package fsse2305.eshop.repository;

import fsse2305.eshop.data.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductCategoryRepository extends CrudRepository<ProductCategoryEntity, Integer> {
    @Query(value = "SELECT pid FROM product_cat where cat_id =:catId", nativeQuery = true)
    List<Integer> getAllByCatId(Integer catId);
}
