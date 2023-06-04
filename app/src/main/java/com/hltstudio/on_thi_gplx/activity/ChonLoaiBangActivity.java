package com.hltstudio.on_thi_gplx.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.hltstudio.on_thi_gplx.R;
import com.hltstudio.on_thi_gplx.adapter.BangAdapter;
import com.hltstudio.on_thi_gplx.model.LoaiBang;

import java.util.ArrayList;

public class ChonLoaiBangActivity extends AppCompatActivity {

    GridView listLoaiBang;
    ArrayList<LoaiBang> arrayList;
    BangAdapter adapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_loai_bang);
        setTitle("Chọn hạng bằng thi");
        initSharedPreference();
        boolean isChooseA1 = sharedPreferences.getBoolean("choosenA1", false);
        boolean isChooseA2 = sharedPreferences.getBoolean("choosenA2", false);
        listLoaiBang = findViewById(R.id.listLoaiBang);
        arrayList = LoaiBang.initLoaiBang();
        adapter = new BangAdapter(ChonLoaiBangActivity.this, R.layout.layout_row_chon_loai_bang, arrayList);
        listLoaiBang.setAdapter(adapter);

        listLoaiBang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        editor.putBoolean("choosenA1", true);
                        editor.putBoolean("choosenA2", false);
                        editor.commit();
                        Intent intent = new Intent(ChonLoaiBangActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void initSharedPreference() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }
}