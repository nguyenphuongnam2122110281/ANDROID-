package com.example.nguyenphuongnam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class loginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // Kích hoạt chế độ EdgeToEdge
        EdgeToEdge.enable(this);

        // Thiết lập giao diện
        setContentView(R.layout.activity_login);

        // Đặt WindowInsetsListener cho View chính
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Kết nối các View từ giao diện XML
        EditText etUsername = findViewById(R.id.editTextTextEmailAddress2);
        EditText etPassword = findViewById(R.id.editTextTextPassword);
        Button btnSignin = findViewById(R.id.btnSignin);

        // Xử lý sự kiện khi nhấn nút Login
        btnSignin.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            // Kiểm tra thông tin đăng nhập
            if (username.equals("nam") && password.equals("1234")) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                // Chuyển sang màn hình khác
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("username", username); // Truyền username
                startActivity(intent);
            } else {
                Toast.makeText(this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(it);
            }
        });
    }
}
