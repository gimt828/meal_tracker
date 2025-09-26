package com.example.meal_tracker.service;

import com.example.meal_tracker.domain.User;
import com.example.meal_tracker.dto.CreateUserRequest;
import com.example.meal_tracker.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(CreateUserRequest request) {
        User newUser = new User();
        newUser.setNickname(request.getNickname());
        newUser.setHeight(request.getHeight());
        newUser.setWeight(request.getWeight());
        newUser.setBirthDate(request.getBirthDate());
        newUser.setGender(request.getGender());

        return userRepository.save(newUser);
    }

    @Transactional(readOnly = true) // 이 부분이 추가 및 수정되었습니다.
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }
}