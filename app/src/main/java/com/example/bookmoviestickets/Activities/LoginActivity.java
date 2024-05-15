package com.example.bookmoviestickets.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmoviestickets.R;

public class LoginActivity extends AppCompatActivity {
    private EditText userEdt, passEdt;
    private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        userEdt=findViewById(R.id.editTextText);
        passEdt=findViewById(R.id.editTextPassword);
        loginBtn=findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(v -> {
            if (userEdt.getText().toString().isEmpty() || passEdt.getText().toString().isEmpty()) {
                Toast.makeText(LoginActivity.this,"Vui lòng nhập tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
            } else if (userEdt.getText().toString().equals("test") && passEdt.getText().toString().equals("test")) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else {
                Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu của bạn không chính xác", Toast.LENGTH_SHORT).show();
            }
        });
    }
}