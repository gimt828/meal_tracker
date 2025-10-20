package com.example.meal_tracker.controller;

import com.example.meal_tracker.domain.User;
import com.example.meal_tracker.dto.CreateUserRequest;
import com.example.meal_tracker.dto.UserResponse; // UserResponse DTO Import (추가)
import com.example.meal_tracker.service.UserService;
import lombok.RequiredArgsConstructor; // 롬복 Import (추가)
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor // final 필드의 생성자를 자동으로 만들어줍니다.
public class UserApiController {

    private final UserService userService; // final로 변경

    // @RequiredArgsConstructor가 생성자를 대신합니다.
    // public UserApiController(UserService userService) { ... }

    @PostMapping("/api/users")
    // 반환 타입을 User에서 UserResponse로 변경
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        User savedUser = userService.createUser(request);
        
        // UserResponse DTO로 변환하여 반환
        return ResponseEntity.ok(new UserResponse(savedUser));
    }

    @GetMapping("/api/users/{id}")
    // 반환 타입을 User에서 UserResponse로 변경
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        
        // UserResponse DTO로 변환하여 반환
        return ResponseEntity.ok(new UserResponse(user));
    }
}