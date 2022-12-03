package com.example.food_project.services.impl;

import com.example.food_project.entity.FoodEntity;
import com.example.food_project.repository.FoodRepository;
import com.example.food_project.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImp implements FoodService {
    @Autowired
    FoodRepository foodRepository;
    @Override
    public List<FoodEntity> get6Food() {
        return foodRepository.get6Food();
    }
}
