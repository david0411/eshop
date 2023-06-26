package fsse2305.eshop.repository;

import fsse2305.eshop.data.entity.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {

    @Query(value = "SELECT cat_id FROM category where u_cat_id=:catId", nativeQuery = true)
    List<Integer> getSubCat(Integer catId);
}
