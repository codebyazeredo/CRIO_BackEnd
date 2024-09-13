package com.crio.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crio.api.domain.user.User;
import com.crio.api.domain.user.UserRequestDTO;
import com.crio.api.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<User> create(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value =  "type", required = true) Integer type) {

        UserRequestDTO userRequestDTO = new UserRequestDTO(name, email, password, type);
        User newUser = this.userService.createUser(userRequestDTO);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {

        List<User> users = this.userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") UUID id) {

        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") UUID id,
            @RequestBody UserRequestDTO dto) {

        User updatedUser = this.userService.updateUser(id, dto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
