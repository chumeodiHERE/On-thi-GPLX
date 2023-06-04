package com.hltstudio.on_thi_gplx.activity;

import com.hltstudio.on_thi_gplx.R;

import java.util.ArrayList;

public class PhotoViewPager {

    private int photoId;

    public PhotoViewPager() {
    }

    public PhotoViewPager(int photoId) {
        this.photoId = photoId;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    protected ArrayList<PhotoViewPager> initPhotoViewPageA1(){
        ArrayList<PhotoViewPager> a1 = new ArrayList<>();
        a1.add(new PhotoViewPager(R.drawable.a1));
        a1.add(new PhotoViewPager(R.drawable.luatduongbo));
        a1.add(new PhotoViewPager(R.drawable.luatduongbo2));
        return a1;
    }
    protected ArrayList<PhotoViewPager> initPhotoViewPageA2(){
        ArrayList<PhotoViewPager> a2 = new ArrayList<>();
        a2.add(new PhotoViewPager(R.drawable.a2));
        a2.add(new PhotoViewPager(R.drawable.luatduongbo));
        a2.add(new PhotoViewPager(R.drawable.luatduongbo2));
        return a2;
    }
}
