package com.everybite.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_preference")
@Getter
@Setter
@NoArgsConstructor
public class UserPreference_DevA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User_DevA user; // User_DevA 로 변경

    private String preferredCuisine;
    private String preferredFood;
}