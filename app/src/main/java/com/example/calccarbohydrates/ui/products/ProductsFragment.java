package com.example.calccarbohydrates.ui.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calccarbohydrates.R;
import com.example.calccarbohydrates.adapters.ProductsAdapter;
import com.example.calccarbohydrates.model.Products;

import java.util.ArrayList;

public class ProductsFragment extends Fragment {

    RecyclerView recyclerView;
    ProductsAdapter productsAdapter;
    private ArrayList<Products> products = new ArrayList<>();
    private ProductsViewModel productsViewModel;

    public static Fragment newInstance() {
        return new ProductsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                @Nullable ViewGroup container,
                @Nullable Bundle savedInstanceState
    ) {
        productsViewModel = new ViewModelProvider(this).get(ProductsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_products, container, false);
        recyclerView = root.findViewById(R.id.recycler_view);


        productsAdapter = new ProductsAdapter(getActivity(), this.products);
            recyclerView.setAdapter(productsAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);
            productsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {

                    products.add(new Products("Daniel"));
                    products.add(new Products( "Jhon"));
                    products.add(new Products( "Jane"));

                    productsAdapter = new ProductsAdapter(getActivity(), products);
                    recyclerView.setAdapter(productsAdapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(linearLayoutManager);}
            });
        return root;
    }
}