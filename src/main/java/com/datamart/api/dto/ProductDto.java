package com.datamart.api.dto;

import com.datamart.api.model.Product;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    
    private UUID id;
    
    @NotBlank
    private String name;
    
    private String description;
    
    private Product.ProductCategory category;
    
    private Product.NetworkType network;
    
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;
    
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal agentPrice;
    
    private String dataAmount;
    
    private Integer validityDays;
    
    private boolean isActive;
    
    private boolean isFeatured;
    
    private String image;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductResponse {
        private Integer count;
        private String next;
        private String previous;
        private List<ProductDto> results;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductFilterRequest {
        private String category;
        private String network;
        private Boolean isActive;
        private BigDecimal minPrice;
        private BigDecimal maxPrice;
        private String search;
        private Integer page = 1;
        private Integer pageSize = 20;
    }
}