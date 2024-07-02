package com.example.springproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // add a new product
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    // get all products
    @GetMapping
    public ResponseEntity<List<Product>> getProductData() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // get product by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        if(optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    // replace an existing product
    @PutMapping("/{id}")
    public ResponseEntity<Void> replaceProduct(@PathVariable Long id, @RequestBody Product newProduct) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        if(optionalProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        productService.replaceProduct(id, newProduct);
        return ResponseEntity.ok().build();
    }

    // delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        if(optionalProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    // subtract amount from product quantity
    @PatchMapping("/buy/{id}")
    public ResponseEntity<Product> subtractFromProduct(@PathVariable Long id, @RequestBody Long amt) {
        Product updatedProduct = productService.buyProduct(id, amt);
        return ResponseEntity.ok(updatedProduct);
    }
}
