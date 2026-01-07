package com.datamart.api.repository;

import com.datamart.api.model.Notification;
import com.datamart.api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    
    Page<Notification> findByUser(User user, Pageable pageable);
    
    List<Notification> findByUserAndIsReadFalse(User user);
    
    long countByUserAndIsReadFalse(User user);
    
    void deleteByUser(User user);
}