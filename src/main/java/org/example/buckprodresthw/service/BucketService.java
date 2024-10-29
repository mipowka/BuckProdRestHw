package org.example.buckprodresthw.service;

import lombok.RequiredArgsConstructor;
import org.example.buckprodresthw.exception.BucketNotFoundException;
import org.example.buckprodresthw.model.Bucket;
import org.example.buckprodresthw.model.Product;
import org.example.buckprodresthw.repository.BucketRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BucketService {

    private final BucketRepository bucketRepository;
    private final ProductService productService;

    public Bucket getBucket(Long id) {
        return bucketRepository.findById(id).orElseThrow(() -> new BucketNotFoundException("Bucket not found"));
    }

    public List<Product> getAllProductsFromBucket(Long id) {
        return getBucket(id).getProducts();
    }

    public Bucket createBucket(Bucket bucket) {
        return bucketRepository.save(bucket);
    }

    public Bucket addProductInBucket(Long bucketId, Long productId) {
        Bucket bucket = getBucket(bucketId);
        Product product = productService.getProductById(productId);
        product.setBucket(bucket);
        bucket.getProducts().add(product);
        return createBucket(bucket);
    }

    public Bucket removeProductFromBucket(Long bucketId, Long productId) {
        Bucket bucket = getBucket(bucketId);
        Product product = productService.getProductById(productId);
        bucket.getProducts().remove(product);
        product.setBucket(null);
        return createBucket(bucket);
    }

    public Bucket removeAllProductsFromBucket(Long bucketId){
        Bucket bucket = getBucket(bucketId);
        Iterator<Product> iterator = bucket.getProducts().iterator();
        while (iterator.hasNext()){
            iterator.next().setBucket(null);
        }
        return createBucket(bucket);
    }
}
