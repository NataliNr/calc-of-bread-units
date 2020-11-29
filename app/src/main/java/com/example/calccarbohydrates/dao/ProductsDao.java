package com.example.calccarbohydrates.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.calccarbohydrates.model.Products;

import java.util.List;

@Dao
public interface ProductsDao {
    @Query("SELECT * FROM Products")
//    List<Products>getAllProducts();
    LiveData<List<Products>> getAllProducts();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProduct(Products products);

//    @Query("SELECT * FROM products WHERE productName = :name")
//    List<Products> findProduct(String name);
//
//    @Query("DELETE FROM products WHERE productName = :name")
//    void deleteProduct(String name);
}
