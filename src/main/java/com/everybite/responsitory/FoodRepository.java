package com.everybite.repository;

import com.everybite.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    // 식사 타입별 음식 조회 (예: 아침, 점심 등)
    List<Food> findByMealTypeIgnoreCase(String mealType);

    // 음식 스타일별 (예: 한식/양식)
    List<Food> findByFoodStyleIgnoreCase(String foodStyle);

    // 특정 칼로리 이하 음식 조회
    @Query("SELECT f FROM Food f WHERE f.calories <= :maxCalories")
    List<Food> findLowCalorieFoods(double maxCalories);
}
