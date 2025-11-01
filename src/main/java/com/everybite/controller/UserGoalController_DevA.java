package com.example.meal_tracker.controller; // 본인 패키지 경로 확인



import com.example.meal_tracker.domain.UserGoal;
import com.example.meal_tracker.dto.CreateUserGoalRequest;
import com.example.meal_tracker.dto.UserGoalResponse;
import com.example.meal_tracker.service.UserGoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController

@RequiredArgsConstructor

@RequestMapping("/api/goals") // 목표 관련 공통 주소

public class UserGoalController {



    private final UserGoalService userGoalService;



    // 사용자 목표 생성 API

    // POST /api/goals

    @PostMapping

    public ResponseEntity<UserGoalResponse> createUserGoal(

            @RequestBody CreateUserGoalRequest request) {



        UserGoal savedGoal = userGoalService.createUserGoal(request);



        return ResponseEntity.ok(new UserGoalResponse(savedGoal));

    }



    // 특정 사용자의 목표 조회 API

    // GET /api/goals/user/{userId}

    @GetMapping("/user/{userId}")

    public ResponseEntity<UserGoalResponse> getUserGoalByUserId(

            @PathVariable("userId") Long userId) {



        UserGoal goal = userGoalService.findUserGoalByUserId(userId);



        return ResponseEntity.ok(new UserGoalResponse(goal));

    }



    // 특정 사용자의 목표 수정 API

    // PUT /api/goals/user/{userId}

    @PutMapping("/user/{userId}")

    public ResponseEntity<UserGoalResponse> updateUserGoal(

            @PathVariable("userId") Long userId,

            @RequestBody CreateUserGoalRequest request) {



        UserGoal updatedGoal = userGoalService.updateUserGoal(userId, request);



        return ResponseEntity.ok(new UserGoalResponse(updatedGoal));

    }



    // --- (이 부분이 새로 추가되었습니다!) ---

    // 특정 사용자의 목표 삭제 API

    // DELETE /api/goals/user/{userId}

    @DeleteMapping("/user/{userId}")

    public ResponseEntity<Void> deleteUserGoal(

            @PathVariable("userId") Long userId) {

        userGoalService.deleteUserGoal(userId);

        return ResponseEntity.ok().build(); // 성공 응답 (내용 없음)

    }

}