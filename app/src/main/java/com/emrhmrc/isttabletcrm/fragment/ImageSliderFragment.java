package com.emrhmrc.isttabletcrm.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GlideBindingAdapters;
import com.emrhmrc.isttabletcrm.helper.SlideInterface;
import com.emrhmrc.isttabletcrm.models.Product.Product;

public class ImageSliderFragment extends DialogFragment implements View.OnClickListener {

    private ImageView img, img_forward, img_next, img_close;
    private TextView txt_id, txt_info;
    private String id, info, img_resource;
    private SlideInterface slideInterface;
    private int position;


    public static ImageSliderFragment newInstance(Product product, int position) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putString("id", product.getProductNumber());
        args.putString("info", product.getName());
        args.putString("image", product.getImage());
        ImageSliderFragment fragment = new ImageSliderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cutomswipe_page_layout, container);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        txt_id = view.findViewById(R.id.txt_id);
        txt_info = view.findViewById(R.id.txt_info);
        img = view.findViewById(R.id.img_pic);
        img_next = view.findViewById(R.id.img_next);
        img_close = view.findViewById(R.id.img_close);
        img_forward = view.findViewById(R.id.img_forward);
        img_next.setOnClickListener(this);
        img_close.setOnClickListener(this);
        img_forward.setOnClickListener(this);
        id = getArguments().getString("id");
        position = getArguments().getInt("position");
        info = getArguments().getString("info");
        img_resource = getArguments().getString("image");
        setField(id, info, img_resource);
        slideInterface = (SlideInterface) getActivity();
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    private void setField(String id, String info, String img_resource) {

        txt_info.setText(info);
        txt_id.setText(id);
        GlideBindingAdapters.setImageResourceBase64(img, img_resource);
    }

    @Override
    public void onResume() {
        super.onResume();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = width / 2;
        params.height = height / 2;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_next:
                slideInterface.slideTo(position + 1);
                dismiss();
                break;
            case R.id.img_forward:
                slideInterface.slideTo(position - 1);
                dismiss();
                break;
            case R.id.img_close:
                dismiss();
                break;

        }

    }


}
