package com.datamart.api.repository;

import com.datamart.api.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    
    Page<Product> findByCategory(Product.ProductCategory category, Pageable pageable);
    
    Page<Product> findByNetwork(Product.NetworkType network, Pageable pageable);
    
    Page<Product> findByIsActiveTrue(Pageable pageable);
    
    Page<Product> findByCategoryAndNetwork(Product.ProductCategory category, Product.NetworkType network, Pageable pageable);
    
    @Query("SELECT p FROM Product p WHERE " +
           "p.isActive = true AND " +
           "(:category IS NULL OR p.category = :category) AND " +
           "(:network IS NULL OR p.network = :network) AND " +
           "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
           "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
           "(:search IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Product> findProducts(@Param("category") Product.ProductCategory category,
                               @Param("network") Product.NetworkType network,
                               @Param("minPrice") java.math.BigDecimal minPrice,
                               @Param("maxPrice") java.math.BigDecimal maxPrice,
                               @Param("search") String search,
                               Pageable pageable);
}