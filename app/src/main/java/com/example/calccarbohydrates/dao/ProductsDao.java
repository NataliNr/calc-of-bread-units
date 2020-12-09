package com.example.calccarbohydrates.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.calccarbohydrates.model.Product;

import java.util.List;

@Dao
public interface ProductsDao {
    @Query("SELECT * FROM Product")
    LiveData<List<Product>> getAllProducts();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProduct(Product product);

//    @Query("SELECT * FROM products WHERE productName = :name")
//    List<Products> findProduct(String name);
//
    @Delete
    void deleteProduct(Product product);

//    @Query("UPDATE products SET text = :sText WHERE ID = :sId")
//    void update(int sID, String sText);
}
