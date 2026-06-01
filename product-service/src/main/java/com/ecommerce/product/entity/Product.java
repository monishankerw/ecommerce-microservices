package com.ecommerce.product.entity;

import lombok.*;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    private String id;

    private String productName;

    private String description;

    private Double price;

    private Integer stock;

    private String category;

    private String brand;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}