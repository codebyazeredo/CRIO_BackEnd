package com.crio.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.api.domain.user.User;
import com.crio.api.domain.user.UserRequestDTO;
import com.crio.api.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserRequestDTO urdto) {

        User newUser = new User();
        newUser.setUsername(urdto.username());
        newUser.setEmail(urdto.email());
        newUser.setPassword(urdto.password());
        newUser.setType(urdto.type());
        newUser.setCreatedAt(LocalDateTime.now());

        userRepository.save(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id).or(() -> {
            throw new RuntimeException("User not found");
        });
    }

    @Transactional
    public User updateUser(UUID id, UserRequestDTO urdto) {
        
        User user = getUserById(id).orElseThrow();
        user.setUsername(urdto.username());
        user.setEmail(urdto.email());
        user.setPassword(urdto.password());
        user.setType(urdto.type());
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

     public Optional<User> findUserByEmail(String email) {
       Optional<User> user = userRepository.findUserByEmail(email);
       return user;
    }

    public List<User> findUserByType(Integer type) {
        return userRepository.findUserByType(type);
    }    

    public List<User> findByCreatedAtBetween(LocalDateTime createdAt, LocalDateTime end) {
        return userRepository.findByCreatedAtBetween(createdAt, end);
    }

}
