package com.ecommerce.ecommerceapp.modules.user.service;

import com.ecommerce.ecommerceapp.modules.user.dto.UserCreateRequest;
import com.ecommerce.ecommerceapp.modules.user.dto.UserDto;
import com.ecommerce.ecommerceapp.modules.user.dto.UserUpdateRequest;
import com.ecommerce.ecommerceapp.modules.user.entity.User;
import com.ecommerce.ecommerceapp.modules.user.entity.UserRole;
import com.ecommerce.ecommerceapp.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<UserDto> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(this::convertToDto);
    }

    public Page<UserDto> getUsersWithFilters(String firstName, String lastName, String role, Pageable pageable) {
        Page<User> users = userRepository.findUsersWithFilters(firstName, lastName, role, pageable);
        return users.map(this::convertToDto);
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
        return convertToDto(user);
    }

    public UserDto createUser(UserCreateRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("User with email already exists: " + request.getEmail());
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRole(UserRole.valueOf(request.getRole()));
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

    public UserDto updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));

        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getRole() != null) {
            user.setRole(UserRole.valueOf(request.getRole()));
        }
        user.setUpdatedAt(LocalDateTime.now());

        User updatedUser = userRepository.save(user);
        return convertToDto(updatedUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private UserDto convertToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole().name(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}