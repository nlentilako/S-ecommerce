package com.datamart.api.dto;

import com.datamart.api.model.ApiKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiKeyDto {
    
    private UUID id;
    
    private String name;
    
    private String apiKey;
    
    private String keyPrefix;
    
    private ApiKey.Environment environment;
    
    private List<String> permissions;
    
    private List<String> allowedIps;
    
    private boolean isActive;
    
    private LocalDateTime createdAt;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateApiKeyRequest {
        private String name;
        
        private ApiKey.Environment environment;
        
        private List<String> permissions;
        
        private List<String> allowedIps;
    }
}