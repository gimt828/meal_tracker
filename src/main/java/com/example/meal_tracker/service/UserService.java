package com.example.meal_tracker.service;

import com.example.meal_tracker.domain.User;
import com.example.meal_tracker.dto.CreateUserRequest;
import com.example.meal_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor; // 롬복 Import 추가
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // final 필드의 생성자를 자동으로 만들어줍니다.
public class UserService {

    private final UserRepository userRepository; // final로 변경

    // @RequiredArgsConstructor가 생성자를 대신합니다.
    // public UserService(UserRepository userRepository) { ... }

    @Transactional
    public User createUser(CreateUserRequest request) {
        
        // 1. 닉네임 중복 검사 로직 (추가)
        if (userRepository.existsByNickname(request.getNickname())) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }

        // 2. DTO -> Entity 변환
        User newUser = new User();
        newUser.setNickname(request.getNickname());
        newUser.setHeight(request.getHeight());
        newUser.setWeight(request.getWeight());
        newUser.setBirthDate(request.getBirthDate());
        newUser.setGender(request.getGender());
        newUser.setDietGoal(request.getDietGoal()); // dietGoal 저장 (추가)

        // 3. DB에 저장
        return userRepository.save(newUser);
    }

    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }
}