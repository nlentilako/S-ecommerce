package com.datamart.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false)
    private String name;
    
    private String description;
    
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    
    @Enumerated(EnumType.STRING)
    private NetworkType network;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    
    @Column(name = "agent_price", precision = 10, scale = 2)
    private BigDecimal agentPrice;
    
    @Column(name = "data_amount")
    private String dataAmount;
    
    @Column(name = "validity_days")
    private Integer validityDays;
    
    private boolean isActive = true;
    
    @Column(name = "is_featured")
    private boolean isFeatured = false;
    
    private String image;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public enum ProductCategory {
        DATA, AIRTIME, VOUCHER, SMS, WAEC
    }
    
    public enum NetworkType {
        MTN, VODAFONE, AIRTELTIGO, TELECEL
    }
}