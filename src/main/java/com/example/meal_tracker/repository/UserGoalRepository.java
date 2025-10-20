package com.example.meal_tracker.repository; // 본인 패키지 경로 확인

import com.example.meal_tracker.domain.UserGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional; // import 추가!

// JpaRepository<엔티티이름, PK의타입>
public interface UserGoalRepository extends JpaRepository<UserGoal, Long> {

    // (참고) UserGoal이 User와 @OneToOne 관계이므로 
    // ID로 찾을 때 List가 아닌 Optional<UserGoal>을 사용합니다.
    Optional<UserGoal> findByUserId(Long userId);
    
}