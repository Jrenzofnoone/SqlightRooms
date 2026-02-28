package com.example.sqlrooms;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.sqlrooms.db.product.Product;
import com.example.sqlrooms.db.product.ProductViewModel;

public class AddingProduct extends AppCompatActivity {
    private EditText et_name, et_price;
    private Button btn_add;
    private ProductViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adding_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        et_name = findViewById(R.id.et_name);
        et_price = findViewById(R.id.et_price);
        btn_add = findViewById(R.id.btn_add);

        viewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        btn_add.setOnClickListener(v -> addProduct());
    }

    private void addProduct(){
        String productName = et_name.getText().toString().trim();
        String productPrice = et_price.getText().toString().trim();

        if (productName.isEmpty() || productPrice.isEmpty()){
            Toast.makeText(this, "Fill all field", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences pref = getSharedPreferences("session", MODE_PRIVATE);
        int userId = pref.getInt("userId", -1);

        int price = Integer.parseInt(productPrice);
        Product user = new Product(0, productName, price, userId);
        viewModel.insert(user);
        Toast.makeText(this, "Succesfully Added", Toast.LENGTH_SHORT).show();
        finish();
    }
}