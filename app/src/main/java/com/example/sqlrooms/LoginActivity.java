package com.example.sqlrooms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sqlrooms.db.user.User;
import com.example.sqlrooms.db.user.UserViewModel;

public class LoginActivity extends AppCompatActivity {
    EditText et_email, et_password;
    TextView tv_signup;
    Button btn_login;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        tv_signup = findViewById(R.id.tv_signup);
        btn_login= findViewById(R.id.btn_login);
        tv_signup.setOnClickListener(v -> {
            Intent i = new Intent(this, SignInActivity.class);
            startActivity(i);
        });
        btn_login.setOnClickListener(v -> login());
    }
    public void login() {
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        LiveData<User> liveData = userViewModel.login(email, password);
        liveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                liveData.removeObserver(this);
                if (user != null){
                    SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
                    prefs.edit().putInt("userId",user.getId()).apply();
                    Toast.makeText(LoginActivity.this, "Succesfully log in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}