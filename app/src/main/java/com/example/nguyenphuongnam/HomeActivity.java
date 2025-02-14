package com.example.nguyenphuongnam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    // Khai báo danh sách sản phẩm
    int image[] = {
            R.drawable.iphone12, R.drawable.laptop, R.drawable.donghothoitrang,
            R.drawable.donghothongminh, R.drawable.samsung, R.drawable.phukien,
            R.drawable.xiaomi, R.drawable.maytinh
    };

    String name[] = {
            "Iphone12", "Macbook", "Rolex", "AppleWatch",
            "SamsungGalaxyXTRA", "Airpods1", "XiaomiPro", "DELLX3PRO"
    };

    int price[] = {12000000, 36000000, 135000000, 8000000, 7000000, 3190000, 4200000, 17000000};

    // Khai báo GridView và các thành phần giao diện
    GridView gv;
    ArrayList<Item> mylist;
    MyArrayAdapter myadapter;
    CartManager cartManager;
    TextView tvWelcome;
    Button btnBack;
    ImageButton btnCart; // nút giỏ hàng

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Khởi tạo CartManager để quản lý giỏ hàng
        cartManager = CartManager.getInstance();

        // Ánh xạ các View từ layout
        tvWelcome = findViewById(R.id.txtKQ);
        btnBack = findViewById(R.id.buttonback);
        btnCart = findViewById(R.id.buttonCart); // Đảm bảo đã có nút này trong layout XML
        gv = findViewById(R.id.gv);

        // Nhận dữ liệu từ Intent
        Intent it = getIntent();
        String username = it.getStringExtra("username");

        // Kiểm tra và hiển thị thông tin chào mừng
        if (username == null || username.isEmpty()) {
            username = "User"; // Giá trị mặc định nếu không có dữ liệu
        }
        tvWelcome.setText("Chào mừng, " + username + "!");

        // Khởi tạo danh sách sản phẩm
        mylist = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            mylist.add(new Item(image[i], name[i], price[i]));
        }

        // Thiết lập Adapter cho GridView
        myadapter = new MyArrayAdapter(HomeActivity.this, R.layout.layout_item, mylist);
        gv.setAdapter(myadapter);

        // Xử lý sự kiện click vào sản phẩm để xem chi tiết
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent myintent = new Intent(HomeActivity.this, SubActivity.class);
                myintent.putExtra("image", image[i]);
                myintent.putExtra("name", name[i]);
                myintent.putExtra("price", price[i]);
                startActivity(myintent);
            }
        });

        // Xử lý sự kiện nút quay lại đăng nhập
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, loginActivity.class);
                startActivity(intent);
                finish(); // Đóng HomeActivity
            }
        });

        // Xử lý sự kiện mở giỏ hàng
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        // Đặt lề tự động cho layout chính
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
