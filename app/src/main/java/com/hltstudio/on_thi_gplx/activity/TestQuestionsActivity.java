package com.hltstudio.on_thi_gplx.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hltstudio.on_thi_gplx.R;
import com.hltstudio.on_thi_gplx.utilities.DbHelper;
import com.hltstudio.on_thi_gplx.model.TESTQUESTIONS;
import com.hltstudio.on_thi_gplx.utilities.ScoreTestHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class TestQuestionsActivity extends AppCompatActivity
{

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCategory;
    private TextView textViewCountDown;

    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;

    private Button buttonNext;

    private CountDownTimer countDownTimer;
    private ArrayList<TESTQUESTIONS> list;
    private long timeLeftInMillis;
    private int questionCounter;
    private int questionSize;
    public int  Score;
    private int ScorePerQues;
    private Boolean answered;
    private DbHelper helper;
    private TESTQUESTIONS thiTracNghiem;
    public int categoryID;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_questions);

        init();

        Intent intent = getIntent();
        categoryID = intent.getIntExtra("Id",0);
        String categoryName = intent.getStringExtra("Name");

        textViewCategory.setText(categoryName);

        helper = new DbHelper(TestQuestionsActivity.this);
        list = helper.getQuestions(categoryID);
        questionSize = list.size();
        ScorePerQues = 100 / questionSize;
        Collections.shuffle(list);

        showNextQuestion();

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!answered){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked()){
                        checkAnswer();
                    }
                    else{
                        Toast.makeText(TestQuestionsActivity.this,"Chưa chọn đáp án", Toast.LENGTH_SHORT);
                    }
                }
                else{
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion() {
        rb1.setTextColor(Color.BLACK);
        rb2.setTextColor(Color.BLACK);
        rb3.setTextColor(Color.BLACK);
        rbGroup.clearCheck();

        if(questionCounter < questionSize){
            thiTracNghiem = list.get(questionCounter);

            textViewQuestion.setText(thiTracNghiem.getQuestion());
            rb1.setText(thiTracNghiem.getOption1());
            rb2.setText(thiTracNghiem.getOption2());
            rb3.setText(thiTracNghiem.getOption3());
            questionCounter++;
            textViewQuestionCount.setText("Câu hỏi :" + questionCounter + "/" + questionSize);
            answered = false;
            buttonNext.setText("Xác Nhận");

            timeLeftInMillis = 30000;
            startCountDown();
        }
        else{
            finishQuestion();
        }
    }

    private void startCountDown()
    {
        countDownTimer = new CountDownTimer(timeLeftInMillis,1000)
        {
            @Override
            public void onTick(long l)
            {
                timeLeftInMillis = l;
                updateCountDownText();
            }

            @Override
            public void onFinish()
            {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void checkAnswer()
    {
        answered = true;
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answer = rbGroup.indexOfChild(rbSelected)+1;
        if(answer == thiTracNghiem.getAnswer()){
            Score = Score + ScorePerQues;
            textViewScore.setText("Điểm : " + Score);
        }
        showSolution();
    }

    private void showSolution()
    {
        rb1.setTextColor(getColor(R.color.false_answer));
        rb2.setTextColor(getColor(R.color.false_answer));
        rb3.setTextColor(getColor(R.color.false_answer));
        switch(thiTracNghiem.getAnswer()){
            case 1:
                rb1.setTextColor(getColor(R.color.true_answer));
                break;
            case 2:
                rb2.setTextColor(getColor(R.color.true_answer));
                break;
            case 3:
                rb3.setTextColor(getColor(R.color.true_answer));
                break;
        }
        if(questionCounter<questionSize)
        {
            buttonNext.setText("Câu tiếp");
        }
        else{
            buttonNext.setText("Hoàn Thành");
        }
        countDownTimer.cancel();
    }

    private void updateCountDownText() {
        int minutes = (int)((timeLeftInMillis/1000)/60);
        int seconds = (int)((timeLeftInMillis/1000)%60);
        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        textViewCountDown.setText(timeFormatted);
        if(timeLeftInMillis < 10000){
            textViewCountDown.setTextColor(Color.RED);
        }else{
            textViewCountDown.setTextColor(Color.BLACK);
        }
    }

    private void finishQuestion() {
        ScoreTestHolder.score = Score;
        ScoreTestHolder.id = categoryID;
        finish();
    }

    private void init(){
        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCategory = findViewById(R.id.text_view_category);

        textViewCountDown = findViewById(R.id.text_view_countdown);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);

        buttonNext = findViewById(R.id.button_confirm_next);
    }
}