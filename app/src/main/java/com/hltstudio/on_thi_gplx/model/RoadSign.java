package com.hltstudio.on_thi_gplx.model;

public class RoadSign {
    private int ID, RSID;
    private String NAME, DESCRIPTION, IMAGENAME;

    public RoadSign() {
    }

    public RoadSign(int ID, int RSID, String NAME, String DESCRIPTION, String IMAGENAME) {
        this.ID = ID;
        this.RSID = RSID;
        this.NAME = NAME;
        this.DESCRIPTION = DESCRIPTION;
        this.IMAGENAME = IMAGENAME;
    }

    public RoadSign(int RSID, String NAME, String DESCRIPTION, String IMAGENAME) {
        this.RSID = RSID;
        this.NAME = NAME;
        this.DESCRIPTION = DESCRIPTION;
        this.IMAGENAME = IMAGENAME;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getRSID() {
        return RSID;
    }

    public void setRSID(int RSID) {
        this.RSID = RSID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getIMAGENAME() {
        return IMAGENAME;
    }

    public void setIMAGENAME(String IMAGENAME) {
        this.IMAGENAME = IMAGENAME;
    }
}
