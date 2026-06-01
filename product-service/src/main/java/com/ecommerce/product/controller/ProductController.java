package com.ecommerce.product.controller;


import com.ecommerce.common.dto.ProductRequest;
import com.ecommerce.product.service.ProductService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok(service.createProduct(request));
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok(service.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        return ResponseEntity.ok(service.deleteProduct(id));
    }
}