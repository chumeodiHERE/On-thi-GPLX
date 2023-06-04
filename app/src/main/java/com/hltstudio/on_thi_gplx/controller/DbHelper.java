package com.hltstudio.on_thi_gplx.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hltstudio.on_thi_gplx.model.Answer;
import com.hltstudio.on_thi_gplx.model.GroupQuestion;
import com.hltstudio.on_thi_gplx.model.Question;
import com.hltstudio.on_thi_gplx.model.RoadSign;
import com.hltstudio.on_thi_gplx.model.RoadSignGroup;
import com.hltstudio.on_thi_gplx.model.TESTQUESTIONS;
import com.hltstudio.on_thi_gplx.model.Topics;

import java.util.ArrayList;
import java.util.List;

public class DbHelper {
    Context context;
    public static final String dbName = "GPLX.db";
    public DbHelper(Context context) {
        this.context = context;
    }
    private SQLiteDatabase openDB() {
        return context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
    }
    private void closeDB(SQLiteDatabase db) {
        db.close();
    }


    public ArrayList<RoadSign> getRSWithRSID(int RSGID)
    {
        SQLiteDatabase db = openDB();
        ArrayList<RoadSign> arr = new ArrayList<>();
        String sql = "select * from ROADSIGN where RSGID = " + RSGID;
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int rsgid = csr.getInt(1);
                    String name = csr.getString(2);
                    String des = csr.getString(3);
                    String imgname = csr.getString(4);
                    arr.add(new RoadSign(rsgid ,name, des, imgname));

                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }

    public ArrayList<GroupQuestion> getAllGQ()
    {
        SQLiteDatabase db = openDB();
        ArrayList<GroupQuestion> arr = new ArrayList<>();
        String sql = "select * from GROUPQUESTION";
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int id = csr.getInt(0);
                    String name = csr.getString(1);
                    String imageName = csr.getString(2);
                    arr.add(new GroupQuestion(id,name, imageName));
                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }

    public ArrayList<Question> getAllQuestion()
    {
        SQLiteDatabase db = openDB();
        ArrayList<Question> arr = new ArrayList<>();
        String sql = "Select * from QUESTION";
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int id = csr.getInt(0);
                    int gqid = csr.getInt(1);
                    String title = csr.getString(2);
                    String imagename = csr.getString(3);
                    String explain = csr.getString(4);
                    String status = csr.getString(5);

                    arr.add(new Question(id ,gqid, title, imagename, explain, status));

                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }

    public ArrayList<Question> getQuestionWithGQId(int idGroup)
    {
        SQLiteDatabase db = openDB();
        ArrayList<Question> arr = new ArrayList<>();
        String sql = "Select * from QUESTION where GROUPID = " + idGroup;
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int id = csr.getInt(0);
                    int gqid = csr.getInt(1);
                    String title = csr.getString(2);
                    String imagename = csr.getString(3);
                    String explain = csr.getString(4);
                    String status = csr.getString(5);
                    arr.add(new Question(id ,gqid, title, imagename, explain, status));
                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }

    public ArrayList<Question> getQuestionIsDoneWithGQId(int idGroup)
    {
        SQLiteDatabase db = openDB();
        ArrayList<Question> arr = new ArrayList<>();
        String sql = "Select * from QUESTION where STATUS = 'true' and GROUPID = " + idGroup;
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int id = csr.getInt(0);
                    int gqid = csr.getInt(1);
                    String title = csr.getString(2);
                    String des = csr.getString(3);
                    String explain = csr.getString(4);
                    String imagename = csr.getString(5);
                    arr.add(new Question(id ,gqid, title, des, explain, imagename));

                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }

    public ArrayList<Answer> getAnswer(int idquest)
    {
        SQLiteDatabase db = openDB();
        ArrayList<Answer> arr = new ArrayList<>();
        String sql = "Select * from ANSWER where QUESTIONID = " + idquest;
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int id = csr.getInt(0);
                    int questid = csr.getInt(1);
                    String cont = csr.getString(2);
                    int check = csr.getInt(3);
                    String ischoose = csr.getString(4);
                    boolean correctcheck = false;
                    if(check == 1)
                        correctcheck = true;
                    arr.add(new Answer(id ,questid, cont, correctcheck, ischoose));

                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }

    public void setDoneQuestion(int questionid)
    {
        SQLiteDatabase db = openDB();
        db.execSQL("Update QUESTION Set STATUS = 'true' where ID = " + questionid);
        closeDB(db);
    }
    public void setFalseAllAnswer(int idQuestion)
    {
        SQLiteDatabase db = openDB();
        db.execSQL("Update ANSWER Set ISCHOOSE = 'false' where QUESTIONID = " + idQuestion);
        closeDB(db);
    }
    public void setIsChooseAnswer(int idAnswer, int idQuestion)
    {
        SQLiteDatabase db = openDB();
        db.execSQL("Update ANSWER Set ISCHOOSE = 'true' where ID = " + idAnswer + " and QUESTIONID = " + idQuestion);
        closeDB(db);
    }
    public void changeTruetoFalseQues(int idQuestion)
    {
        SQLiteDatabase db = openDB();
        db.execSQL("Update QUESTION Set STATUS = 'false' where ID = " + idQuestion);
        closeDB(db);
        closeDB(db);
    }
    public void changeTruetoFalseAnsw(int idAnswer)
    {
        SQLiteDatabase db = openDB();
        db.execSQL("Update ANSWER Set ISCHOOSE = 'false' where ID = " + idAnswer);
        closeDB(db);
    }

    public List<Topics> getDataCategory(){
        SQLiteDatabase db = openDB();
        List<Topics> list = new ArrayList<>();
        String sql = "Select * from TOPICS";
        Cursor csr = db.rawQuery(sql,null);
        if(csr.moveToFirst()){
            do{
                int id = csr.getInt(0);
                String name = csr.getString(1);
                list.add(new Topics(id,name));
            }while (csr.moveToNext());
        }
        closeDB(db);
        return list;
    }

    public ArrayList<TESTQUESTIONS> getQuestions(int ID){
        ArrayList<TESTQUESTIONS> arr = new ArrayList<>();
        String sql = "Select * from TESTQUESTIONS where ID_TOPICS = " + ID;
        SQLiteDatabase db = openDB();
        Cursor csr = db.rawQuery(sql,null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int id = csr.getInt(0);
                    String question = csr.getString(1);
                    String option1 = csr.getString(2);
                    String option2 = csr.getString(3);
                    String option3 = csr.getString(4);
                    int answer = csr.getInt(5);
                    int id_topics = csr.getInt(6);
                    arr.add(new TESTQUESTIONS(id,question,option1,option2,option3,answer,id_topics));
                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }
}
