package com.example.ecommerceappfinalproject.ProductDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="pasta_table")
public class Pasta {

    public Pasta(){}

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String Name;
    private boolean integrale;
    private float price;
    private String provenance;
    private String ingredients;
    private String imageUrl;

    public Pasta(String name, boolean integrale, float price, String provenance, String ingredients, String imageUrl) {
        Name = name;
        this.integrale = integrale;
        this.price = price;
        this.provenance = provenance;
        this.ingredients = ingredients;
        this.imageUrl = imageUrl;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setIntegrale(boolean integrale) {
        this.integrale = integrale;
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

    public boolean isIntegrale() {
        return integrale;
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
}
