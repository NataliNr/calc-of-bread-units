package com.example.calccarbohydrates.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@Entity(tableName = "product")
public class Product implements Serializable {

    public Product(int id, String name, String carbohydrates) {
        this.id = id;
        this.name = name;
        this.carbohydrates = carbohydrates;
    }
    @Ignore
    public Product(String name, String carbohydrates) {
        this.name = name;
        this.carbohydrates = carbohydrates;
    }
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("carbohydrates")
    @ColumnInfo(name = "carbohydrates")
    private String carbohydrates;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(String carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

}