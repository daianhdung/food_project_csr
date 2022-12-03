package com.example.food_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "food")
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "image")
    private String image;
    @Column(name = "price")
    private int price;

    @ManyToOne()
    @JoinColumn(name = "id_category")
    private CategoryEntity category;

    @ManyToOne()
    @JoinColumn(name = "id_restaurant")
    private RestaurantEntity restaurant;


    @OneToOne(mappedBy = "food")
    private FoodDetailEntity foodDetail;

    @OneToMany(mappedBy = "food")
    private Set<FoodReviewEntity> foodReview;

    @OneToMany(mappedBy = "food")
    private Set<FoodAddonEntity> foodAddon;

    @OneToMany(mappedBy = "food")
    private Set<FoodMaterialEntity> foodMaterials;

    @OneToMany(mappedBy = "food")
    private Set<FoodOrderEntity> foodOrder;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public FoodDetailEntity getFoodDetail() {
        return foodDetail;
    }

    public void setFoodDetail(FoodDetailEntity foodDetail) {
        this.foodDetail = foodDetail;
    }

    public Set<FoodReviewEntity> getFoodReview() {
        return foodReview;
    }

    public void setFoodReview(Set<FoodReviewEntity> foodReview) {
        this.foodReview = foodReview;
    }

    public Set<FoodAddonEntity> getFoodAddon() {
        return foodAddon;
    }

    public void setFoodAddon(Set<FoodAddonEntity> foodAddon) {
        this.foodAddon = foodAddon;
    }

    public Set<FoodMaterialEntity> getFoodMaterials() {
        return foodMaterials;
    }

    public void setFoodMaterials(Set<FoodMaterialEntity> foodMaterials) {
        this.foodMaterials = foodMaterials;
    }

    public Set<FoodOrderEntity> getFoodOrder() {
        return foodOrder;
    }

    public void setFoodOrder(Set<FoodOrderEntity> foodOrder) {
        this.foodOrder = foodOrder;
    }
}
