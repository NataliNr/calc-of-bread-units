package com.example.calccarbohydrates.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.calccarbohydrates.dao.ProductsDao;
import com.example.calccarbohydrates.db.AppDatabase;
import com.example.calccarbohydrates.model.Product;

import java.util.List;

public class ProductRepository {

    private final ProductsDao productsDao;
    private final LiveData<List<Product>> productsList;



    public ProductRepository(Application application) {
        AppDatabase db = AppDatabase.getAppDatabase(application);

        productsDao = db.productsDao();
        productsList = productsDao.getAllProducts();
    }

    public LiveData<List<Product>> getProductsList() {
        return productsList;
    }

    public void insert(Product product) {
        new InsertAsyncTask(productsDao).execute(product);
    }

    public void delete(Product product) {
        new DeleteAsyncTask(productsDao).execute(product);
    }

    private static class InsertAsyncTask extends AsyncTask<Product, Void, Void> {

        private final ProductsDao productsDao;

        InsertAsyncTask(ProductsDao productsDao) {
            this.productsDao = productsDao;
        }

        @Override
        protected Void doInBackground(final Product... params) {
            productsDao.insertProduct(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Product, Void, Void> {

        private final ProductsDao productsDao;

        DeleteAsyncTask(ProductsDao productsDao) {
            this.productsDao = productsDao;
        }

        @Override
        protected Void doInBackground(final Product... params) {
            productsDao.deleteProduct(params[0]);
            return null;
        }
    }

}
