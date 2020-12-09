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
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.example.calccarbohydrates.R;
import com.example.calccarbohydrates.model.Product;

public class CreateProductFragment extends Fragment {

    EditText name;
    EditText carbohydrates;
    Button button;
    private ProductViewModel productsViewModel;

    public static Fragment newInstance() {
        return new CreateProductFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState
    ){
        View root = inflater.inflate(R.layout.fragment_create_product, container, false);
        productsViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        name = root.findViewById(R.id.name);
        carbohydrates = root.findViewById(R.id.carbohydrates);
        button = root.findViewById(R.id.create_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputCheck(name.getText().toString(),carbohydrates.getText().toString())){
                    Product product = new Product(name.getText().toString(),carbohydrates.getText().toString());
                    productsViewModel.insert(product);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, ListProductsFragment.newInstance(), ListProductsFragment.class.getSimpleName());
                    fragmentTransaction.commit();

                }
            }
        });
        return root;
    }

    private boolean inputCheck(String name, String carbohydrates){
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(carbohydrates));
    }

}
