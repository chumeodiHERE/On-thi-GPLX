package com.hltstudio.on_thi_gplx.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.hltstudio.on_thi_gplx.R;
import com.hltstudio.on_thi_gplx.adapter.ViewMeoPagerAdapter;

public class MeoActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meo);
        setTitle("Mẹo thi kết quả cao");
        overridePendingTransition(R.anim.slide_in, 0);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = findViewById(R.id.tab_meoThi);
        viewPager = findViewById(R.id.pager_meoThi);

        ViewMeoPagerAdapter viewMeoPagerAdapter = new ViewMeoPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewMeoPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        overridePendingTransition(R.anim.slide_back_in, R.anim.slide_back);
        return true;
    }
}