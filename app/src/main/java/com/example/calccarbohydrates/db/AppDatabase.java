package com.example.calccarbohydrates.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.calccarbohydrates.dao.ProductsDao;
import com.example.calccarbohydrates.model.Products;

@Database(entities = {Products.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductsDao productsDao();
}
