package com.example.calccarbohydrates.ui.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.calccarbohydrates.R;

public class UpdateProductFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState
    ){
        View root = inflater.inflate(R.layout.fragment_update_product, container, false);
        return root;
    }

}
