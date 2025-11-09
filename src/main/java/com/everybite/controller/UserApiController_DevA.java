package com.everybite.controller;

import com.everybite.dto.CreateUserRequest_DevA;
import com.everybite.dto.UserResponse_DevA;
import com.everybite.entity.User_DevA;
import com.everybite.service.UserService_DevA;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController_DevA {

    private final UserService_DevA userService;

    @PostMapping("/api/users")
    public ResponseEntity<UserResponse_DevA> createUser(@RequestBody CreateUserRequest_DevA request) {
        User_DevA savedUser = userService.createUser(request);
        return ResponseEntity.ok(new UserResponse_DevA(savedUser));
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserResponse_DevA> getUserById(@PathVariable("id") Long id) {
        User_DevA user = userService.findUserById(id);
        return ResponseEntity.ok(new UserResponse_DevA(user));
    }
}