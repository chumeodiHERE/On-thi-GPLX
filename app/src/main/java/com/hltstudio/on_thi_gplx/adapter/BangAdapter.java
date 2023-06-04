package com.hltstudio.on_thi_gplx.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
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
import com.hltstudio.on_thi_gplx.model.LoaiBang;

import java.util.ArrayList;

public class BangAdapter extends ArrayAdapter {

    Context context;
    ArrayList<LoaiBang> arrayList;
    int layout;

    public BangAdapter(@NonNull Context context, int resource, @NonNull ArrayList<LoaiBang> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.arrayList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LoaiBang loaiBang = arrayList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, null);
        }
        TextView txtViewTenBang = convertView.findViewById(R.id.txtViewTenBang);
        TextView txtViewMieuTaBang = convertView.findViewById(R.id.txtViewMieuTaBang);
        ImageView imgIscheck = convertView.findViewById(R.id.imgIscheck);

        txtViewTenBang.setText(loaiBang.getTenBang());
        txtViewMieuTaBang.setText(loaiBang.getMieuTa());
        return convertView;
    }
}
