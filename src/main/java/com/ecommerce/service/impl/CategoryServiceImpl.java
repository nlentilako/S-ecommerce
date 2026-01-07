package com.ecommerce.service.impl;

import com.ecommerce.dto.CategoryDto;
import com.ecommerce.model.Category;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Override
    public Category createCategory(CategoryDto categoryDto) {
        if (categoryRepository.existsByName(categoryDto.getName())) {
            throw new RuntimeException("Category with name already exists: " + categoryDto.getName());
        }
        
        Category category = new Category(
            categoryDto.getName(),
            categoryDto.getDescription(),
            categoryDto.getImageUrl()
        );
        
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> parentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (parentCategory.isPresent()) {
                category.setParentCategory(parentCategory.get());
            } else {
                throw new RuntimeException("Parent category not found with id: " + categoryDto.getParentCategoryId());
            }
        }
        
        return categoryRepository.save(category);
    }
    
    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
    
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    @Override
    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
    
    @Override
    public List<Category> getTopLevelCategories() {
        return categoryRepository.findByParentCategoryIsNull();
    }
    
    @Override
    public List<Category> getSubcategories(Long parentId) {
        return categoryRepository.findByParentCategory_Id(parentId);
    }
    
    @Override
    public Category updateCategory(Long id, CategoryDto categoryDto) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            Category category = existingCategory.get();
            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());
            category.setImageUrl(categoryDto.getImageUrl());
            
            // Update parent category if provided
            if (categoryDto.getParentCategoryId() != null) {
                if (!categoryDto.getParentCategoryId().equals(id)) { // Prevent circular reference
                    Optional<Category> parentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
                    if (parentCategory.isPresent()) {
                        category.setParentCategory(parentCategory.get());
                    } else {
                        throw new RuntimeException("Parent category not found with id: " + categoryDto.getParentCategoryId());
                    }
                } else {
                    throw new RuntimeException("Parent category cannot be itself");
                }
            } else {
                category.setParentCategory(null); // Set as top-level category
            }
            
            category.setUpdatedAt(java.time.LocalDateTime.now());
            return categoryRepository.save(category);
        } else {
            throw new RuntimeException("Category not found with id: " + id);
        }
    }
    
    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
    
    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }
}