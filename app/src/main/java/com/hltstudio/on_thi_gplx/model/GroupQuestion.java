package com.hltstudio.on_thi_gplx.model;

public class GroupQuestion {
    private int ID;
    private String NAME, IMAGENAME;

    public GroupQuestion() {
    }

    public GroupQuestion(String NAME, String IMAGENAME) {
        this.NAME = NAME;
        this.IMAGENAME = IMAGENAME;
    }

    public GroupQuestion(int ID, String NAME, String IMAGENAME) {
        this.ID = ID;
        this.NAME = NAME;
        this.IMAGENAME = IMAGENAME;
    }

    public GroupQuestion(String NAME) {
        this.NAME = NAME;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getIMAGENAME() {
        return IMAGENAME;
    }

    public void setIMAGENAME(String IMAGENAME) {
        this.IMAGENAME = IMAGENAME;
    }
}
