package com.example.food_project.controller;

import com.example.food_project.dto.RestaurantDTO;
import com.example.food_project.dto.RestaurantDetailDTO;
import com.example.food_project.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("")
    public ResponseEntity<List<RestaurantDTO>> getRestaurantFeature(){
        List<RestaurantDTO> restaurantEntity = restaurantService.getListRestaurant();
        return new ResponseEntity<>(restaurantEntity, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetailFood(@PathVariable("id") int id){
        RestaurantDetailDTO detailDTO = restaurantService.getDetailRestaurant(id);
        return new ResponseEntity<>(detailDTO, HttpStatus.OK);
    }
//
//    @GetMapping("/clear")
//    public ResponseEntity<?> clearCacheRestaurant(){
//        restaurantService.clearAllCache();
//        return new ResponseEntity<>("", HttpStatus.OK);
//    }
}

