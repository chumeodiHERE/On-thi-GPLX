package com.hltstudio.on_thi_gplx.utilities;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hltstudio.on_thi_gplx.model.Answer;
import com.hltstudio.on_thi_gplx.model.GroupQuestion;
import com.hltstudio.on_thi_gplx.model.Question;
import com.hltstudio.on_thi_gplx.model.RoadSign;
import com.hltstudio.on_thi_gplx.model.TESTQUESTIONS;
import com.hltstudio.on_thi_gplx.model.Topics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DbHelper
{
    Context context;
    private final String dbName = "GPLX.db";
    private final String DB_PATH = "/databases/";

    public DbHelper(Context context) {
        this.context = context;
    }

    private SQLiteDatabase openDB()
    {
        return context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
    }

    private void closeDB(SQLiteDatabase db) {
        db.close();
    }

    private String getDatabasePath()
    {
        return context.getApplicationInfo().dataDir + DB_PATH + dbName;
    }

    public void copyDatabaseFromAssets()
    {
        try
        {
            InputStream myInput;
            //Lấy database đưa vào myInput
            myInput = context.getAssets().open(dbName);
            //Lấy đường dẫn lưu trữ để đưa myInput vào
            String outFileName = getDatabasePath();

            File f = new File(context.getApplicationInfo().dataDir + DB_PATH);
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

    public void initDatabase()
    {
        File dbFile = context.getDatabasePath(dbName);
        if(!dbFile.exists())
        {
            copyDatabaseFromAssets();
        }
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

    public ArrayList<Topics> getDataCategory()
    {
        SQLiteDatabase db = openDB();
        ArrayList<Topics> list = new ArrayList<>();
        String sql = "Select * from TOPICS";
        Cursor csr = db.rawQuery(sql,null);
        if(csr.moveToFirst()){
            do{
                int id = csr.getInt(0);
                String name = csr.getString(1);
                String ispass = csr.getString(2);
                list.add(new Topics(id, name, ispass));
            }while (csr.moveToNext());
        }
        closeDB(db);
        return list;
    }

    public ArrayList<TESTQUESTIONS> getQuestions(int ID)
    {
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

    public void setFalseIsPassTopic(int ID)
    {
        SQLiteDatabase db = openDB();
        db.execSQL("Update TOPICS Set ISPASS = 'false' where ID = " + ID);
        closeDB(db);
    }
    public void setTrueIsPassTopic(int ID)
    {
        SQLiteDatabase db = openDB();
        db.execSQL("Update TOPICS Set ISPASS = 'true' where ID = " + ID);
        closeDB(db);
    }
}
