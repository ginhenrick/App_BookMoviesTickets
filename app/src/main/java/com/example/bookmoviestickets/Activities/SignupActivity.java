package com.example.bookmoviestickets.Activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmoviestickets.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextEmail, editTextPhone, editTextDate, editTextPassword, editTextRePassword;
    private TextView textViewUsernameStatus, textViewEmailStatus, textViewPhoneStatus, textViewPasswordStatus;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editSignupPassword);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextDate = findViewById(R.id.editTextDate);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        editTextRePassword = findViewById(R.id.editTextTextRePassword);
        Button buttonCreateAccount = findViewById(R.id.registerBtn);

        textViewUsernameStatus = findViewById(R.id.textViewUsernameStatus);
        textViewEmailStatus = findViewById(R.id.textViewEmailStatus);
        textViewPhoneStatus = findViewById(R.id.textViewPhoneStatus);
        textViewPasswordStatus = findViewById(R.id.textViewPasswordStatus);

        // Thêm TextWatcher cho EditText Username
        editTextUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String username = s.toString();
                if (username.isEmpty()) {
                    textViewUsernameStatus.setVisibility(View.GONE);
                } else {
                    // Kiểm tra username đã tồn tại hay chưa trên Firebase
                    mDatabase.child("users").orderByChild("username").equalTo(username).addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                        @Override
                        public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                textViewUsernameStatus.setVisibility(View.VISIBLE);
                                textViewUsernameStatus.setText("Tên người dùng đã tồn tại");
                                textViewUsernameStatus.setTextColor(getResources().getColor(R.color.red));
                            } else {
                                textViewUsernameStatus.setVisibility(View.VISIBLE);
                                textViewUsernameStatus.setText("Tên người dùng hợp lệ");
                                textViewUsernameStatus.setTextColor(getResources().getColor(R.color.green));
                            }
                        }

                        @Override
                        public void onCancelled(com.google.firebase.database.DatabaseError databaseError) {
                            // Xử lý lỗi
                        }
                    });
                }
            }
        });

        // Thêm TextWatcher cho EditText Email
        editTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String email = s.toString();
                if (email.isEmpty()) {
                    textViewEmailStatus.setVisibility(View.GONE);
                } else {
                    // Kiểm tra email đã tồn tại hay chưa trên Firebase
                    mDatabase.child("users").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                        @Override
                        public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                textViewEmailStatus.setVisibility(View.VISIBLE);
                                textViewEmailStatus.setText("Email đã tồn tại");
                                textViewEmailStatus.setTextColor(getResources().getColor(R.color.red));
                            } else {
                                textViewEmailStatus.setVisibility(View.VISIBLE);
                                textViewEmailStatus.setText("Email hợp lệ");
                                textViewEmailStatus.setTextColor(getResources().getColor(R.color.green));
                            }
                        }

                        @Override
                        public void onCancelled(com.google.firebase.database.DatabaseError databaseError) {
                            // Xử lý lỗi
                        }
                    });
                }
            }
        });

        // Thêm TextWatcher cho EditText Phone
        editTextPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String phone = s.toString();
                if (phone.isEmpty()) {
                    textViewPhoneStatus.setVisibility(View.GONE);
                } else {
                    // Kiểm tra phone đã tồn tại hay chưa trên Firebase
                    mDatabase.child("users").orderByChild("phone").equalTo(phone).addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                        @Override
                        public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                textViewPhoneStatus.setVisibility(View.VISIBLE);
                                textViewPhoneStatus.setText("Số điện thoại đã tồn tại");
                                textViewPhoneStatus.setTextColor(getResources().getColor(R.color.red));
                            } else {
                                textViewPhoneStatus.setVisibility(View.VISIBLE);
                                textViewPhoneStatus.setText("Số điện thoại hợp lệ");
                                textViewPhoneStatus.setTextColor(getResources().getColor(R.color.green));
                            }
                        }

                        @Override
                        public void onCancelled(com.google.firebase.database.DatabaseError databaseError) {
                            // Xử lý lỗi
                        }
                    });
                }
            }
        });

        // Thêm TextWatcher cho EditText Password
        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String password = s.toString();
                if (password.isEmpty()) {
                    textViewPasswordStatus.setVisibility(View.GONE);
                } else {
                    int strength = checkPasswordStrength(password);
                    if (strength == 1) {
                        textViewPasswordStatus.setVisibility(View.VISIBLE);
                        textViewPasswordStatus.setText("Mật khẩu yếu");
                        textViewPasswordStatus.setTextColor(getResources().getColor(R.color.red));
                    } else if (strength == 2) {
                        textViewPasswordStatus.setVisibility(View.VISIBLE);
                        textViewPasswordStatus.setText("Mật khẩu trung bình");
                        textViewPasswordStatus.setTextColor(getResources().getColor(R.color.orange));
                    } else if (strength == 3) {
                        textViewPasswordStatus.setVisibility(View.VISIBLE);
                        textViewPasswordStatus.setText("Mật khẩu mạnh");
                        textViewPasswordStatus.setTextColor(getResources().getColor(R.color.green));
                    }
                }
            }
        });

        buttonCreateAccount.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString();
            String email = editTextEmail.getText().toString();
            String phone = editTextPhone.getText().toString();
            String date = editTextDate.getText().toString();
            String password = editTextPassword.getText().toString();
            String rePassword = editTextRePassword.getText().toString();

            if (username.isEmpty() || email.isEmpty() || phone.isEmpty() || date.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
                new AlertDialog.Builder(SignupActivity.this)
                        .setTitle("Lỗi")
                        .setMessage("Vui lòng điền đầy đủ thông tin!")
                        .setPositiveButton("OK", null)
                        .show();
            } else if (!password.equals(rePassword)) {
                new AlertDialog.Builder(SignupActivity.this)
                        .setTitle("Lỗi")
                        .setMessage("Mật khẩu không khớp!")
                        .setPositiveButton("OK", null)
                        .show();
            } else {
                User user = new User(username, password, email, phone, date);

                String userId = mDatabase.child("users").push().getKey();
                assert userId != null;
                mDatabase.child("users").child(userId).setValue(user)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(SignupActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(SignupActivity.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
                        });
            }
        });

        ImageView backImgSignup = findViewById(R.id.backImgSignup);
        backImgSignup.setOnClickListener(v -> finish());
    }


    // Hàm kiểm tra độ mạnh của mật khẩu (ví dụ đơn giản)
    private int checkPasswordStrength(String password) {
        if (password.length() < 8) {
            return 1; // Yếu
        } else if (password.matches(".*[a-z].*[A-Z].*") && password.matches(".*[0-9].*")) {
            return 3; // Mạnh
        } else if (password.matches(".*[a-z].*[A-Z].*") || password.matches(".*[0-9].*")) {
            return 2; // Trung bình
        } else {
            return 1; // Yếu
        }
    }
}