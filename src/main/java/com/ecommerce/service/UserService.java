package com.ecommerce.service;

import com.ecommerce.dto.UserDto;
import com.ecommerce.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(UserDto userDto);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    Page<User> getAllUsers(Pageable pageable);
    User updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}