package com.example.food_project.repository;

import com.example.food_project.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Integer> {

    @Query(value = "select r.* from restaurant as r order by r.id DESC limit 6", nativeQuery = true)
    List<RestaurantEntity> get6Restaurant();

    List<RestaurantEntity> findAll();

    Optional<RestaurantEntity> findById(int id);
}
