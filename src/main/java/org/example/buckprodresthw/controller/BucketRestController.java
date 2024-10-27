package org.example.buckprodresthw.controller;

import lombok.RequiredArgsConstructor;
import org.example.buckprodresthw.model.Bucket;
import org.example.buckprodresthw.model.Product;
import org.example.buckprodresthw.service.BucketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bucket")
@RequiredArgsConstructor
public class BucketRestController {
    private final BucketService bucketService;

    @GetMapping("/{id}")
    public Bucket getBucket(@PathVariable Long id) {
        return bucketService.getBucket(id);
    }

    @GetMapping("/{id}/products")
    public List<Product> getAllProductsInBucket(@PathVariable Long id) {
        return bucketService.getAllProductsFromBucket(id);
    }

    @PostMapping("/new")
    public Bucket createBucket(@RequestBody Bucket bucket) {
        return bucketService.createBucket(bucket);
    }

    @PostMapping("/{bucketId}/new/product/{productId}")
    public Bucket addProductInBucket(@PathVariable Long bucketId, @PathVariable Long productId) {
        return bucketService.addProductInBucket(bucketId, productId);
    }

    @DeleteMapping("/{bucketId}/delete/product/{productId}")
    public Bucket deleteProductFromBucket(@PathVariable Long bucketId, @PathVariable Long productId) {
        return bucketService.removeProductFromBucket(bucketId, productId);
    }
}
