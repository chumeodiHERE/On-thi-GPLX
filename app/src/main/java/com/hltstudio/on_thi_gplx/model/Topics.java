package com.hltstudio.on_thi_gplx.model;

public class Topics
{
    private int id;
    private String name, ispass;

    public Topics(){}
    public Topics(int id, String name, String ispass)
    {
        this.id = id;
        this.name = name;
        this.ispass = ispass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIspass() {
        return ispass;
    }

    public void setIspass(String ispass) {
        this.ispass = ispass;
    }

    @Override
    public String toString() {
        return getName();
    }
}
