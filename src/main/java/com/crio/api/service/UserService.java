package com.crio.api.service;

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

        userRepository.save(newUser);
        return newUser;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(UUID id){
        return userRepository.findById(id);
    }

    public User updateUser(UUID id, User user){
        
        User updatedUser = userRepository.findById(id).orElseThrow();
        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setType(user.getType());
        updatedUser.setCreatedAt(user.getCreatedAt());
        updatedUser.setUpdatedAt(user.getUpdatedAt());
        
        return userRepository.save(updatedUser);
    }

    public void deleteUser (UUID id) {
        userRepository.deleteById(id);
    }
}
