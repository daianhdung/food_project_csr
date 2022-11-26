package com.example.food_project.entity;

import com.example.food_project.entity.id.FoodMaterialId;

import javax.persistence.*;

@Entity(name = "food_material")
@IdClass(FoodMaterialId.class)
public class FoodMaterialEntity {

    @Id
    private int id_food;
    @Id
    private int id_meterial;

    @ManyToOne
    @JoinColumn(name = "id_food", insertable = false, updatable = false)
    private FoodEntity food;

    @ManyToOne
    @JoinColumn(name = "id_material", insertable = false, updatable = false)
    private MaterialEntity material;

    public int getId_food() {
        return id_food;
    }

    public void setId_food(int id_food) {
        this.id_food = id_food;
    }

    public int getId_meterial() {
        return id_meterial;
    }

    public void setId_meterial(int id_meterial) {
        this.id_meterial = id_meterial;
    }

    public FoodEntity getFood() {
        return food;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }

    public MaterialEntity getMaterial() {
        return material;
    }

    public void setMaterial(MaterialEntity material) {
        this.material = material;
    }
}
