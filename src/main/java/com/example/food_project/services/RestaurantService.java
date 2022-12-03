package com.example.food_project.services;

import com.example.food_project.dto.RestaurantDTO;
import com.example.food_project.dto.RestaurantDetailDTO;

import java.util.List;

public interface RestaurantService {

    List<RestaurantDTO> getListRestaurant();

    RestaurantDetailDTO getDetailRestaurant(int id);

//    void clearAllCache();
}
