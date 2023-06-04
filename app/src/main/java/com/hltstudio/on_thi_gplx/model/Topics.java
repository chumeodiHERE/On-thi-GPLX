package com.hltstudio.on_thi_gplx.model;

public class Topics {
    private int id;
    private String name;
    public Topics(){}
    public Topics(int id, String name) {
        this.id = id;
        this.name = name;
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
    @Override
    public String toString() {
        return getName();
    }
}
