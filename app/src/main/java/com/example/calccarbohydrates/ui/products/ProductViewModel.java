package com.example.calccarbohydrates.ui.products;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.calccarbohydrates.model.Product;
import com.example.calccarbohydrates.repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private final ProductRepository productsRepository;
    private final LiveData<List<Product>> productsList;

    public ProductViewModel(Application application) {
        super(application);
        productsRepository = new ProductRepository(application);
        productsList = productsRepository.getProductsList();

    }

    public LiveData<List<Product>> getProductsList() {
        return productsList;
    }

    public void insert(Product products) {
        productsRepository.insert(products);
    }

    public void update(Product product) {
        productsRepository.update(product);
    }

    public void delete(Product product) {
        productsRepository.delete(product);
    }

}