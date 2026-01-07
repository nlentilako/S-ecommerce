package com.ecommerce.service;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(ProductDto productDto);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    Page<Product> getAllProducts(Pageable pageable);
    Page<Product> findProducts(String name, Long categoryId, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
    Product updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
    List<Product> getProductsByCategory(Long categoryId);
    Page<Product> getProductsByCategory(Long categoryId, Pageable pageable);
}