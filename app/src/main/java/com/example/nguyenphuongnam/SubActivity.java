package com.example.nguyenphuongnam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {
    ImageView imgSubItem;
    TextView txtSubTen, txtSubGia;
    Button btnAddToCart;
    Button btnBackToHome; // Thêm nút Back;
    CartManager cartManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        // Ánh xạ các thành phần từ layout
        imgSubItem = findViewById(R.id.img_subitem);
        txtSubTen = findViewById(R.id.txt_subtensp);
        txtSubGia = findViewById(R.id.txt_subgiasp);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnBackToHome = findViewById(R.id.btnBackToHome); // Ánh xạ nút Back

        cartManager = CartManager.getInstance();

        // Nhận dữ liệu từ Intent
        Intent myIntent = getIntent();
        int img = myIntent.getIntExtra("image", 0);
        String tensp = myIntent.getStringExtra("name");
        int gia = myIntent.getIntExtra("price", 0);

        // Kiểm tra dữ liệu
        if (tensp != null) {
            txtSubTen.setText(tensp);
        } else {
            txtSubTen.setText("Không có tên sản phẩm");
        }

        if (gia > 0) {
            txtSubGia.setText("Giá SP: " + gia + " VND");
        } else {
            txtSubGia.setText("Giá không xác định");
        }

        imgSubItem.setImageResource(img);

        // Xử lý sự kiện khi nhấn "Thêm vào giỏ hàng"
        btnAddToCart.setOnClickListener(v -> {
            String id = tensp + "_" + System.currentTimeMillis(); // Tạo ID duy nhất
            int quantity = 1; // Mặc định số lượng là 1
            String imageUrl = "https://your-image-url.com/sample.jpg"; // Thay thế bằng URL ảnh thật

            ItemCart newItem = new ItemCart(id, tensp, gia, quantity, img);
            CartManager.getInstance().addItem(newItem);

            // Hiển thị thông báo
            Toast.makeText(SubActivity.this, "Đã thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
        });
        // ✅ Xử lý sự kiện quay lại HomeActivity
        btnBackToHome.setOnClickListener(v -> {
            Intent intent = new Intent(SubActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Đóng SubActivity để quay lại HomeActivity
        });
    }
}
