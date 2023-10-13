package com.hltstudio.on_thi_gplx.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hltstudio.on_thi_gplx.R;
import com.hltstudio.on_thi_gplx.adapter.HocLyThuyetAdapter;
import com.hltstudio.on_thi_gplx.utilities.DbHelper;
import com.hltstudio.on_thi_gplx.model.GroupQuestion;

import java.util.ArrayList;

public class HocLyThuyetActivity extends AppCompatActivity {
    ListView listView;
    DbHelper helper;
    HocLyThuyetAdapter adapter;
    ArrayList<GroupQuestion> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoc_ly_thuyet);
        setTitle("Học lý thuyết");
        overridePendingTransition(R.anim.slide_in, 0);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.listGroupCauHoi);
        helper = new DbHelper(HocLyThuyetActivity.this);
        list = helper.getAllGQ();
        adapter = new HocLyThuyetAdapter(HocLyThuyetActivity.this, list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent();
                it.putExtra("GroupName", list.get(position).getNAME());
                it.putExtra("IDGroup", list.get(position).getID());

                it.setClass(getApplicationContext(), HocLyThuyetDetailActivity.class);
                startActivity(it);
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        list.clear();
        list = helper.getAllGQ();
        adapter = new HocLyThuyetAdapter(HocLyThuyetActivity.this, list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        overridePendingTransition(R.anim.slide_back_in, R.anim.slide_back);
        return true;
    }
}