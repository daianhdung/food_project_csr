package com.example.food_project.services.impl;

import com.example.food_project.dto.RestaurantDTO;
import com.example.food_project.dto.RestaurantDetailDTO;
import com.example.food_project.entity.FoodEntity;
import com.example.food_project.entity.RestaurantEntity;
import com.example.food_project.entity.RestaurantReviewEntity;
import com.example.food_project.model.FoodModel;
import com.example.food_project.repository.RestaurantRepository;
import com.example.food_project.services.RestaurantService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class RestaurantServiceImp implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public List<RestaurantDTO> getListRestaurant() {
        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        List<RestaurantEntity> restaurantEntities = restaurantRepository.findAll();
        for (RestaurantEntity data: restaurantEntities) {
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setTitle(data.getName());
            restaurantDTO.setImage(data.getImage());
            float sumRate = 0;
            float avgRate = 0;
            for(RestaurantReviewEntity dataReview : data.getRestaurantReview()){
                sumRate += dataReview.getRate();
            }
            if(data.getRestaurantReview().size() > 0){
                avgRate = sumRate / data.getRestaurantReview().size();
            }
            restaurantDTO.setAvgRate(avgRate);
            restaurantDTOS.add(restaurantDTO);
        }

        return restaurantDTOS;
    }

    @Override
//    @Cacheable("detail_restaurant")
    public RestaurantDetailDTO getDetailRestaurant(int id) {
        String key = "res" + id;
        Gson gson = new Gson();
        RestaurantDetailDTO restaurantDetailDTO = new RestaurantDetailDTO();
        if(redisTemplate.hasKey(key)){
            //Key có tồn tại
            String data = (String) redisTemplate.opsForValue().get(key);
            restaurantDetailDTO = gson.fromJson(data, RestaurantDetailDTO.class);
        }else {
            //Key ko tồn tại
            Optional<RestaurantEntity> restaurantEntity = restaurantRepository.findById(id);
            if(restaurantEntity.isPresent()){
                restaurantDetailDTO.setTitle(restaurantEntity.get().getName());
                restaurantDetailDTO.setTitle(restaurantEntity.get().getImage());
                float sumRate = 0;
                float avgRate = 0;
                for(RestaurantReviewEntity dataReview : restaurantEntity.get().getRestaurantReview()){
                    sumRate += dataReview.getRate();
                }
                if(restaurantEntity.get().getRestaurantReview().size() > 0){
                    avgRate = sumRate / restaurantEntity.get().getRestaurantReview().size();
                }
                restaurantDetailDTO.setAvgRate(avgRate);
                List<FoodModel> foodModels = new ArrayList<>();
                for(FoodEntity foodEntity : restaurantEntity.get().getFoods()){
                    FoodModel foodModel = new FoodModel();
                    foodModel.setId(foodEntity.getId());
                    foodModel.setName(foodEntity.getName());
                    foodModel.setImage(foodEntity.getImage());
                    foodModel.setPrice(foodEntity.getPrice());
                    foodModels.add(foodModel);
                }
                restaurantDetailDTO.setFoods(foodModels);
            }
            String json = gson.toJson(restaurantDetailDTO);
            redisTemplate.opsForValue().set(key, json);
        }
        return restaurantDetailDTO;
    }

//    @Override
//    @CacheEvict(value = "detail_restaurant", allEntries = true)
//    public void clearAllCache() {
//    }
}
