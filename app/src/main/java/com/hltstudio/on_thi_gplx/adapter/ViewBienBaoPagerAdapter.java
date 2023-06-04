package com.hltstudio.on_thi_gplx.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.hltstudio.on_thi_gplx.activity.BienBaoCamFragment;
import com.hltstudio.on_thi_gplx.activity.BienChiDanFragment;
import com.hltstudio.on_thi_gplx.activity.BienHieuLenhFragment;
import com.hltstudio.on_thi_gplx.activity.BienNguyHiemVaCanhBaoFragment;
import com.hltstudio.on_thi_gplx.activity.BienPhuFragment;
import com.hltstudio.on_thi_gplx.activity.MeoLyThuyetFragment;
import com.hltstudio.on_thi_gplx.activity.MeoThucHanhFragment;

public class ViewBienBaoPagerAdapter extends FragmentStatePagerAdapter {

    public ViewBienBaoPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BienBaoCamFragment();
            case 1:
                return new BienHieuLenhFragment();
            case 2:
                return new BienChiDanFragment();
            case 3:
                return new BienNguyHiemVaCanhBaoFragment();
            default:
                return new BienPhuFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "BIỂN BÁO CẤM";
                break;
            case 1:
                title = "BIỂN HIỆU LỆNH";
                break;
            case 2:
                title = "BIỂN CHỈ DẪN";
                break;
            case 3:
                title = "BIỂN BÁO NGUY HIỂM VÀ CẢNH BÁO";
                break;
            case 4:
                title = "BIỂN PHỤ";
                break;
        }
        return title;
    }
}
