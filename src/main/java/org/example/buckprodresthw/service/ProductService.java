package org.example.buckprodresthw.service;

import lombok.RequiredArgsConstructor;
import org.example.buckprodresthw.exception.ProductNotFoundException;
import org.example.buckprodresthw.model.Product;
import org.example.buckprodresthw.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public ResponseEntity<String> deleteProductById(Long id) {
        Product productById = getProductById(id);
        productRepository.delete(productById);
        return ResponseEntity.ok("Product deleted successfully");
    }

    public ResponseEntity<Product> updateProductById(Long id, Product product) {
        Product productById = getProductById(id);
        productById.setName(product.getName());
        productById.setPrice(product.getPrice());

        createProduct(productById);
        return ResponseEntity.ok(productById);
    }

    public ResponseEntity<Product> partialUpdateProductById(Long id, Product product) {
        Product productById = getProductById(id);

        if (product.getName() != null) {
            productById.setName(product.getName());
        }
        if (product.getPrice() != null) {
            productById.setPrice(product.getPrice());
        }

        createProduct(productById);
        return ResponseEntity.ok(productById);
    }
}
