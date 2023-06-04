package com.hltstudio.on_thi_gplx.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.hltstudio.on_thi_gplx.R;
import com.hltstudio.on_thi_gplx.adapter.BienBaoAdapter;
import com.hltstudio.on_thi_gplx.controller.DbHelper;
import com.hltstudio.on_thi_gplx.model.RoadSign;

import java.util.List;

public class BienChiDanFragment extends Fragment {
    private Context mContext;
    RecyclerView recyclerView;
    List<RoadSign> list;
    DbHelper helper;
    BienBaoAdapter adapter;

    public BienChiDanFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        helper = new DbHelper(mContext);
        return inflater.inflate(R.layout.fragment_bien_chi_dan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.listBienChiDan);
        list = helper.getRSWithRSID(2);
        adapter = new BienBaoAdapter(list, mContext);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }
}