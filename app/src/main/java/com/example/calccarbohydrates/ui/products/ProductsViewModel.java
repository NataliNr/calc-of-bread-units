package com.example.calccarbohydrates.ui.products;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.calccarbohydrates.dao.ProductsDao;
import com.example.calccarbohydrates.db.AppDatabase;
import com.example.calccarbohydrates.model.Products;
import com.example.calccarbohydrates.repository.ProductsRepository;

import java.util.List;

public class ProductsViewModel extends AndroidViewModel {

    private ProductsRepository productsRepository;
    private LiveData<List<Products>> productsList;

    public ProductsViewModel(Application application) {
        super(application);
        productsRepository = new ProductsRepository(application);
        productsList = productsRepository.getProductsList();

    }

    public LiveData<List<Products>> getProductsList() {
        return productsList;
    }

    public void insert(Products products) {
        productsRepository.insert(products);
    }

}