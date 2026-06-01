package com.ecommerce.common.dto;

import jakarta.validation.constraints.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank(message = "Product Name Required")
    private String productName;

    @NotBlank(message = "Description Required")
    private String description;

    @NotNull(message = "Price Required")
    @Positive
    private Double price;

    @NotNull(message = "Stock Required")
    private Integer stock;

    @NotBlank(message = "Category Required")
    private String category;

    @NotBlank(message = "Brand Required")
    private String brand;
}