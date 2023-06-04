package com.hltstudio.on_thi_gplx.model;

import java.util.ArrayList;

public class LoaiBang {

    private String tenBang;
    private String mieuTa;

    public LoaiBang(String tenBang, String mieuTa) {
        this.tenBang = tenBang;
        this.mieuTa = mieuTa;
    }

    public String getTenBang() {
        return tenBang;
    }

    public void setTenBang(String tenBang) {
        this.tenBang = tenBang;
    }

    public String getMieuTa() {
        return mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }

    public static ArrayList<LoaiBang> initLoaiBang() {
        ArrayList<LoaiBang> arrayList = new ArrayList<>();
        arrayList.add(new LoaiBang("BẰNG A1", "Xe máy, xe mô tô 2 bánh có dung tích xi-lanh dưới 175cm3"));
        arrayList.add(new LoaiBang("BẰNG A2", "Xe mô tô 2 bánh có dung tích từ 175cm3 trở lên"));

        return arrayList;
    }
}
