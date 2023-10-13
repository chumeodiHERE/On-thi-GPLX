package com.hltstudio.on_thi_gplx.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hltstudio.on_thi_gplx.R;
import com.hltstudio.on_thi_gplx.adapter.AnswerAdapter;
import com.hltstudio.on_thi_gplx.utilities.DbHelper;
import com.hltstudio.on_thi_gplx.model.Answer;
import com.hltstudio.on_thi_gplx.model.Question;
import com.hltstudio.on_thi_gplx.utilities.imageConverter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class HocLyThuyetDetailActivity extends AppCompatActivity
{

    ArrayList<Answer> answerArrayList;
    ArrayList<Question> questionArrayList;
    AnswerAdapter answerAdapter;
    ListView listView;
    DbHelper dbHelper;
    TextView tvQuestion, tvExplain, tvTitle;
    ImageView img_question;
    imageConverter converter;
    BottomNavigationView navigation;
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoc_ly_thuyet_detail);
        overridePendingTransition(R.anim.slide_in, 0);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AnhXa();
        Intent it = getIntent();
        String groupName = it.getStringExtra("GroupName");
        setTitle(groupName);
        int idgroup = it.getIntExtra("IDGroup", 0);


        questionArrayList = dbHelper.getQuestionWithGQId(idgroup);

        loadQuestion(index);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId()) {
                case R.id.previous_ques:
                    if(index == 0) {
                        index = questionArrayList.size() -1;
                        loadQuestion(index);
                    } else {
                        index--;
                        loadQuestion(index);
                    }
                    return true;
                case R.id.next_ques:
                    if(index == questionArrayList.size() - 1) {
                        index = 0;
                        loadQuestion(index);
                    } else {
                        index++;
                        loadQuestion(index);
                    }
                    return true;
                case R.id.home_screen:
                    Intent intent = new Intent(HocLyThuyetDetailActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    private void loadQuestion(int i)
    {
        Question qes = questionArrayList.get(i);

        tvTitle.setVisibility(View.GONE);
        tvExplain.setVisibility(View.GONE);

        if(qes.getIMAGENAME() != null)
        {
            converter = new imageConverter(qes.getIMAGENAME());
            img_question.setVisibility(View.VISIBLE);
            img_question.setImageBitmap(converter.imageConverted());
        }

        tvQuestion.setText(qes.getTITLE());
        answerArrayList = dbHelper.getAnswer(qes.getID());

        for (Answer answer : answerArrayList)
        {
            if(answer.isCORRECTCHECK() && answer.getISCHOOSE().equals("true"))
            {
                tvTitle.setVisibility(View.VISIBLE);
                tvExplain.setVisibility(View.VISIBLE);
            }
        }

        answerAdapter = new AnswerAdapter(getApplicationContext(), R.layout.layout_row_answer, answerArrayList);
        listView.setAdapter(answerAdapter);

        tvExplain.setText(qes.getEXPLAINQUESTION());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Answer answer = answerArrayList.get(position);
                int idqes =answer.getQUESTIONID();
                int idanw = answer.getID();
                if(answer.getISCHOOSE().equals("true"))
                {
                    if(answer.isCORRECTCHECK())
                    {
                        tvTitle.setVisibility(View.GONE);
                        tvExplain.setVisibility(View.GONE);
                    }
                    dbHelper.changeTruetoFalseAnsw(idanw);
                    dbHelper.changeTruetoFalseQues(idqes);
                    loadQuestion(index);

                }
                else
                {
                    if(answer.isCORRECTCHECK())
                    {
                        tvTitle.setVisibility(View.VISIBLE);
                        tvExplain.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        tvTitle.setVisibility(View.GONE);
                        tvExplain.setVisibility(View.GONE);
                    }
                    dbHelper.setFalseAllAnswer(idqes);
                    dbHelper.setIsChooseAnswer(idanw, idqes);
                    dbHelper.setDoneQuestion(idqes);
                    loadQuestion(index);
                }
            }
        });
    }

    private void AnhXa()
    {
        dbHelper = new DbHelper(getApplicationContext());
        listView = (ListView) findViewById(R.id.lvAnswer);
        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        tvExplain = (TextView) findViewById(R.id.tvExplain);
        img_question = (ImageView) findViewById(R.id.img_question);
        navigation = (BottomNavigationView) findViewById(R.id.navigation_hoc_lt);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        overridePendingTransition(R.anim.slide_back_in, R.anim.slide_back);
        return true;
    }
}