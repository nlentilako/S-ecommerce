package com.datamart.api.repository;

import com.datamart.api.model.Transaction;
import com.datamart.api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    
    Page<Transaction> findByUser(User user, Pageable pageable);
    
    Page<Transaction> findByTransactionType(Transaction.TransactionType transactionType, Pageable pageable);
    
    Page<Transaction> findByStatus(Transaction.TransactionStatus status, Pageable pageable);
    
    @Query("SELECT t FROM Transaction t WHERE " +
           "t.user = :user AND " +
           "(:transactionType IS NULL OR t.transactionType = :transactionType) AND " +
           "(:status IS NULL OR t.status = :status) AND " +
           "(:startDate IS NULL OR t.createdAt >= :startDate) AND " +
           "(:endDate IS NULL OR t.createdAt <= :endDate)")
    Page<Transaction> findTransactionsByUserAndFilters(@Param("user") User user,
                                                       @Param("transactionType") Transaction.TransactionType transactionType,
                                                       @Param("status") Transaction.TransactionStatus status,
                                                       @Param("startDate") LocalDateTime startDate,
                                                       @Param("endDate") LocalDateTime endDate,
                                                       Pageable pageable);
}