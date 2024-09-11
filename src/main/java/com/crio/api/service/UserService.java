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

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserRequestDTO urdto){

        User newUser = new User();
        newUser.setName(urdto.name());
        newUser.setEmail(urdto.email());
        newUser.setPassword(urdto.password());
        newUser.setType(urdto.type());
        newUser.setCreatedAt(LocalDateTime.now());

        userRepository.save(newUser);
        return newUser;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(UUID id){
        return Optional.of(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }

    public User updateUser(UUID id, UserRequestDTO urdto){
        
        User updatedUser = getUserById(id).orElseThrow();
        
        updatedUser.setName(urdto.name());
        updatedUser.setEmail(urdto.email());
        updatedUser.setPassword(urdto.password());
        updatedUser.setType(urdto.type());
        updatedUser.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(updatedUser);

    }

    public void deleteUser (UUID id) {
        userRepository.deleteById(id);
    }
}
