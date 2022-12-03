package com.example.food_project.repository;

import com.example.food_project.entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Integer> {

    @Query(value = "select f.* from food as f order by f.id DESC limit 6", nativeQuery = true)
    List<FoodEntity> get6Food();
}
