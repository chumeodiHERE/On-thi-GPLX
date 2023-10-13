package com.hltstudio.on_thi_gplx.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hltstudio.on_thi_gplx.R;
import com.hltstudio.on_thi_gplx.model.Meo;
import com.hltstudio.on_thi_gplx.model.RoadSign;
import com.hltstudio.on_thi_gplx.utilities.imageConverter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BienBaoAdapter extends RecyclerView.Adapter<BienBaoAdapter.BienBaoViewHolder>
{

    List<RoadSign> list;
    Context context;
    imageConverter converter;

    public BienBaoAdapter(List<RoadSign> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public BienBaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_list_bien_bao, parent, false);
        return new BienBaoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BienBaoViewHolder holder, int position) {
        RoadSign roadSign = list.get(position);
        converter = new imageConverter(roadSign.getIMAGENAME());
        holder.txtRoadSignName.setText(roadSign.getNAME());
        holder.txtRoadSignDescription.setText(roadSign.getDESCRIPTION());
        holder.imgRoadSign.setImageBitmap(converter.imageConverted());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BienBaoViewHolder extends RecyclerView.ViewHolder {

        TextView txtRoadSignName, txtRoadSignDescription;
        ImageView imgRoadSign;

        public BienBaoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRoadSignName = itemView.findViewById(R.id.txtRoadSignName);
            txtRoadSignDescription = itemView.findViewById(R.id.txtRoadSignDescription);
            imgRoadSign = itemView.findViewById(R.id.imgRoadSign);
        }
    }
}
