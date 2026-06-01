package com.ecommerce.product.service;


import com.ecommerce.common.dto.ApiResponseDto;
import com.ecommerce.common.dto.ProductRequest;

public interface ProductService {

    ApiResponseDto<?> createProduct(ProductRequest request);

    ApiResponseDto<?> getAllProducts();

    ApiResponseDto<?> getProductById(String id);

    ApiResponseDto<?> updateProduct(String id, ProductRequest request);

    ApiResponseDto<?> deleteProduct(String id);
}