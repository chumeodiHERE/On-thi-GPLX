package com.hltstudio.on_thi_gplx.model;

public class Question {
    private int ID, GROUPID;
    private String TITLE, IMAGENAME, EXPLAINQUESTION, STATUS;

    public Question() {
    }

    public Question(int ID, int GROUPID, String TITLE, String IMAGENAME, String EXPLAINQUESTION, String STATUS) {
        this.ID = ID;
        this.GROUPID = GROUPID;
        this.TITLE = TITLE;
        this.IMAGENAME = IMAGENAME;
        this.EXPLAINQUESTION = EXPLAINQUESTION;
        this.STATUS = STATUS;
    }

    public Question(int GROUPID, String TITLE, String IMAGENAME, String EXPLAINQUESTION, String STATUS) {
        this.GROUPID = GROUPID;
        this.TITLE = TITLE;
        this.IMAGENAME = IMAGENAME;
        this.EXPLAINQUESTION = EXPLAINQUESTION;
        this.STATUS = STATUS;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getGROUPID() {
        return GROUPID;
    }

    public void setGROUPID(int GROUPID) {
        this.GROUPID = GROUPID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getEXPLAINQUESTION() {
        return EXPLAINQUESTION.replaceAll("\\\\n", "\\\n");
    }

    public void setEXPLAINQUESTION(String EXPLAINQUESTION) {
        this.EXPLAINQUESTION = EXPLAINQUESTION;
    }

    public String getIMAGENAME() {
        return IMAGENAME;
    }

    public void setIMAGENAME(String IMAGENAME) {
        this.IMAGENAME = IMAGENAME;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
}
