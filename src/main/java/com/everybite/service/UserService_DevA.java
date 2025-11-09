package com.everybite.service;

import com.everybite.dto.CreateUserRequest_DevA;
import com.everybite.entity.User_DevA;
import com.everybite.responsitory.UserRepository_DevA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService_DevA {

    private final UserRepository_DevA userRepository;

    @Transactional
    public User_DevA createUser(CreateUserRequest_DevA request) {

        if (userRepository.existsByNickname(request.getNickname())) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }

        User_DevA newUser = new User_DevA();
        newUser.setNickname(request.getNickname());
        newUser.setHeight(request.getHeight());
        newUser.setWeight(request.getWeight());
        newUser.setBirthDate(request.getBirthDate());
        newUser.setGender(request.getGender());
        newUser.setDietGoal(request.getDietGoal());

        return userRepository.save(newUser);
    }

    @Transactional(readOnly = true)
    public User_DevA findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }
}