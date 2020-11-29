package com.example.calccarbohydrates.ui.products;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.calccarbohydrates.R;
import com.example.calccarbohydrates.model.Products;

public class CreateProductsFragment extends Fragment {

    EditText name;
    EditText carbohydrates;
    Button button;
    private ProductsViewModel productsViewModel;

    public static Fragment newInstance() {
        return new CreateProductsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState
    ){
        View root = inflater.inflate(R.layout.fragment_create_product, container, false);
        productsViewModel = new ViewModelProvider(this).get(ProductsViewModel.class);
        name = root.findViewById(R.id.name);
        carbohydrates = root.findViewById(R.id.carbohydrates);
        button = root.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputCheck(name.getText().toString(),carbohydrates.getText().toString())){
                    Products products = new Products(name.getText().toString(),carbohydrates.getText().toString());
                    productsViewModel.insert(products);

                }
            }
        });
        return root;
    }

    private boolean inputCheck(String name, String carbohydrates){
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(carbohydrates));
    }

}
