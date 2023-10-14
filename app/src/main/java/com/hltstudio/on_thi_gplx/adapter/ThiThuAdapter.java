package com.hltstudio.on_thi_gplx.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.hltstudio.on_thi_gplx.R;
import com.hltstudio.on_thi_gplx.activity.TestQuestionsActivity;
import com.hltstudio.on_thi_gplx.model.TESTQUESTIONS;
import com.hltstudio.on_thi_gplx.model.Topics;
import com.hltstudio.on_thi_gplx.utilities.DbHelper;

import java.util.ArrayList;

public class ThiThuAdapter extends BaseAdapter
{
    Context context;
    ArrayList<Topics> list;
    private static final int REQUEST_CODE_QUESTION = 1;

    public ThiThuAdapter(Context context, ArrayList<Topics> list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        convertView = LayoutInflater.from(context).inflate(R.layout.layout_row_list_topic, parent, false);
        Topics topic = list.get(position);
        DbHelper helper = new DbHelper(context);
        ArrayList<TESTQUESTIONS> testquestions = helper.getQuestions(topic.getId());

        TextView txtTopicQuesNumber = convertView.findViewById(R.id.txtTopicQuesNumber);
        TextView txtTopicName = convertView.findViewById(R.id.txtTopicName);
        TextView txtIsPassTest = convertView.findViewById(R.id.txtIsPassTest);
        Button btn_start_test = convertView.findViewById(R.id.btn_start_test);

        txtTopicQuesNumber.setText(testquestions.size() + " câu hỏi");
        txtTopicName.setText(topic.getName());
        if (topic.getIspass() != null)
        {
            if (topic.getIspass().equals("true"))
            {
                txtIsPassTest.setText("ĐẬU");
                txtIsPassTest.setTextColor(Color.rgb(0, 191, 0));
            } else if (topic.getIspass().equals("false")) {
                txtIsPassTest.setText("RỚT");
                txtIsPassTest.setTextColor(Color.RED);
            }
        }
        btn_start_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TestQuestionsActivity.class);
                intent.putExtra("Id", topic.getId());
                intent.putExtra("Name", topic.getName());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
