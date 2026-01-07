package com.datamart.api.dto;

import com.datamart.api.model.Agent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentDto {
    
    private UUID id;
    
    private Agent.AgentStatus status;
    
    private String businessName;
    
    private String businessType;
    
    private String businessAddress;
    
    private String ghanaCardNumber;
    
    private String ghanaCardImage;
    
    private String reason;
    
    private LocalDateTime appliedAt;
    
    private LocalDateTime reviewedAt;
    
    private String reviewerNotes;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AgentApplicationRequest {
        private String businessName;
        
        private String businessType;
        
        private String businessAddress;
        
        private String ghanaCardNumber;
        
        private String ghanaCardImage;
        
        private String reason;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AgentApprovalRequest {
        private String reason;
    }
}