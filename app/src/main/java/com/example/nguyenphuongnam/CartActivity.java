package com.example.nguyenphuongnam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {
    private CartManager cartManager;
    private TextView totalPrice;
    private CartAdapter adapter;
    private ListView cartListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Khởi tạo CartManager
        cartManager = CartManager.getInstance();

        // Ánh xạ các thành phần UI
        cartListView = findViewById(R.id.cartListView);
        totalPrice = findViewById(R.id.totalPrice);
        Button btnCheckout = findViewById(R.id.btnCheckout);
        Button btnBackToHome = findViewById(R.id.btnBackToHome); // ✅ Thêm ánh xạ nút "Back to Home"

        // ✅ Truyền callback để cập nhật tổng tiền khi thay đổi số lượng
        adapter = new CartAdapter(this, cartManager.getCartItems(), this::updateTotalPrice);
        cartListView.setAdapter(adapter);

        // Hiển thị tổng tiền ban đầu
        updateTotalPrice();

        // Xử lý sự kiện khi nhấn nút thanh toán
        btnCheckout.setOnClickListener(v -> {
            if (cartManager.getCartItems().isEmpty()) {
                Toast.makeText(this, "Giỏ hàng đang trống!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Thanh toán thành công!", Toast.LENGTH_SHORT).show();
                cartManager.clearCart(); // Xóa giỏ hàng sau khi thanh toán
                updateTotalPrice(); // Cập nhật lại tổng tiền
                adapter.notifyDataSetChanged(); // Cập nhật giao diện giỏ hàng
            }
        });

        // ✅ Xử lý sự kiện quay lại HomeActivity khi bấm "Back to Home"
        btnBackToHome.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Đóng CartActivity để quay lại HomeActivity
        });
    }

    // Hàm cập nhật tổng tiền
    private void updateTotalPrice() {
        int total = cartManager.getTotalPrice();
        totalPrice.setText("Tổng tiền: " + total + " VND");
    }
}
