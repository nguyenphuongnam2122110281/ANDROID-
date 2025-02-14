package com.example.nguyenphuongnam;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<ItemCart> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    // Đảm bảo chỉ có một instance (Singleton Pattern)
    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    // ✅ Thêm sản phẩm vào giỏ hàng (nếu đã có thì tăng số lượng)
    public void addItem(ItemCart item) {
        if (item == null) return; // Kiểm tra tránh lỗi NullPointerException

        for (ItemCart cartItem : cartItems) {
            if (cartItem.getName().equals(item.getName())) { // Kiểm tra trùng sản phẩm theo tên
                cartItem.increaseQuantity();
                return;
            }
        }
        cartItems.add(item);
    }

    // ✅ Xóa sản phẩm khỏi giỏ hàng
    public void removeItem(ItemCart item) {
        if (item == null) return;
        cartItems.remove(item);
    }

    // ✅ Tăng số lượng sản phẩm
    public void increaseQuantity(ItemCart item) {
        for (ItemCart cartItem : cartItems) {
            if (cartItem.getId().equals(item.getId())) {
                cartItem.increaseQuantity();
                return;
            }
        }
    }

    public void decreaseQuantity(ItemCart item) {
        for (ItemCart cartItem : cartItems) {
            if (cartItem.getId().equals(item.getId()) && cartItem.getQuantity() > 1) {
                cartItem.decreaseQuantity();
                return;
            }
        }
    }


    // ✅ Lấy danh sách sản phẩm trong giỏ hàng
    public List<ItemCart> getCartItems() {
        return cartItems;
    }

    // ✅ Tính tổng tiền giỏ hàng
    public int getTotalPrice() {
        int total = 0;
        for (ItemCart item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    // ✅ Xóa toàn bộ giỏ hàng
    public void clearCart() {
        cartItems.clear();
    }
}
