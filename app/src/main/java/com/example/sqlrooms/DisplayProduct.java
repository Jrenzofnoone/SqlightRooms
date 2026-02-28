package com.example.sqlrooms;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sqlrooms.db.product.Product;

public class DisplayProduct extends AppCompatActivity {
    TextView tv_name, tv_price, tv_id;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv_name = findViewById(R.id.tv_name);
        tv_price = findViewById(R.id.tv_price);
        tv_id = findViewById(R.id.tv_id);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            product = getIntent().getSerializableExtra("product_data", Product.class);
        } else {
            product = (Product) getIntent().getSerializableExtra("product_data");
        }
        tv_id.setText(String.valueOf(product.getId()));
        tv_name.setText("Product Name: "+product.getProductName());
        tv_price.setText("Product Price" + String.valueOf(product.getProductPrice()));


    }
}