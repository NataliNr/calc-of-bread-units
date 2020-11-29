package com.example.calccarbohydrates.ui.products;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calccarbohydrates.R;
import com.example.calccarbohydrates.adapters.ProductsAdapter;
import com.example.calccarbohydrates.model.Products;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListProductsFragment extends Fragment {

    RecyclerView recyclerView;
    ProductsAdapter productsAdapter;
    ProductsViewModel productsViewModel;
    FloatingActionButton fab;

    public static Fragment newInstance() {
        return new ListProductsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                @Nullable ViewGroup container,
                @Nullable Bundle savedInstanceState
    ) {
        View root = inflater.inflate(R.layout.fragment_list_products, container, false);
        fab = root.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, CreateProductsFragment.newInstance(), CreateProductsFragment.class.getSimpleName());
                fragmentTransaction.commit();
            }
        });

        recyclerView = root.findViewById(R.id.recycler_view);
        productsAdapter = new ProductsAdapter(null,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(productsAdapter);

        productsViewModel = new ViewModelProvider(this).get(ProductsViewModel.class);
        productsViewModel.getProductsList().observe((LifecycleOwner) getActivity(), new Observer<List<Products>>() {
            @Override
            public void onChanged(@Nullable final List<Products> products) {
                productsAdapter.setProducts(products);
            }
        });
        return root;
    }
}