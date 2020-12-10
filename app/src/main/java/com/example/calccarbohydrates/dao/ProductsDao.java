package com.example.calccarbohydrates.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.calccarbohydrates.model.Product;

import java.util.List;

@Dao
public interface ProductsDao {
    @Query("SELECT * FROM Product")
    LiveData<List<Product>> getAllProducts();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProduct(Product product);

    @Update
    void updateProduct(Product product);

    @Delete
    void deleteProduct(Product product);

}
