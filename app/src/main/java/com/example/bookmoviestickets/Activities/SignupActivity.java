package com.example.bookmoviestickets.Activities;

import android.annotation.SuppressLint;
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
    private Button buttonCreateAccount;
    private TextView textViewUsernameStatus, textViewEmailStatus, textViewPhoneStatus, textViewPasswordStatus;
    private DatabaseReference mDatabase;

    @SuppressLint("MissingInflatedId")
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
        buttonCreateAccount = findViewById(R.id.registerBtn); // Sử dụng ID registerBtn

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
                                textViewUsernameStatus.setText("Username already exists");
                                textViewUsernameStatus.setTextColor(getResources().getColor(R.color.red));
                                textViewUsernameStatus.setTextAppearance(SignupActivity.this, R.style.ItalicTextView);
                            } else {
                                textViewUsernameStatus.setVisibility(View.VISIBLE);
                                textViewUsernameStatus.setText("Username success");
                                textViewUsernameStatus.setTextColor(getResources().getColor(R.color.green));
                                textViewUsernameStatus.setTextAppearance(SignupActivity.this, R.style.ItalicTextView);
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
                // Không cần xử lý
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Không cần xử lý
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
                                textViewEmailStatus.setText("Email already exists");
                                textViewEmailStatus.setTextColor(getResources().getColor(R.color.red));
                                textViewEmailStatus.setTextAppearance(SignupActivity.this, R.style.ItalicTextView);
                            } else {
                                textViewEmailStatus.setVisibility(View.VISIBLE);
                                textViewEmailStatus.setText("Email success");
                                textViewEmailStatus.setTextColor(getResources().getColor(R.color.green));
                                textViewEmailStatus.setTextAppearance(SignupActivity.this, R.style.ItalicTextView);
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
                // Không cần xử lý
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Không cần xử lý
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
                                textViewPhoneStatus.setText("Phone already exists");
                                textViewPhoneStatus.setTextColor(getResources().getColor(R.color.red));
                                textViewPhoneStatus.setTextAppearance(SignupActivity.this, R.style.ItalicTextView);
                            } else {
                                textViewPhoneStatus.setVisibility(View.VISIBLE);
                                textViewPhoneStatus.setText("Phone success");
                                textViewPhoneStatus.setTextColor(getResources().getColor(R.color.green));
                                textViewPhoneStatus.setTextAppearance(SignupActivity.this, R.style.ItalicTextView);
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
                        textViewPasswordStatus.setText("Weak password");
                        textViewPasswordStatus.setTextColor(getResources().getColor(R.color.red));
                        textViewPasswordStatus.setTextAppearance(SignupActivity.this, R.style.ItalicTextView);
                    } else if (strength == 2) {
                        textViewPasswordStatus.setVisibility(View.VISIBLE);
                        textViewPasswordStatus.setText("Medium Password");
                        textViewPasswordStatus.setTextColor(getResources().getColor(R.color.orange));
                        textViewPasswordStatus.setTextAppearance(SignupActivity.this, R.style.ItalicTextView);
                    } else if (strength == 3) {
                        textViewPasswordStatus.setVisibility(View.VISIBLE);
                        textViewPasswordStatus.setText("Strong Password");
                        textViewPasswordStatus.setTextColor(getResources().getColor(R.color.green));
                        textViewPasswordStatus.setTextAppearance(SignupActivity.this, R.style.ItalicTextView);
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
                        .setTitle("Error")
                        .setMessage("Please complete all information!")
                        .setPositiveButton("OK", null)
                        .show();
            } else if (!password.equals(rePassword)) {
                new AlertDialog.Builder(SignupActivity.this)
                        .setTitle("Error")
                        .setMessage("Password incorrect!")
                        .setPositiveButton("OK", null)
                        .show();
            } else {

                // Tạo đối tượng User
                User user = new User(username, password, email, phone, date);

                // Lưu dữ liệu lên Firebase
                String userId = mDatabase.child("users").push().getKey(); // Tạo một key duy nhất
                mDatabase.child("users").child(userId).setValue(user)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(SignupActivity.this, "Register Successfully!", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(SignupActivity.this, "Register Failed! Permission denied.", Toast.LENGTH_SHORT).show(); // Thêm thông báo lỗi chi tiết
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
