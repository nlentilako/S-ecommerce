package com.datamart.api.dto;

import com.datamart.api.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    
    private UUID id;
    
    private String reference;
    
    private Transaction.TransactionType transactionType;
    
    private BigDecimal amount;
    
    private Transaction.TransactionStatus status;
    
    private String description;
    
    private LocalDateTime createdAt;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransactionFilterRequest {
        private String transactionType;
        private String status;
        private String startDate;
        private String endDate;
        private Integer page = 1;
        private Integer pageSize = 20;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransactionResponse {
        private Integer count;
        private List<TransactionDto> results;
    }
}