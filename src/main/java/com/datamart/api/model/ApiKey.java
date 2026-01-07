package com.datamart.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "api_keys")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiKey {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    private String name;
    
    @Column(unique = true)
    private String apiKey;
    
    @Column(name = "key_prefix")
    private String keyPrefix;
    
    @Enumerated(EnumType.STRING)
    private Environment environment;
    
    @ElementCollection
    @CollectionTable(name = "api_key_permissions", joinColumns = @JoinColumn(name = "api_key_id"))
    @Column(name = "permission")
    private List<String> permissions;
    
    @ElementCollection
    @CollectionTable(name = "api_key_allowed_ips", joinColumns = @JoinColumn(name = "api_key_id"))
    @Column(name = "allowed_ip")
    private List<String> allowedIps;
    
    private boolean isActive = true;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public enum Environment {
        LIVE, TEST
    }
}