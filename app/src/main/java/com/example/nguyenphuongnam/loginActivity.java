package com.example.nguyenphuongnam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity {
    List<Model.data> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Thiết lập insets cho giao diện
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Kết nối với các View trong layout
        EditText etUsername = findViewById(R.id.editTextTextEmailAddress2);
        EditText etPassword = findViewById(R.id.editTextTextPassword);
        Button btnSignin = findViewById(R.id.btnSignin);

        btnSignin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            //đăng nhập tài khoản bằng API
            Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
            Call<Model> call = methods.getAllData();

            call.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        list = response.body().getData();
                        boolean matchFound = false;

                        for (Model.data user : list) {
                            String firstName = user.getFirst_name();
                            String lastName = user.getLast_name();

                            // Kiểm tra thông tin đăng nhập
                            if (username.equals(firstName) && password.equals(lastName)) {
                                Toast.makeText(loginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                                // Chuyển sang màn hình chính
                                Intent intent = new Intent(loginActivity.this, HomeActivity.class);
                                intent.putExtra("username", firstName);
                                startActivity(intent);
                                finish();
                                return;
                            }
                        }
                        Toast.makeText(loginActivity.this, "Sai tên đăng nhập hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(loginActivity.this, "Không nhận được dữ liệu từ server", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Model> call, Throwable t) {
                    Toast.makeText(loginActivity.this, "Đã xảy ra lỗi trong quá trình kết nối", Toast.LENGTH_LONG).show();
                }
            });
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent it = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(it);
        });
    }
}

