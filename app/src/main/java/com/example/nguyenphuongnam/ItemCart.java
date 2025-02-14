package com.example.nguyenphuongnam;

public class ItemCart {
    private String id;
    private String name;
    private int price;
    private int quantity;
    private int image; // Đường dẫn hình ảnh sản phẩm

    public ItemCart(String id, String name, int price, int quantity, int image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public int getImage() { return image; }

    //Tăng giảm số luong
    public void increaseQuantity() { this.quantity++; }
    public void decreaseQuantity() { if (this.quantity > 1) this.quantity--; }
}
