package com.emrhmrc.isttabletcrm.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.emrhmrc.isttabletcrm.R;

public class BeforeAfterPicFragment extends DialogFragment implements View.OnClickListener {

    private ImageView img_close;
    private LinearLayout lnr;

    public static BeforeAfterPicFragment newInstance() {
        Bundle args = new Bundle();
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
       /* img_close = view.findViewById(R.id.img_close);
        img_close.setOnClickListener(this);*/
      //  lnr = view.findViewById(R.id.lnrfrag);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

          /*  case R.id.img_close:
                this.dismiss();
                break;*/

        }

    }
}
