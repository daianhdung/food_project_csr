package com.example.food_project.entity;


import javax.persistence.*;
import java.util.Set;

@Entity(name = "restaurant")
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image")
    private String image;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "restaurant")
    private Set<RestaurantReviewEntity> restaurantReview;

    @OneToMany(mappedBy = "restaurant")
    private Set<FoodEntity> foods;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RestaurantReviewEntity> getRestaurantReview() {
        return restaurantReview;
    }

    public void setRestaurantReview(Set<RestaurantReviewEntity> restaurantReview) {
        this.restaurantReview = restaurantReview;
    }

    public Set<FoodEntity> getFoods() {
        return foods;
    }

    public void setFoods(Set<FoodEntity> foods) {
        this.foods = foods;
    }
}
