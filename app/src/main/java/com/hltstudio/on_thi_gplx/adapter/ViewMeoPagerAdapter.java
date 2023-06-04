package com.hltstudio.on_thi_gplx.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.hltstudio.on_thi_gplx.activity.MeoLyThuyetFragment;
import com.hltstudio.on_thi_gplx.activity.MeoThucHanhFragment;

public class ViewMeoPagerAdapter extends FragmentStatePagerAdapter {

    public ViewMeoPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new MeoThucHanhFragment();
            case 0:
            default:
                return new MeoLyThuyetFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "MẸO LÝ THUYẾT";
                break;
            case 1:
                title = "MẸO THỰC HÀNH";
                break;
        }
        return title;
    }
}
