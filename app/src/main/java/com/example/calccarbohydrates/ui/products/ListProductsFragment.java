package com.example.calccarbohydrates.ui.products;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calccarbohydrates.R;
import com.example.calccarbohydrates.adapters.ProductAdapter;
import com.example.calccarbohydrates.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListProductsFragment extends Fragment implements SearchView.OnQueryTextListener {

    RecyclerView recyclerView;
    ProductAdapter productsAdapter;
    ProductViewModel productsViewModel;
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
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment createProductFragment = new CreateProductFragment();
                activity.getSupportFragmentManager()
                        .beginTransaction().replace(R.id.fragment_container, createProductFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        recyclerView = root.findViewById(R.id.recycler_view);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        productsAdapter = new ProductAdapter(null, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        productsViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productsViewModel.getProductsList().observe((LifecycleOwner) getActivity(), new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable final List<Product> products) {
                productsAdapter.setProducts(products, productsViewModel);
            }
        });
        recyclerView.setAdapter(productsAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);


        item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                productsAdapter.setFilter(productsViewModel.getProductsList().getValue());
                return true;
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }
        });

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<Product> filteredModelList = filter(productsViewModel.getProductsList().getValue(), newText);
        productsAdapter.setFilter(filteredModelList);
        return true;
    }

    private List<Product> filter(List<Product> models, String query) {
        query = query.toLowerCase();

        final List<Product> filteredModelList = new ArrayList<>();
        for (Product model : models) {
            final String text = model.getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}