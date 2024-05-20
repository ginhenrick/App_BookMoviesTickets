package com.example.bookmoviestickets.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmoviestickets.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private TextView profileUsername;
    private EditText profileEmailShow, profilePhoneShow, profileDateOfBirthShow, profilePasswordShow;
    private Button editProfileBtn, saveProfileBtn;
    private DatabaseReference mDatabase;
    private User currentUser; // Lưu trữ thông tin người dùng hiện tại

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Khởi tạo các view
        profileUsername = findViewById(R.id.profileUsername);
        profileEmailShow = findViewById(R.id.profileEmailShow);
        profilePhoneShow = findViewById(R.id.profilePhoneShow);
        profileDateOfBirthShow = findViewById(R.id.profileDateOfBirthShow);
        profilePasswordShow = findViewById(R.id.profilePasswordShow);
        editProfileBtn = findViewById(R.id.editProfileBtn);
        saveProfileBtn = findViewById(R.id.saveProfileBtn);

        // Lấy thông tin người dùng từ Intent
        String username = getIntent().getStringExtra("username");

        // Lấy thông tin người dùng từ Firebase
        mDatabase.child("users").orderByChild("username").equalTo(username)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                currentUser = userSnapshot.getValue(User.class);
                                displayProfileInfo();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Xử lý lỗi
                    }
                });

        // Xử lý sự kiện cho nút "Edit"
        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableEditingMode();
            }
        });

        // Xử lý sự kiện cho nút "Save"
        saveProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfileChanges();
            }
        });

        ImageView backImgProfile = findViewById(R.id.backImgProfile);
        backImgProfile.setOnClickListener(v -> finish());
    }

    private void displayProfileInfo() {
        profileUsername.setText(currentUser.getUsername());
        profileEmailShow.setText(currentUser.getEmail());
        profilePhoneShow.setText(currentUser.getPhone());
        profileDateOfBirthShow.setText(currentUser.getDate());
        profilePasswordShow.setText(currentUser.getPassword());

        // Ẩn nút "Save" và cho phép chỉnh sửa (nếu cần)
        saveProfileBtn.setVisibility(View.GONE);
        enableEditingMode();
    }

    private void enableEditingMode() {
        profileEmailShow.setEnabled(true);
        profilePhoneShow.setEnabled(true);
        profileDateOfBirthShow.setEnabled(true);
        profilePasswordShow.setEnabled(true);
        saveProfileBtn.setVisibility(View.VISIBLE);
    }

    private void saveProfileChanges() {
        String email = profileEmailShow.getText().toString();
        String phone = profilePhoneShow.getText().toString();
        String date = profileDateOfBirthShow.getText().toString();
        String password = profilePasswordShow.getText().toString();

        // Kiểm tra dữ liệu
        if (email.isEmpty() || phone.isEmpty() || date.isEmpty() || password.isEmpty()) {
            new AlertDialog.Builder(ProfileActivity.this)
                    .setTitle("Lỗi")
                    .setMessage("Vui lòng điền đầy đủ thông tin!")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        // Cập nhật thông tin người dùng
        currentUser.setEmail(email);
        currentUser.setPhone(phone);
        currentUser.setDate(date);
        currentUser.setPassword(password);

        // Lưu thông tin lên Firebase
        mDatabase.child("users").child(currentUser.getUsername()).setValue(currentUser)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(ProfileActivity.this, "Thông tin đã được lưu!", Toast.LENGTH_SHORT).show();
                    // Cập nhật giao diện
                    displayProfileInfo();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(ProfileActivity.this, "Lưu thông tin thất bại!", Toast.LENGTH_SHORT).show();
                });
    }
}