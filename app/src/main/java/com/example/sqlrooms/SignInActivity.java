package com.example.sqlrooms;

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

import com.example.sqlrooms.db.user.User;
import com.example.sqlrooms.db.user.UserViewModel;

public class SignInActivity extends AppCompatActivity {
    EditText et_email, et_password, et_confirmPassword;
    Button btn_signin;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_confirmPassword = findViewById(R.id.et_confirmPassword);
        btn_signin = findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(v -> signup());
    }
    public void signup() {
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        String confirmPassword = et_confirmPassword.getText().toString();
        if (email.isEmpty() || password.isEmpty()|| confirmPassword.isEmpty()){
            Toast.makeText(this, "Fill all the Fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!password.equals(confirmPassword)) {
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
            return;
        }
        User user = new User(0, email, password);
        userViewModel.insert(user);
        Toast.makeText(this, "Successfully Sign in", Toast.LENGTH_SHORT).show();
        finish();
    }
}