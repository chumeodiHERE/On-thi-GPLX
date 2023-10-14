package com.hltstudio.on_thi_gplx.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hltstudio.on_thi_gplx.R;
import com.hltstudio.on_thi_gplx.utilities.DbHelper;
import com.hltstudio.on_thi_gplx.model.Topics;

import java.util.List;

public class TopicsActivity extends AppCompatActivity
{

    private float highScore;
    private TextView textViewHighScore;
    private Spinner spinnerCategory;
    private Button buttonStartQuestion;
    private DbHelper helper;
    private static final int REQUEST_CODE_QUESTION = 1;
    private List<Topics> list;
    private ArrayAdapter<Topics> adapter;
    private Topics topics;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        setTitle("Thi thử");
        overridePendingTransition(R.anim.slide_in, 0);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

        loadCategory();
        loadHighScore();
        buttonStartQuestion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                starQuestion();
            }
        });
    }

    private void loadHighScore()
    {
        SharedPreferences preferences = getSharedPreferences("share",MODE_PRIVATE);
        highScore = preferences.getFloat("highScore",0);
        textViewHighScore.setText("Điểm cao nhất : " + highScore);
    }

    private void starQuestion()
    {
        topics = (Topics) spinnerCategory.getSelectedItem();
        int ID = topics.getId();
        String Name = topics.getName();

        Intent intent =new Intent(TopicsActivity.this, TestQuestionsActivity.class);
        intent.putExtra("Id",ID);
        intent.putExtra("Name",Name);
        startActivityForResult(intent,REQUEST_CODE_QUESTION);
    }

    private void init()
    {
        textViewHighScore = findViewById(R.id.textview_high_score);
        buttonStartQuestion = findViewById(R.id.button_start_question);
        spinnerCategory = findViewById(R.id.spinner_categorycategory);
    }
    private void loadCategory()
    {
        helper = new DbHelper(TopicsActivity.this);
        list = helper.getDataCategory();
        adapter = new ArrayAdapter<>(TopicsActivity.this, android.R.layout.simple_spinner_item,list);
        spinnerCategory.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_QUESTION){
            if(resultCode == RESULT_OK){
                float score = data.getFloatExtra("score",0);
                if(score > highScore){
                    updatehighScore(score);
                }
            }
        }
    }

    private void updatehighScore(float score)
    {
        highScore = score;
        textViewHighScore.setText("Điểm cao nhất : "+ highScore);

        SharedPreferences preferences = getSharedPreferences("share",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putFloat("highScore",highScore);
        editor.apply();
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