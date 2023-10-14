package com.hltstudio.on_thi_gplx.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.hltstudio.on_thi_gplx.R;
import com.hltstudio.on_thi_gplx.adapter.HocLyThuyetAdapter;
import com.hltstudio.on_thi_gplx.utilities.DbHelper;
import com.hltstudio.on_thi_gplx.model.GroupQuestion;

import java.util.ArrayList;

public class HocLyThuyetActivity extends AppCompatActivity
{
    ListView listView;
    DbHelper helper;
    HocLyThuyetAdapter adapter;
    ArrayList<GroupQuestion> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
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
    protected void onStop()
    {
        super.onStop();
        list.clear();
        list = helper.getAllGQ();
        adapter = new HocLyThuyetAdapter(HocLyThuyetActivity.this, list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu_hoc_ly_thuyet, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId() == R.id.resetLearningProgress)
        {
            openDialog(Gravity.CENTER);
        }
        return super.onOptionsItemSelected(item);
    }

    private void openDialog(int gravity)
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_dialog_reset_progess_learning);
        Window window = dialog.getWindow();
        if (window == null){return;}
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowatt = window.getAttributes();
        windowatt.gravity = gravity;
        window.setAttributes(windowatt);

        Button btnDismiss = dialog.findViewById(R.id.btn_dismiss);
        Button btnAllow = dialog.findViewById(R.id.btn_allow_reset);

        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnAllow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.copyDatabaseFromAssets();
                list.clear();
                list = helper.getAllGQ();
                adapter = new HocLyThuyetAdapter(HocLyThuyetActivity.this, list);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        overridePendingTransition(R.anim.slide_back_in, R.anim.slide_back);
        return true;
    }
}