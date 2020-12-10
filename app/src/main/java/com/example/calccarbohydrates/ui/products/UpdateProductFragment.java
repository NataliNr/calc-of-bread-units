package com.example.calccarbohydrates.ui.products;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.calccarbohydrates.R;
import com.example.calccarbohydrates.model.Product;

import java.io.Serializable;

public class UpdateProductFragment extends Fragment {

    private final static String PRODUCT = "Product";
    private Product product;
    EditText name;
    EditText carbohydrates;
    Button button;
    private ProductViewModel productsViewModel;

    public static Fragment newInstance(Product product) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(PRODUCT, (Serializable) product);
        UpdateProductFragment fragment = new UpdateProductFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState
    ){
        View root = inflater.inflate(R.layout.fragment_update_product, container, false);
        productsViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        name = root.findViewById(R.id.update_name);
        carbohydrates = root.findViewById(R.id.update_carbohydrates);
        button = root.findViewById(R.id.update_button);

        Bundle bundle = getArguments();
        product = (Product) bundle.getSerializable(PRODUCT);
        int id = product.getId();
        name.setText(product.getName());
        carbohydrates.setText(product.getCarbohydrates());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputCheck(name.getText().toString(),carbohydrates.getText().toString())) {
                    Product product = new Product(id,name.getText().toString(), carbohydrates.getText().toString());
                    productsViewModel.update(product);

                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment listProductsFragment = new ListProductsFragment();
                    activity.getSupportFragmentManager()
                            .beginTransaction().replace(R.id.fragment_container, listProductsFragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });


        return root;
    }

        private boolean inputCheck(String name, String carbohydrates){
            return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(carbohydrates));
        }

}
