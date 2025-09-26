
package com.example.meal_tracker.controller;

import com.example.meal_tracker.domain.User;
import com.example.meal_tracker.dto.CreateUserRequest;
import com.example.meal_tracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 추가 import
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/users")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest request) {
        User savedUser = userService.createUser(request);
        return ResponseEntity.ok(savedUser);
    }

    // ID로 사용자 조회하는 메소드 추가
    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }
}
