package com.example.ecommerceappfinalproject.ProductDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "special_table")
public class Special {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String Name;
    private String type;
    private float price;
    private String provenance;
    private String ingredients;
    private String imageUrl;


    public Special(){}

    public Special(String name, String type, float price, String provenance, String ingredients, String position) {
        Name = name;
        this.type = type;
        this.price = price;
        this.provenance = provenance;
        this.ingredients = ingredients;
        this.imageUrl = position;
    }





    public void setImageUrl(String position) {
        this.imageUrl = position;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    public String getProvenance() {
        return provenance;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
