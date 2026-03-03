package com.example.sqlrooms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlrooms.db.product.ProductViewModel;

public class MainActivity extends AppCompatActivity {

    private Button btn_add;
    private RecyclerView rv_products;
    private product_rvAdapter rv_adapter;
    private ProductViewModel viewModel;
    private SearchView sv_searchprod;

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
        sv_searchprod = findViewById(R.id.sv_searchprod);
        rv_products = findViewById(R.id.rv_products);
        viewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        rv_adapter = new product_rvAdapter();

        rv_products.setLayoutManager(new LinearLayoutManager(this));
        rv_products.setAdapter(rv_adapter);

        SharedPreferences pref = getSharedPreferences("session", MODE_PRIVATE);
        int userId = pref.getInt("userId", -1);

        loadProducts(userId, "");

        sv_searchprod.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loadProducts(userId, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                loadProducts(userId, newText);
                return true;
            }
        });
        btn_add.setOnClickListener(v -> {
            Intent i = new Intent(this, AddingProduct.class);
            startActivity(i);
        });
    }

    private void loadProducts(int userId, String query) {
        String searchQuery = "%" + query + "%";
        viewModel.getAllProducts(userId, searchQuery).observe(this, products -> {
            rv_adapter.setProducts(products);
        });
    }
}
