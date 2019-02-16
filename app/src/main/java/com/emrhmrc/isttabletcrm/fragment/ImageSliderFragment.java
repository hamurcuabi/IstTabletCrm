package com.emrhmrc.isttabletcrm.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.emrhmrc.isttabletcrm.R;
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

    }

    private void setField(String id, String info, String img_resource) {

        txt_info.setText(info);
        txt_id.setText(id);
        RequestOptions option = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);
        Glide.with(getActivity())
                .setDefaultRequestOptions(option)
                .load(img_resource)
                .into(img);

    }

    @Override
    public void onResume() {
        super.onResume();

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
