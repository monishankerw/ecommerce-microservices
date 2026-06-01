package com.ecommerce.common.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private String id;

    private String productName;

    private String description;

    private Double price;

    private Integer stock;

    private String category;

    private String brand;

    private Boolean active;
}