package com.everybite.repository;

import com.everybite.entity.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
    // 스프링에게 이 인터페이스가 데이터 접근 계층임을 명시.
    // 런타임 시 JPA가 이 인터페이스의 구현체를 자동 생성함.
    
public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Long> {
    // JpaRepository<엔티티, PK타입>을 상속받아 기본 CRUD 메서드 제공

    //특정 날짜의 운동 기록 조회
    List<ExerciseRecord> findByDate(LocalDate date);
    // 날짜 범위 내의 운동 기록 조회 (start ~ end)
    List<ExerciseRecord> findByDateBetween(LocalDate start, LocalDate end);

}
