package com.hltstudio.on_thi_gplx.model;

import com.hltstudio.on_thi_gplx.R;

import java.util.ArrayList;

public class ChucNang {

    private int imgId;
    private String tenChucNang;

    public ChucNang(int imgId, String tenChucNang) {
        this.imgId = imgId;
        this.tenChucNang = tenChucNang;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTenChucNang() {
        return tenChucNang;
    }

    public void setTenChucNang(String tenChucNang) {
        this.tenChucNang = tenChucNang;
    }

    public static ArrayList<ChucNang> initChucNang() {
        ArrayList<ChucNang> arrayList = new ArrayList<>();
        arrayList.add(new ChucNang(R.drawable.book, "Học lý thuyết\n"));
        arrayList.add(new ChucNang(R.drawable.crossing, "Biển báo\nđường bộ"));
        arrayList.add(new ChucNang(R.drawable.idea, "Mẹo thi\n"));
        arrayList.add(new ChucNang(R.drawable.doexam, "Thi thử\n"));
        return arrayList;
    }
}
