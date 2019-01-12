package com.emrhmrc.isttabletcrm.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.emrhmrc.isttabletcrm.R;

public class BeforeAfterPicFragment extends DialogFragment implements View.OnClickListener {

    private ImageView img_close;
    private int w, h;

    public static BeforeAfterPicFragment newInstance(int w, int h) {
        Bundle args = new Bundle();
        args.putInt("w", w);
        args.putInt("h", h);
        BeforeAfterPicFragment fragment = new BeforeAfterPicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_before_after_pic, container);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        img_close = view.findViewById(R.id.img_close);
        img_close.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = w;
        params.height = h;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.img_close:
                this.dismiss();
                break;

        }

    }
}
