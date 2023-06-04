package com.hltstudio.on_thi_gplx.model;

public class Answer {
    private int ID, QUESTIONID;
    private String CONTENT;
    private boolean CORRECTCHECK;
    private String ISCHOOSE;

    public Answer(int ID, int QUESTIONID, String CONTENT, boolean CORRECTCHECK) {
        this.ID = ID;
        this.QUESTIONID = QUESTIONID;
        this.CONTENT = CONTENT;
        this.CORRECTCHECK = CORRECTCHECK;
    }

    public Answer(int ID, int QUESTIONID, String CONTENT, boolean CORRECTCHECK, String ISCHOOSE) {
        this.ID = ID;
        this.QUESTIONID = QUESTIONID;
        this.CONTENT = CONTENT;
        this.CORRECTCHECK = CORRECTCHECK;
        this.ISCHOOSE = ISCHOOSE;
    }

    public Answer() {
    }

    public Answer(int QUESTIONID, String CONTENT, boolean CORRECTCHECK) {
        this.QUESTIONID = QUESTIONID;
        this.CONTENT = CONTENT;
        this.CORRECTCHECK = CORRECTCHECK;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getQUESTIONID() {
        return QUESTIONID;
    }

    public void setQUESTIONID(int QUESTIONID) {
        this.QUESTIONID = QUESTIONID;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public boolean isCORRECTCHECK() {
        return CORRECTCHECK;
    }

    public void setCORRECTCHECK(boolean CORRECTCHECK) {
        this.CORRECTCHECK = CORRECTCHECK;
    }

    public String getISCHOOSE() {
        return ISCHOOSE;
    }

    public void setISCHOOSE(String ISCHOOSE) {
        this.ISCHOOSE = ISCHOOSE;
    }
}
