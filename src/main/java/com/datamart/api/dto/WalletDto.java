package com.datamart.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletDto {
    
    private UUID id;
    
    private BigDecimal balance;
    
    private String currency;
    
    private boolean isActive;
    
    private LocalDateTime createdAt;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FundWalletRequest {
        @DecimalMin(value = "1.0", inclusive = true)
        private BigDecimal amount;
        
        @NotNull
        private String paymentMethod;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FundWalletResponse {
        private String reference;
        private BigDecimal amount;
        private String paymentUrl;
        private String status;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WithdrawRequest {
        @DecimalMin(value = "1.0", inclusive = true)
        private BigDecimal amount;
        
        @NotBlank
        private String bankCode;
        
        @NotBlank
        private String accountNumber;
        
        @NotBlank
        private String accountName;
    }
}