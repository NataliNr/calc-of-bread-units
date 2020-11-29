package com.example.calccarbohydrates.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.calccarbohydrates.dao.ProductsDao;
import com.example.calccarbohydrates.db.AppDatabase;
import com.example.calccarbohydrates.model.Products;

import java.util.List;

public class ProductsRepository {

    private  ProductsDao productsDao;
    private LiveData<List<Products>> productsList;



    public ProductsRepository(Application application) {
        AppDatabase db = AppDatabase.getAppDatabase(application);

        productsDao = db.productsDao();
        productsList = productsDao.getAllProducts();
    }

    public LiveData<List<Products>> getProductsList() {
        return productsList;
    }

    public void insert(Products products) {
        new InsertAsyncTask(productsDao).execute(products);
    }

    private static class InsertAsyncTask extends AsyncTask<Products, Void, Void> {

        private ProductsDao productsDao;

        InsertAsyncTask(ProductsDao productsDao) {
            this.productsDao = productsDao;
        }

        @Override
        protected Void doInBackground(final Products... params) {
            productsDao.insertProduct(params[0]);
            return null;
        }
    }

}
