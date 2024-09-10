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

    public User createUser(UserRequestDTO userRequestDTO){

        User newUser = new User();
        newUser.setName(userRequestDTO.name());
        newUser.setEmail(userRequestDTO.email());
        newUser.setPassword(userRequestDTO.password());
        newUser.setType(userRequestDTO.type());
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

    public User updateUser(UUID id, UserRequestDTO dto){
        
        User updatedUser = getUserById(id).orElseThrow();
        
        updatedUser.setName(dto.name());
        updatedUser.setEmail(dto.email());
        updatedUser.setPassword(dto.password());
        updatedUser.setType(dto.type());
        updatedUser.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(updatedUser);

    }

    public void deleteUser (UUID id) {
        userRepository.deleteById(id);
    }
}
