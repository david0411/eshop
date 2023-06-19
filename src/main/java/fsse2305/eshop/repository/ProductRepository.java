package fsse2305.eshop.repository;

import fsse2305.eshop.data.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {


    List<ProductEntity> getAllProduct();
}
