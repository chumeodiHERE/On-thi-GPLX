package com.hltstudio.on_thi_gplx.model;

public class RoadSignGroup {
    int ID;
    String NAME;

    public RoadSignGroup(String NAME) {
        this.NAME = NAME;
    }

    public RoadSignGroup() {
    }

    public RoadSignGroup(int ID, String NAME) {
        this.ID = ID;
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
}
