package com.example.food_project.entity.id;

import java.io.Serializable;

public class FoodMaterialId implements Serializable {

    private int id_food;
    private int id_meterial;

    public FoodMaterialId(int id_food, int id_meterial) {
        this.id_food = id_food;
        this.id_meterial = id_meterial;
    }

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
}
