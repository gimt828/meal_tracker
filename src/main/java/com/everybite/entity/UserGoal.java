package com.everybite.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_goal")
public class UserGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate start_date;

    @Column(nullable = false)
    private LocalDate end_date;

    @Column(nullable = false)
    private int target_calories;

    @Column(nullable = false)
    private double target_weight;

    @Column(nullable = false)
    private Long user_id;

    // 기본 생성자
    public UserGoal() {}

    // 전체 필드 생성자
    public UserGoal(Long id, LocalDate start_date, LocalDate end_date,
                    int target_calories, double target_weight, Long user_id) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.target_calories = target_calories;
        this.target_weight = target_weight;
        this.user_id = user_id;
    }

    // Getter / Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public int getTarget_calories() {
        return target_calories;
    }

    public void setTarget_calories(int target_calories) {
        this.target_calories = target_calories;
    }

    public double getTarget_weight() {
        return target_weight;
    }

    public void setTarget_weight(double target_weight) {
        this.target_weight = target_weight;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    // (디버깅/로그용)
    @Override
    public String toString() {
        return "UserGoal{" +
                "id=" + id +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", target_calories=" + target_calories +
                ", target_weight=" + target_weight +
                ", user_id=" + user_id +
                '}';
    }
}
