package com.hltstudio.on_thi_gplx.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hltstudio.on_thi_gplx.R;
import com.hltstudio.on_thi_gplx.controller.DbHelper;
import com.hltstudio.on_thi_gplx.model.GroupQuestion;
import com.hltstudio.on_thi_gplx.model.Question;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class HocLyThuyetAdapter extends BaseAdapter {
    Context context;
    ArrayList<GroupQuestion>list;


    public HocLyThuyetAdapter(Context context, ArrayList<GroupQuestion> list) {
        this.context = context;
        this.list = list;
    }


    public Bitmap convertStringToBitmapFromAccess(String filename){
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open(filename); Bitmap bitmap = BitmapFactory.decodeStream(is); return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.layout_row_hoc_ly_thuyet, viewGroup,false);
        GroupQuestion question = list.get(i);
        DbHelper helper = new DbHelper(context);
        ArrayList<Question> listQuest = helper.getQuestionWithGQId(question.getID());
        ArrayList<Question> listQuestIsDone = helper.getQuestionIsDoneWithGQId(question.getID());

        ImageView imgQuestionGroup = view.findViewById(R.id.imgQuestionGroup);
        TextView txtNameQuestionGroup = view.findViewById(R.id.txtNameQuestionGroup);
        TextView txtNumberQuestion = view.findViewById(R.id.txtNumberQuestion);
        TextView txtNumbAllQuestionProgress = view.findViewById(R.id.txtNumbAllQuestion);
        TextView txtNumbQuestIsDone = view.findViewById(R.id.txtNumbQuestIsDone);
        ProgressBar progress_question = view.findViewById(R.id.progress_question);

        imgQuestionGroup.setImageBitmap(convertStringToBitmapFromAccess(question.getIMAGENAME()));
        txtNameQuestionGroup.setText(question.getNAME());
        txtNumberQuestion.setText("Gồm " + listQuest.size() + " câu hỏi");
        txtNumbQuestIsDone.setText(listQuestIsDone.size() + " ");
        txtNumbAllQuestionProgress.setText("/ " + listQuest.size());
        progress_question.setMax(listQuest.size());
        progress_question.setProgress(listQuestIsDone.size());

        return view;
    }
}
