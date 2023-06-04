package com.hltstudio.on_thi_gplx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.hltstudio.on_thi_gplx.activity.PhotoViewPager;
import com.hltstudio.on_thi_gplx.R;

import java.util.ArrayList;

public class PhotoViewPagerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<PhotoViewPager> arrayList;

    public PhotoViewPagerAdapter(Context context, ArrayList<PhotoViewPager> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_img_viewpager,container,false);
        ImageView imageView = view.findViewById(R.id.imgPhotoViewPager);
        PhotoViewPager photoViewPager = arrayList.get(position);
        if(photoViewPager != null){
            Glide.with(context).load(photoViewPager.getPhotoId()).into(imageView);
        }
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(arrayList!=null){
            return arrayList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
