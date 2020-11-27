package com.example.calccarbohydrates.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.calccarbohydrates.model.Products;

import java.util.List;

@Dao
public interface ProductsDao {
    @Query("SELECT * FROM Products")
    List<Products>getAllProducts();

    @Insert
    void insertAll(Products...products);
}
