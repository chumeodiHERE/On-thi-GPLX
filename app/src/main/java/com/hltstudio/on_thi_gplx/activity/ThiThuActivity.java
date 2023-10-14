package com.hltstudio.on_thi_gplx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hltstudio.on_thi_gplx.R;
import com.hltstudio.on_thi_gplx.adapter.ThiThuAdapter;
import com.hltstudio.on_thi_gplx.model.Topics;
import com.hltstudio.on_thi_gplx.utilities.DbHelper;
import com.hltstudio.on_thi_gplx.utilities.ScoreTestHolder;

import java.util.ArrayList;

public class ThiThuActivity extends AppCompatActivity
{
    ListView listView;
    DbHelper helper;
    ThiThuAdapter adapter;
    ArrayList<Topics> list;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thi_thu);
        setTitle("Thi Thử");
        overridePendingTransition(R.anim.slide_in, 0);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.list_de_thi);
        helper = new DbHelper(ThiThuActivity.this);
        list = helper.getDataCategory();
        adapter = new ThiThuAdapter(ThiThuActivity.this, list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ScoreTestHolder.id != -1 && ScoreTestHolder.score != -1)
        {
            if (ScoreTestHolder.score >= 80)
                helper.setTrueIsPassTopic(ScoreTestHolder.id);
            else
                helper.setFalseIsPassTopic(ScoreTestHolder.id);

            list.clear();
            list = helper.getDataCategory();
            adapter = new ThiThuAdapter(ThiThuActivity.this, list);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu_thi, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId() == R.id.thiInfo)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final View customLayout = getLayoutInflater().inflate(R.layout.alert_dialog_thi_info_layout, null);
            builder.setView(customLayout);
            builder.setPositiveButton("ĐÃ HIỂU", (dialog, which) -> {});
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        overridePendingTransition(R.anim.slide_back_in, R.anim.slide_back);
        return true;
    }
}
