package com.datamart.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterRequest {
        @Email
        @NotBlank
        private String email;
        
        @NotBlank
        @Size(min = 8, message = "Password must be at least 8 characters")
        private String password;
        
        @NotBlank
        private String passwordConfirm;
        
        @NotBlank
        private String firstName;
        
        @NotBlank
        private String lastName;
        
        @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone number is invalid")
        private String phoneNumber;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest {
        @Email
        @NotBlank
        private String email;
        
        @NotBlank
        private String password;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResponse {
        private String access;
        private String refresh;
        private UserDto user;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TokenRefreshRequest {
        @NotBlank
        private String refresh;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TokenRefreshResponse {
        private String access;
        private String refresh;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LogoutRequest {
        @NotBlank
        private String refresh;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthResponse {
        private UUID id;
        private String email;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String role;
        private TokensDto tokens;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TokensDto {
        private String access;
        private String refresh;
    }
}