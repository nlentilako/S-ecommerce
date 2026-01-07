package com.datamart.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "agents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(name = "business_name")
    private String businessName;
    
    @Column(name = "business_type")
    private String businessType;
    
    @Column(name = "business_address")
    private String businessAddress;
    
    @Column(name = "ghana_card_number")
    private String ghanaCardNumber;
    
    @Column(name = "ghana_card_image")
    private String ghanaCardImage;
    
    private String reason;
    
    @Enumerated(EnumType.STRING)
    private AgentStatus status = AgentStatus.PENDING;
    
    @Column(name = "applied_at")
    private LocalDateTime appliedAt = LocalDateTime.now();
    
    @Column(name = "reviewed_at")
    private LocalDateTime reviewedAt;
    
    @Column(name = "reviewer_notes")
    private String reviewerNotes;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public enum AgentStatus {
        PENDING, APPROVED, REJECTED, SUSPENDED
    }
}