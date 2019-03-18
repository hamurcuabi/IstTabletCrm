package com.emrhmrc.isttabletcrm.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.ServAppDescriptionAdapter;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdNotes;

import java.util.ArrayList;

public class ReasonOfBreakdownFragment extends DialogFragment implements View.OnClickListener, OnItemClickListener {

    private ImageView img_close;
    private RecyclerView rcv;
    private ArrayList<ServAppGetByIdNotes> list;
    private ServAppDescriptionAdapter adapter;

    public static ReasonOfBreakdownFragment newInstance(ArrayList<ServAppGetByIdNotes> list) {
        Bundle args = new Bundle();
        args.putSerializable("list", list);
        ReasonOfBreakdownFragment fragment = new ReasonOfBreakdownFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.reasonofbreakdown_fragment, container);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = (ArrayList<ServAppGetByIdNotes>) getArguments().getSerializable("list");
        rcv = view.findViewById(R.id.rcv);
        img_close = view.findViewById(R.id.img_close);
        adapter = new ServAppDescriptionAdapter(getActivity(), this);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcv.setAdapter(adapter);
        adapter.setItems(list);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
        img_close.setOnClickListener(view1 -> {
            dismiss();

        });
    }

    @Override
    public void onResume() {
        super.onResume();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = 9 * width / 10;
        params.height = 9 * height / 10;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {


        }
    }

    @Override
    public void onItemClicked(Object item, int positon) {

    }
}
