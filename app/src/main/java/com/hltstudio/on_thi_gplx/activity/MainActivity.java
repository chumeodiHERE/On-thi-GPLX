package com.hltstudio.on_thi_gplx.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;

import com.hltstudio.on_thi_gplx.R;
import com.hltstudio.on_thi_gplx.adapter.ChucNangAdapter;
import com.hltstudio.on_thi_gplx.adapter.PhotoViewPagerAdapter;
import com.hltstudio.on_thi_gplx.model.ChucNang;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    GridView listChucNang;
    ArrayList<ChucNang> arrayList;
    ChucNangAdapter adapter;
    ViewPager viewPager2;
    CircleIndicator circleIndicator;
    PhotoViewPagerAdapter photoAdapter;
    ArrayList<PhotoViewPager> pagers;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Ôn thi giấy phép lái xe");
        overridePendingTransition(R.anim.slide_in, 0);

        addControl();

        pagers = new PhotoViewPager().initPhotoViewPageA1();
        arrayList = ChucNang.initChucNang();

        photoAdapter = new PhotoViewPagerAdapter(this, pagers);
        adapter = new ChucNangAdapter(MainActivity.this, R.layout.layout_chuc_nang_item, arrayList);

        viewPager2.setAdapter(photoAdapter);
        circleIndicator.setViewPager(viewPager2);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        listChucNang.setAdapter(adapter);
        autoTimer();

        listChucNang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChucNang chucNang = arrayList.get(position);
                Intent intent = new Intent();
                switch (chucNang.getTenChucNang()) {
                    case "Mẹo thi\n":
                        intent = new Intent(MainActivity.this, MeoActivity.class);
                        startActivity(intent);
                        break;
                    case "Biển báo\nđường bộ":
                        intent = new Intent(MainActivity.this, RoadSignActivity.class);
                        startActivity(intent);
                        break;
                    case "Học lý thuyết\n":
                        intent = new Intent(MainActivity.this, HocLyThuyetActivity.class);
                        startActivity(intent);
                        break;
                    case "Thi thử\n":
                        intent = new Intent(MainActivity.this, TopicsActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void addControl() {
        viewPager2 = findViewById(R.id.vpg);
        circleIndicator = findViewById(R.id.circle_indicator);
        listChucNang = findViewById(R.id.listChucNang);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu_trang_chu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settingApp) {
            Intent intent = new Intent(MainActivity.this, SettingAppActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void autoTimer() {
        if(pagers==null||pagers.isEmpty()){
            return;
        }
        if(timer == null){
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int crrItem = viewPager2.getCurrentItem();
                        int totalItem = pagers.size()-1;
                        if(crrItem<totalItem){
                            crrItem++;
                            viewPager2.setCurrentItem(crrItem);
                        }else{
                            viewPager2.setCurrentItem(0);
                        }
                    }
                });
            }
        },1500,6500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer!=null){
            timer.cancel();
            timer=null;
        }
    }

}