package com.ecommerce.repository;

import com.ecommerce.model.Review;
import com.ecommerce.model.User;
import com.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long productId);
    List<Review> findByUserId(Long userId);
    List<Review> findByRatingGreaterThanEqual(Integer minRating);
    List<Review> findByUserIdOrderByCreatedAtDesc(Long userId);
    Page<Review> findByProductId(Long productId, Pageable pageable);
    
    @Query("SELECT r FROM Review r JOIN FETCH r.user u WHERE r.product.id = :productId")
    List<Review> findReviewsWithUserByProductId(@Param("productId") Long productId);
    
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.product.id = :productId")
    Double findAverageRatingByProductId(@Param("productId") Long productId);
}