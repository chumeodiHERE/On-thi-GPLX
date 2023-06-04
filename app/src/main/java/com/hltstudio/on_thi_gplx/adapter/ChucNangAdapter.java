package com.hltstudio.on_thi_gplx.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.hltstudio.on_thi_gplx.R;
import com.hltstudio.on_thi_gplx.model.ChucNang;

import java.util.ArrayList;

public class ChucNangAdapter extends ArrayAdapter {

    Context context;
    ArrayList<ChucNang> arrayList;
    int layout;

    public ChucNangAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ChucNang> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.arrayList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ChucNang chucNang = arrayList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, null);
        }
        ImageView imgChucNang = convertView.findViewById(R.id.imgChucNang);
        TextView txtViewTenChucNang = convertView.findViewById(R.id.txtViewTenChucNang);

        imgChucNang.setImageResource(chucNang.getImgId());
        txtViewTenChucNang.setText(chucNang.getTenChucNang());

        return convertView;
    }

}
