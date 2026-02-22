package com.example.sqlrooms;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlrooms.db.ProductViewModel;

public class MainActivity extends AppCompatActivity {

    private Button btn_add;

    private RecyclerView rv_products;

    private product_rvAdapter rv_adapter;

    private ProductViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btn_add = findViewById(R.id.btn_add);
        rv_products = findViewById(R.id.rv_products);
        viewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        rv_adapter = new product_rvAdapter();

        rv_products.setLayoutManager(new LinearLayoutManager(this));
        rv_products.setAdapter(rv_adapter);

        viewModel.getAllProducts().observe(this, products -> {
            rv_adapter.setProducts(products);
        });

        btn_add.setOnClickListener(v -> {
            Intent i = new Intent(this, AddingProduct.class);
            startActivity(i);
        });
    }
}