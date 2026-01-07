package com.ecommerce.service.impl;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public Product createProduct(ProductDto productDto) {
        Optional<Category> category = categoryRepository.findById(productDto.getCategoryId());
        Optional<User> seller = userRepository.findById(productDto.getSellerId());
        
        if (category.isEmpty()) {
            throw new RuntimeException("Category not found with id: " + productDto.getCategoryId());
        }
        
        if (seller.isEmpty()) {
            throw new RuntimeException("Seller not found with id: " + productDto.getSellerId());
        }
        
        Product product = new Product(
            productDto.getName(),
            productDto.getDescription(),
            productDto.getPrice(),
            productDto.getImageUrl(),
            productDto.getStockQuantity(),
            category.get(),
            seller.get()
        );
        
        return productRepository.save(product);
    }
    
    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    
    @Override
    public Page<Product> findProducts(String name, Long categoryId, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        return productRepository.findProducts(name, categoryId, minPrice, maxPrice, pageable);
    }
    
    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setImageUrl(productDto.getImageUrl());
            product.setStockQuantity(productDto.getStockQuantity());
            
            // Update category if provided
            if (productDto.getCategoryId() != null) {
                Optional<Category> category = categoryRepository.findById(productDto.getCategoryId());
                if (category.isPresent()) {
                    product.setCategory(category.get());
                } else {
                    throw new RuntimeException("Category not found with id: " + productDto.getCategoryId());
                }
            }
            
            // Update seller if provided
            if (productDto.getSellerId() != null) {
                Optional<User> seller = userRepository.findById(productDto.getSellerId());
                if (seller.isPresent()) {
                    product.setSeller(seller.get());
                } else {
                    throw new RuntimeException("Seller not found with id: " + productDto.getSellerId());
                }
            }
            
            product.setUpdatedAt(java.time.LocalDateTime.now());
            return productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }
    
    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
    
    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategory_Id(categoryId);
    }
    
    @Override
    public Page<Product> getProductsByCategory(Long categoryId, Pageable pageable) {
        return productRepository.findByCategory_Id(categoryId, pageable);
    }
}