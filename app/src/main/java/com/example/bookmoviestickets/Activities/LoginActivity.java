package com.example.bookmoviestickets.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmoviestickets.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {
    private EditText userEdt, passEdt;
    private Button loginBtn;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDatabase = FirebaseDatabase.getInstance().getReference(); // Khởi tạo Firebase Database

        initView();
    }

    private void initView() {
        userEdt = findViewById(R.id.editTextText);
        passEdt = findViewById(R.id.editTextPassword);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(v -> {
            String input = userEdt.getText().toString();
            String password = passEdt.getText().toString();

            if (input.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Vui lòng nhập email hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
            } else {
                attemptLogin(input, password);
            }
        });
        ImageView backImgLogin = findViewById(R.id.backImgLogin);
        backImgLogin.setOnClickListener(v -> finish());
    }

    private void attemptLogin(String input, String password) {
        executorService.execute(() -> {
            mDatabase.child("users").orderByChild("username").equalTo(input) // Truy vấn dữ liệu từ Firebase
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                    User user = userSnapshot.getValue(User.class); // Lấy dữ liệu User từ Firebase
                                    if (password.equals(user.password)) {
                                        // Đăng nhập thành công
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    } else {
                                        // Mật khẩu sai
                                        runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Mật khẩu sai!", Toast.LENGTH_SHORT).show());
                                    }
                                }
                            } else {
                                // Tên tài khoản hoặc email không tồn tại
                                runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Tên tài khoản hoặc email không tồn tại!", Toast.LENGTH_SHORT).show());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Xử lý lỗi
                        }
                    });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }

    public void goToSignup(View view) {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}