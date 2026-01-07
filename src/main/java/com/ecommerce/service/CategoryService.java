package com.ecommerce.service;

import com.ecommerce.dto.CategoryDto;
import com.ecommerce.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category createCategory(CategoryDto categoryDto);
    Optional<Category> getCategoryById(Long id);
    List<Category> getAllCategories();
    Page<Category> getAllCategories(Pageable pageable);
    List<Category> getTopLevelCategories();
    List<Category> getSubcategories(Long parentId);
    Category updateCategory(Long id, CategoryDto categoryDto);
    void deleteCategory(Long id);
    boolean existsByName(String name);
}