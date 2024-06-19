package com.example.springproject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void replaceProduct(Long id, Product newProduct) {
        newProduct.setId(id);
        productRepository.save(newProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
