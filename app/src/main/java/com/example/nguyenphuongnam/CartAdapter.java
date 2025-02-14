package com.example.nguyenphuongnam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


import java.util.List;

public class CartAdapter extends BaseAdapter {
    private Context context;
    private List<ItemCart> cartItems;
    private CartManager cartManager;

    private Runnable updateTotalCallback; // Callback để cập nhật tổng tiền

    public CartAdapter(Context context, List<ItemCart> cartItems,Runnable updateTotalCallback) {
        this.context = context;
        this.cartItems = cartItems;
        this.cartManager = CartManager.getInstance();
        this.updateTotalCallback = updateTotalCallback;
    }

    @Override
    public int getCount() { return cartItems.size(); }

    @Override
    public Object getItem(int position) { return cartItems.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        }

        ItemCart item = cartItems.get(position);

        // Liên kết các thành phần trong cart_item.xml
        ImageView itemImage = convertView.findViewById(R.id.imgItem);
        TextView itemName = convertView.findViewById(R.id.itemName);
        TextView itemPrice = convertView.findViewById(R.id.itemPrice);
        TextView itemQuantity = convertView.findViewById(R.id.itemQuantity);
        Button btnIncrease = convertView.findViewById(R.id.btnIncrease);
        Button btnDecrease = convertView.findViewById(R.id.btnDecrease);
        ImageButton btnRemove = convertView.findViewById(R.id.btnRemove);

        // Gán dữ liệu từ ItemCart vào các View
        itemName.setText(item.getName());
        itemPrice.setText(item.getPrice() + " VND");
        itemQuantity.setText(String.valueOf(item.getQuantity()));

        // ✅ Load ảnh từ drawable bằng setImageResource()
        itemImage.setImageResource(item.getImage());

        // Xử lý sự kiện tăng số lượng
        btnIncrease.setOnClickListener(v -> {
            cartManager.increaseQuantity(item);
            notifyDataSetChanged();
            updateTotalCallback.run(); // ✅ Gọi hàm cập nhật tổng tiền
        });

        // Xử lý sự kiện giảm số lượng
        btnDecrease.setOnClickListener(v -> {
            if (item.getQuantity() > 1) { // Không cho giảm xuống dưới 1
                cartManager.decreaseQuantity(item);
                notifyDataSetChanged();
                updateTotalCallback.run(); // ✅ Gọi hàm cập nhật tổng tiền
            }
        });

        // Xử lý sự kiện xóa sản phẩm khỏi giỏ hàng
        btnRemove.setOnClickListener(v -> {
            cartManager.removeItem(item);
            notifyDataSetChanged();
            updateTotalCallback.run(); // Cập nhật tổng tiền
            Toast.makeText(context, "Đã xóa sản phẩm!", Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }
}
