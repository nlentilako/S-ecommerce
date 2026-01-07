package com.datamart.api.dto;

import com.datamart.api.model.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    
    private UUID id;
    
    private String title;
    
    private String message;
    
    private Notification.NotificationType notificationType;
    
    private boolean isRead;
    
    private LocalDateTime createdAt;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NotificationResponse {
        private Integer count;
        private Integer unreadCount;
        private List<NotificationDto> results;
    }
}