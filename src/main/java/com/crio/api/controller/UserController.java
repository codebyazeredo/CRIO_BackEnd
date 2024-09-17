package com.crio.api.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

    //QUERY
    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<User>> findUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.findUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<User>> findUserByType(@PathVariable Integer type) {
        List<User> users = userService.findUserByType(type);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/created-at")
    public ResponseEntity<List<User>> findByCreatedAtBetween(
            @RequestParam("createdAt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createdAt,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<User> users = userService.findByCreatedAtBetween(createdAt, end);
        return ResponseEntity.ok(users);
    }

    //CRUD
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<User> create(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "type", required = true) Integer type) {

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
