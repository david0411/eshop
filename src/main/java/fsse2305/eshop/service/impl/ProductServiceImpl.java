package fsse2305.eshop.service.impl;

import fsse2305.eshop.repository.ProductRepository;
import fsse2305.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    public ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository)  {
        this.productRepository = productRepository;
    }

    public void getAllProduct() {
        productRepository.getAllProduct();
    }
}
