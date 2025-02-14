package com.example.nguyenphuongnam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class create_accountActivity extends AppCompatActivity {

    private EditText emailField, passwordField, confirmPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Liên kết các thành phần
        emailField = findViewById(R.id.editTextTextEmailAddress);
        passwordField = findViewById(R.id.editTextTextPassword2);
        confirmPasswordField = findViewById(R.id.editTextTextPassword3);
        Button signUpButton = findViewById(R.id.button4);
        Button btnBackRegister = findViewById(R.id.btnBackRegister);

        // Xử lý đăng ký
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailField.getText().toString().trim();
                String password = passwordField.getText().toString();
                String confirmPassword = confirmPasswordField.getText().toString();

                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(create_accountActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(create_accountActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(create_accountActivity.this, "Địa chỉ email không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }

                saveAccount(email, password);
                Toast.makeText(create_accountActivity.this, "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();

                // Tự động đăng nhập sau khi đăng ký thành công
                autoLogin(email, password);
            }
        });

        // Xử lý quay lại
        btnBackRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(create_accountActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveAccount(String email, String password) {
        getSharedPreferences("UserPrefs", MODE_PRIVATE)
                .edit()
                .putString("email", email)
                .putString("password", password)
                .apply();

        Log.d("DEBUG", "Saved Email: " + email);
        Log.d("DEBUG", "Saved Password: " + password);
    }

    private void autoLogin(String email, String password) {
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String registeredEmail = prefs.getString("email", null);
        String registeredPassword = prefs.getString("password", null);

        if (email.equals(registeredEmail) && password.equals(registeredPassword)) {
            Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(create_accountActivity.this, loginActivity.class); // Chuyển đến màn hình chính
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Đăng nhập thất bại. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
        }
    }
}
