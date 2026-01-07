package com.datamart.api.dto;

import com.datamart.api.model.UserProfile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    
    private UUID id;
    
    @Email
    private String email;
    
    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;
    
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone number is invalid")
    private String phoneNumber;
    
    private String role;
    
    private boolean isActive;
    
    private LocalDateTime dateJoined;
    
    private UserProfileDto profile;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserProfileDto {
        private String avatar;
        private String dateOfBirth;
        private String address;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateProfileRequest {
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private UserProfileDto profile;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChangePasswordRequest {
        private String oldPassword;
        private String newPassword;
        private String newPasswordConfirm;
    }
}