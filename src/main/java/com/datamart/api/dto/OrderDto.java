package com.datamart.api.dto;

import com.datamart.api.model.Order;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class OrderDto {
    
    private UUID id;
    
    private String orderNumber;
    
    private ProductDto product;
    
    private Integer quantity;
    
    private BigDecimal unitPrice;
    
    private BigDecimal totalAmount;
    
    private Order.OrderStatus status;
    
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone number is invalid")
    private String recipientPhone;
    
    private Order.PaymentMethod paymentMethod;
    
    private LocalDateTime createdAt;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateOrderRequest {
        @NotNull
        private UUID productId;
        
        private Integer quantity = 1;
        
        @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone number is invalid")
        private String recipientPhone;
        
        @NotNull
        private Order.PaymentMethod paymentMethod;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateBulkOrderRequest {
        @NotNull
        private UUID productId;
        
        @NotNull
        private List<@Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone number is invalid") String> recipients;
        
        @NotNull
        private Order.PaymentMethod paymentMethod;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderFilterRequest {
        private String status;
        private String startDate;
        private String endDate;
        private Integer page = 1;
        private Integer pageSize = 20;
    }
}