package com.example.calccarbohydrates.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.calccarbohydrates.dao.ProductsDao;
import com.example.calccarbohydrates.model.Products;

@Database(entities = {Products.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProductsDao productsDao();

    private static final String DB_NAME = "products_database";
    private static volatile AppDatabase INSTANCE;


         public static synchronized AppDatabase getAppDatabase(final Context context) {
             if(INSTANCE==null){

                 INSTANCE = create(context);
                    }
                    return INSTANCE;

         }
                private static AppDatabase create(final Context context) {
                return Room.databaseBuilder(
                                context,
                                AppDatabase.class,
                                DB_NAME).build();
                    }
    }
