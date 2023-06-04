package com.hltstudio.on_thi_gplx.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;

import com.hltstudio.on_thi_gplx.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class LaunchActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String DB_NAME = "GPLX.db";
    private final String DB_PATH = "/databases/";
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Objects.requireNonNull(getSupportActionBar()).hide();

        solveCopyFromAssets();
        initSharedPreference();
        boolean isChooseA1 = sharedPreferences.getBoolean("choosenA1", false);
        boolean isChooseA2 = sharedPreferences.getBoolean("choosenA2", false);

        new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                if (isChooseA1) {
                    Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                    startActivity(intent);
                } else if (isChooseA2) {
                    Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(LaunchActivity.this, ChonLoaiBangActivity.class);
                    startActivity(intent);
                }
            }
        }.start();
    }

    private void solveCopyFromAssets() {
        File dbFile = getDatabasePath(DB_NAME);
        if(!dbFile.exists())
        {
            copyDatabase();
        }
    }

    private void copyDatabase() {
        try {
            InputStream myInput;
            //Lấy database đưa vào myInput
            myInput = getAssets().open(DB_NAME);
            //Lấy đường dẫn lưu trữ để đưa myInput vào
            String outFileName = getDatabasePath();

            File f = new File(getApplicationInfo().dataDir + DB_PATH);
            if (!f.exists())
                f.mkdir();
            //mở CSDL rỗng InputStream
            //myOutput để tương tác với CSDL
            OutputStream myOutput = new FileOutputStream(outFileName);
            //Sao chép CSDL từ myInput vào myOutput
            int size = myInput.available();
            byte[] buffer = new byte[size];
            myInput.read(buffer);
            //ghi vào myOutput
            myOutput.write(buffer);
            //làm rỗng file myOutput
            myOutput.flush();
            //Đóng các file myOutput, myInput
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH + DB_NAME;
    }

    private void initSharedPreference() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }
}