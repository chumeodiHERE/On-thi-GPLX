package com.hltstudio.on_thi_gplx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hltstudio.on_thi_gplx.R;
import com.hltstudio.on_thi_gplx.model.Answer;

import java.util.List;

public class AnswerAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Answer> list;


    public AnswerAdapter(Context context, int layout, List<Answer> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataHolder dataHolder;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(layout, null);
            dataHolder = new DataHolder();

            //AnhXa
            dataHolder.numbrow = (ImageView) convertView.findViewById(R.id.numbrow);
            dataHolder.tvAnswer = (TextView) convertView.findViewById(R.id.tvAnswer);
            dataHolder.imgstatus = (ImageView) convertView.findViewById(R.id.imgstatus);

            convertView.setTag(dataHolder);
        }
        else {
            dataHolder = (DataHolder) convertView.getTag();
        }

        Answer answer = list.get(position);

        dataHolder.tvAnswer.setText(answer.getCONTENT());

        if (answer.getISCHOOSE().equals("true")) {
            if (answer.isCORRECTCHECK()) {
                dataHolder.tvAnswer.setTextColor(context.getResources().getColor(R.color.true_answer));
            } else if (!answer.isCORRECTCHECK()) {
                dataHolder.tvAnswer.setTextColor(context.getResources().getColor(R.color.false_answer));
            }
        } else {
            dataHolder.tvAnswer.setTextColor(context.getResources().getColor(R.color.black));
        }

        switch (position)
        {
            case 0:
                Answer an0 = list.get(0);
                if (an0.getISCHOOSE().equals("true")) {
                    if (an0.isCORRECTCHECK()) {
                        dataHolder.numbrow.setImageResource(R.drawable.number_1_true);
                        dataHolder.imgstatus.setVisibility(View.VISIBLE);
                        dataHolder.imgstatus.setImageResource(R.drawable.baseline_check_24_green);
                    } else if (!an0.isCORRECTCHECK()) {
                        dataHolder.numbrow.setImageResource(R.drawable.number_1_false);
                        dataHolder.imgstatus.setVisibility(View.VISIBLE);
                        dataHolder.imgstatus.setImageResource(R.drawable.baseline_clear_24_red);
                    }
                } else {
                    dataHolder.numbrow.setImageResource(R.drawable.number_1_normal);
                    dataHolder.imgstatus.setVisibility(View.GONE);
                }
                break;
            case 1:
                Answer an1 = list.get(1);
                if (an1.getISCHOOSE().equals("true")) {
                    if (an1.isCORRECTCHECK()) {
                        dataHolder.numbrow.setImageResource(R.drawable.number_2_true);
                        dataHolder.imgstatus.setVisibility(View.VISIBLE);
                        dataHolder.imgstatus.setImageResource(R.drawable.baseline_check_24_green);
                    } else if (!an1.isCORRECTCHECK()) {
                        dataHolder.numbrow.setImageResource(R.drawable.number_2_false);
                        dataHolder.imgstatus.setVisibility(View.VISIBLE);
                        dataHolder.imgstatus.setImageResource(R.drawable.baseline_clear_24_red);
                    }
                } else {
                    dataHolder.numbrow.setImageResource(R.drawable.number_2_normal);
                    dataHolder.imgstatus.setVisibility(View.GONE);
                }
                break;
            case 2:
                Answer an2 = list.get(2);
                if (an2.getISCHOOSE().equals("true")) {
                    if (an2.isCORRECTCHECK()) {
                        dataHolder.numbrow.setImageResource(R.drawable.number_3_true);
                        dataHolder.imgstatus.setVisibility(View.VISIBLE);
                        dataHolder.imgstatus.setImageResource(R.drawable.baseline_check_24_green);
                    } else if (!an2.isCORRECTCHECK()) {
                        dataHolder.numbrow.setImageResource(R.drawable.number_3_false);
                        dataHolder.imgstatus.setVisibility(View.VISIBLE);
                        dataHolder.imgstatus.setImageResource(R.drawable.baseline_clear_24_red);
                    }
                } else {
                    dataHolder.numbrow.setImageResource(R.drawable.number_3_normal);
                    dataHolder.imgstatus.setVisibility(View.GONE);
                }
                break;
            case 3:
                Answer an3 = list.get(3);
                if (an3.getISCHOOSE().equals("true")) {
                    if (an3.isCORRECTCHECK()) {
                        dataHolder.numbrow.setImageResource(R.drawable.number_4_true);
                        dataHolder.imgstatus.setVisibility(View.VISIBLE);
                        dataHolder.imgstatus.setImageResource(R.drawable.baseline_check_24_green);
                    } else if (!an3.isCORRECTCHECK()) {
                        dataHolder.numbrow.setImageResource(R.drawable.number_4_false);
                        dataHolder.imgstatus.setVisibility(View.VISIBLE);
                        dataHolder.imgstatus.setImageResource(R.drawable.baseline_clear_24_red);
                    }
                } else {
                    dataHolder.numbrow.setImageResource(R.drawable.number_4_normal);
                    dataHolder.imgstatus.setVisibility(View.GONE);
                }
                break;
        }
        return convertView;
    }

    public class DataHolder
    {
        ImageView numbrow;
        TextView tvAnswer;
        ImageView imgstatus;
    }
}
