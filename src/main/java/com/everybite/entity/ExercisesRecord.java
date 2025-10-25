package com.everybite.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "exercise_record")
public class ExerciseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exercise_id;

    @Column(nullable = false)
    private LocalDate date; // 운동 날짜

    @Column(nullable = false)
    private String exercise_name; // 운동 이름

    @Column(nullable = false)
    private int duration_minutes; // 운동 시간(분)

    @Column(nullable = false)
    private double calories_burned; // 소모 칼로리(kcal)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExerciseType exercise_type; // 운동 종류(유산소, 근력 등)

    // 기본 생성자
    public ExerciseRecord() {}

    // 전체 필드 생성자
    public ExerciseRecord(Long exercise_id, LocalDate date, String exercise_name,
                          int duration_minutes, double calories_burned, ExerciseType exercise_type) {
        this.exercise_id = exercise_id;
        this.date = date;
        this.exercise_name = exercise_name;
        this.duration_minutes = duration_minutes;
        this.calories_burned = calories_burned;
        this.exercise_type = exercise_type;
    }

    // Getter / Setter
    public Long getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(Long exercise_id) {
        this.exercise_id = exercise_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

    public int getDuration_minutes() {
        return duration_minutes;
    }

    public void setDuration_minutes(int duration_minutes) {
        this.duration_minutes = duration_minutes;
    }

    public double getCalories_burned() {
        return calories_burned;
    }

    public void setCalories_burned(double calories_burned) {
        this.calories_burned = calories_burned;
    }

    public ExerciseType getExercise_type() {
        return exercise_type;
    }

    public void setExercise_type(ExerciseType exercise_type) {
        this.exercise_type = exercise_type;
    }

    // Optional: toString() (디버깅 시 유용)
    @Override
    public String toString() {
        return "ExerciseRecord{" +
                "exercise_id=" + exercise_id +
                ", date=" + date +
                ", exercise_name='" + exercise_name + '\'' +
                ", duration_minutes=" + duration_minutes +
                ", calories_burned=" + calories_burned +
                ", exercise_type=" + exercise_type +
                '}';
    }
}
