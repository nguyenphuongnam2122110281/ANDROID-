package com.example.nguyenphuongnam;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Item> {
    //khai bao 3 tham so cua Adapter
    Activity context;
    int Idlayout;
    ArrayList<Item> mylist;
    //Tao Contructor de HomeActivity goi den va truyen 3 tham so vao

    public MyArrayAdapter( Activity context, int idlayout, ArrayList<Item> mylist) {
        super(context, idlayout,mylist);
        this.context = context;
        Idlayout = idlayout;
        this.mylist = mylist;
    }
    //goi ham getView de Adapter lay va hien thi du lieu

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Tao de chua layout
        LayoutInflater myflactor = context.getLayoutInflater();
        // Dat Idlayout len de Tao View
        convertView = myflactor.inflate(Idlayout,null);
        //lay 1 phan tu trong mang du lieu
        Item myitem = mylist.get(position);
        //khai bao,anh xa Id va hien thi anh cua san pham
        ImageView img_item = convertView.findViewById(R.id.img_item);
        img_item.setImageResource(myitem.getImage());
        //khai bao,anh xa id va hien thi ten san pham
        TextView txt_tensp = convertView.findViewById(R.id.txt_tensp);
        txt_tensp.setText(myitem.getName());
        //khai bao ,anh xa id va hien thi gia san pham
        TextView txt_giasp = convertView.findViewById(R.id.txt_giasp);
        txt_giasp.setText("Giá: "+myitem.getPrice());
        return convertView;
    }
}
