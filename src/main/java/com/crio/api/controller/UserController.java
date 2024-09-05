package com.crio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crio.api.domain.user.User;
import com.crio.api.domain.user.UserRequestDTO;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<User> create(@RequestParam("name") String name,
                                       @RequestParam("email") String email,
                                       @RequestParam("password") String password,
                                       @RequestParam("type") Integer type) {

        UserRequestDTO userRequestDTO = new UserRequestDTO(name, email, password, type);
        User newUser = this.userService.createUser(userRequestDTO);
        return ResponseEntity.ok(newUser);
    }
}
