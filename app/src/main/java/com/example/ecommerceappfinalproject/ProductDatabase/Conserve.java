package com.example.ecommerceappfinalproject.ProductDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "conserve_table")
public class Conserve {

    @PrimaryKey(autoGenerate = true)
    private int id;


    private String name;
    private float price;
    private String ingredients;
    private float weight;
    private float netWeight;
    private String type;
    private String imageUrl;

    public Conserve(String name, float price, String ingredients, float weight, float netWeight, String type, String imageUrl) {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.weight = weight;
        this.netWeight = netWeight;
        this.type = type;
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setNetWeight(float netWeight) {
        this.netWeight = netWeight;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public float getWeight() {
        return weight;
    }

    public float getNetWeight() {
        return netWeight;
    }
}
