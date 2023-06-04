package com.hltstudio.on_thi_gplx.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hltstudio.on_thi_gplx.R;
import com.hltstudio.on_thi_gplx.model.Meo;

import java.util.List;

public class MeoAdapter extends RecyclerView.Adapter<MeoAdapter.MeoViewHolder> {

    List<Meo> list;

    public MeoAdapter(List<Meo> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MeoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_meo_thi, parent, false);
        return new MeoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MeoViewHolder holder, int position) {
        Meo meo = list.get(position);
        holder.txtViewNameMeo.setText(meo.getTenMeo());
        holder.txtViewNoiDungMeo.setText(meo.getNoiDung());

        boolean isExpand = list.get(position).isExpandable();
        holder.relativeLayout.setVisibility(isExpand ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MeoViewHolder extends RecyclerView.ViewHolder {

        TextView txtViewNameMeo, txtViewNoiDungMeo;
        LinearLayout linearLayout;
        RelativeLayout relativeLayout;

        public MeoViewHolder(View view) {
            super(view);

            txtViewNameMeo = view.findViewById(R.id.txtViewNameMeo);
            txtViewNoiDungMeo = view.findViewById(R.id.txtViewNoiDungMeo);
            linearLayout = view.findViewById(R.id.row_meo);
            relativeLayout = view.findViewById(R.id.expand_layout_noidung_meo);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Meo meo = list.get(getAdapterPosition());
                    meo.setExpandable(!meo.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
