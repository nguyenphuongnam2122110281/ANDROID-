package com.example.nguyenphuongnam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {
    private static final String[] items = {"lorem","ipsum","dolor"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Nhận dữ liệu từ Intent
        Intent it = this.getIntent();
        String data = it.getStringExtra("name"); // Lấy tên
        String pass = it.getStringExtra("pass"); // Lấy mật khẩu (nếu cần)


        // Kết nối TextView từ giao diện
        TextView tvWelcome = findViewById(R.id.txtKQ);

        // Lấy dữ liệu từ Intent
        String username = getIntent().getStringExtra("username");

        // Kiểm tra và hiển thị thông tin chào mừng
        if (username == null || username.isEmpty()) {
            username = "User"; // Giá trị mặc định nếu không có dữ liệu
        }

        // Hiển thị dữ liệu trong TextView tvWelcome
        tvWelcome.setText("Chào mừng, " + username + "!");

        // Khai báo myListView và kết nối với ListView trong layout
        ListView myListView = findViewById(R.id.my_listview);
        // Khai báo myArrayAdapter
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                items
        );
        // Gắn myArrayAdapter vào cho myListView
        myListView.setAdapter(myArrayAdapter);

        // Thêm sự kiện cho nút btnBack
        Button btnBack = findViewById(R.id.buttonback);
        btnBack.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                // Quay lại loginActivity
                Intent intent = new Intent(HomeActivity.this, loginActivity.class);
                startActivity(intent);
                finish(); // Đóng HomeActivity
            }
        });
    }

}
