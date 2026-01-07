package com.datamart.api.repository;

import com.datamart.api.model.ApiKey;
import com.datamart.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, UUID> {
    Optional<ApiKey> findByApiKey(String apiKey);
    Optional<ApiKey> findByUserIdAndName(UUID userId, String name);
    boolean existsByApiKey(String apiKey);
}