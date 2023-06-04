package com.hltstudio.on_thi_gplx.model;

import java.util.ArrayList;
import java.util.List;

public class Meo {

    private String tenMeo;
    private String noiDung;
    private boolean expandable;

    public Meo(String tenMeo, String noiDung) {
        this.tenMeo = tenMeo;
        this.noiDung = noiDung;
        this.expandable = false;
    }

    public String getTenMeo() {
        return tenMeo;
    }

    public void setTenMeo(String tenMeo) {
        this.tenMeo = tenMeo;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public static List<Meo> initMeoLyThuyet() {
        List<Meo> meos = new ArrayList<>();
        meos.add(new Meo("Khái niệm và quy tắc",
                "1. Câu khái niệm \"Phương tiện giao thông cơ giới đường bộ\" chọn \"Kể cả xe máy điện\".\n" +
                        "2. Câu khái niệm \"Phương tiện giao thông cơ giới đường bộ\" chọn \"Kể cả xe máy điện\".\n" +
                        "3. Câu khái niệm \"Phương tiện giao thông cơ giới đường bộ\" chọn \"Kể cả xe máy điện\".\n"));

        meos.add(new Meo("Hệ thống biển báo",
                "1.Đối với các câu có biển báo hiệu lệnh được đặt trước ngã ba, ngã tư. Chọn câu 1 nếu câu hỏi 1 dòng, câu 3 nếu câu hỏi có 2 dòng.\n" +
                        "2.Biển báo cấm máy kéo thì không cấm ô tô tải và ngược lại.\n" +
                        "3.Biển báo cấm rẽ trái thì cấm quay đầu và biển cấm quay đầu thì không cấm rẽ trái"));

        meos.add(new Meo("Sa hình",
                "1.Đối với các câu có biển báo hiệu lệnh được đặt trước ngã ba, ngã tư. Chọn câu 1 nếu câu hỏi 1 dòng, câu 3 nếu câu hỏi có 2 dòng.\n" +
                        "2.Biển báo cấm máy kéo thì không cấm ô tô tải và ngược lại.\n" +
                        "3.Biển báo cấm rẽ trái thì cấm quay đầu và biển cấm quay đầu thì không cấm rẽ trái"));
        return meos;
    }

    public static List<Meo> initMeoThucHanh() {
        List<Meo> meos = new ArrayList<>();
        meos.add(new Meo("Giới thiệu",
                "1. Câu khái niệm \"Phương tiện giao thông cơ giới đường bộ\" chọn \"Kể cả xe máy điện\".\n" +
                        "2. Câu khái niệm \"Phương tiện giao thông cơ giới đường bộ\" chọn \"Kể cả xe máy điện\".\n" +
                        "3. Câu khái niệm \"Phương tiện giao thông cơ giới đường bộ\" chọn \"Kể cả xe máy điện\".\n"));

        meos.add(new Meo("Bài 1: Chạy theo vòng số 8",
                "1.Đối với các câu có biển báo hiệu lệnh được đặt trước ngã ba, ngã tư. Chọn câu 1 nếu câu hỏi 1 dòng, câu 3 nếu câu hỏi có 2 dòng.\n" +
                        "2.Biển báo cấm máy kéo thì không cấm ô tô tải và ngược lại.\n" +
                        "3.Biển báo cấm rẽ trái thì cấm quay đầu và biển cấm quay đầu thì không cấm rẽ trái"));

        meos.add(new Meo("Bài 2: Đi đường thẳng",
                "1.Đối với các câu có biển báo hiệu lệnh được đặt trước ngã ba, ngã tư. Chọn câu 1 nếu câu hỏi 1 dòng, câu 3 nếu câu hỏi có 2 dòng.\n" +
                        "2.Biển báo cấm máy kéo thì không cấm ô tô tải và ngược lại.\n" +
                        "3.Biển báo cấm rẽ trái thì cấm quay đầu và biển cấm quay đầu thì không cấm rẽ trái"));
        return meos;
    }
}
