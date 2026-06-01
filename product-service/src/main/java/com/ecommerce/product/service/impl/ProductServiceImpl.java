package com.ecommerce.product.service.impl;
import com.ecommerce.common.dto.ApiResponseDto;
import com.ecommerce.common.dto.ProductRequest;
import com.ecommerce.common.exception.ProductNotFoundException;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.repository.ProductRepository;
import com.ecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public ApiResponseDto<?> createProduct(ProductRequest request) {

        Product product = Product.builder().productName(request.getProductName()).description(request.getDescription()).price(request.getPrice()).stock(request.getStock()).category(request.getCategory()).brand(request.getBrand()).active(true).createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();

        Product savedProduct = repository.save(product);

        return ApiResponseDto.builder().success(true).message("Product Created Successfully").data(savedProduct).build();
    }

    @Override
    public ApiResponseDto<?> getAllProducts() {

        return ApiResponseDto.builder().success(true).message("Products Fetched Successfully").data(repository.findAll()).build();
    }

    @Override
    public ApiResponseDto<?> getProductById(String id) {

        Product product = repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

        return ApiResponseDto.builder().success(true).message("Product Fetched Successfully").data(product).build();
    }

    @Override
    public ApiResponseDto<?> updateProduct(String id, ProductRequest request) {

        Product product = repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

        product.setProductName(request.getProductName());

        product.setDescription(request.getDescription());

        product.setPrice(request.getPrice());

        product.setStock(request.getStock());

        product.setCategory(request.getCategory());

        product.setBrand(request.getBrand());

        product.setUpdatedAt(LocalDateTime.now());

        Product updatedProduct = repository.save(product);

        return ApiResponseDto.builder().success(true).message("Product Updated Successfully").data(updatedProduct).build();
    }

    @Override
    public ApiResponseDto<?> deleteProduct(String id) {

        Product product = repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

        repository.delete(product);

        return ApiResponseDto.builder().success(true).message("Product Deleted Successfully").data(null).build();
    }
}