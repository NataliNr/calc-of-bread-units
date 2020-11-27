package com.example.calccarbohydrates.ui.products;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.calccarbohydrates.MainActivity;
import com.example.calccarbohydrates.R;
import com.example.calccarbohydrates.db.AppDatabase;
import com.example.calccarbohydrates.model.Products;

public class CreateProducts extends AppCompatActivity {

    EditText name;
    EditText carbohydrates;
    Button button;

    @Override
    protected void onCreate(@NonNull Bundle savedInstamceState){
        super.onCreate(savedInstamceState);
        setContentView(R.layout.create_products);
        name = findViewById(R.id.name);
        carbohydrates = findViewById(R.id.carbohydrates);
        button = findViewById(R.id.button);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                .allowMainThreadQueries()
                .build();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Products products = new Products(name.getText().toString(),carbohydrates.getText().toString());
                db.productsDao().insertAll(new Products(products.getName(),products.getCarbohydrates()));
                startActivity(new Intent(CreateProducts.this, MainActivity.class));
            }
        });
    }
}
