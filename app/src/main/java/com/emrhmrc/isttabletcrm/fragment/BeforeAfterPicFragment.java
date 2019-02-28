package com.emrhmrc.isttabletcrm.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import com.emrhmrc.isttabletcrm.R;

import static android.app.Activity.RESULT_OK;

public class BeforeAfterPicFragment extends DialogFragment implements View.OnClickListener {

    private static final int REQUEST_VIDEO_CAPTURE = 3;
    private static final int REQUEST_VIDEO_CAPTURE_SECOND = 4;
    private static final String TAG = "BeforeAfterPicFragment";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_CAPTURE_SECOND = 2;
    private ImageView img_close, img_first, img_second, img_add_pic_first, img_add_pic_second,
            img_add_video, img_add_video_second;
    private VideoView video_first, video_second;

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
        img_close = view.findViewById(R.id.img_close);
        img_first = view.findViewById(R.id.img_first);
        img_second = view.findViewById(R.id.img_second);
        img_add_pic_first = view.findViewById(R.id.img_add_pic_first);
        img_add_pic_second = view.findViewById(R.id.img_add_pic_second);
        video_first = view.findViewById(R.id.video_first);
        video_second = view.findViewById(R.id.video_second);
        img_add_video = view.findViewById(R.id.img_add_video);
        img_add_video_second = view.findViewById(R.id.img_add_video_second);
        img_close.setOnClickListener(this);
        img_add_pic_second.setOnClickListener(this);
        img_add_pic_first.setOnClickListener(this);
        img_add_video.setOnClickListener(this);
        img_add_video_second.setOnClickListener(this);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
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

            case R.id.img_close:
                this.dismiss();
                break;

            case R.id.img_add_pic_first:
                dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE);
                break;
            case R.id.img_add_pic_second:
                dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE_SECOND);
                break;
            case R.id.img_add_video:
                dispatchTakeVideoIntent(REQUEST_VIDEO_CAPTURE);
                break;
            case R.id.img_add_video_second:
                dispatchTakeVideoIntent(REQUEST_VIDEO_CAPTURE_SECOND);
                break;

        }

    }


    private void dispatchTakePictureIntent(int i) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, i);
        }
    }

    private void dispatchTakeVideoIntent(int i) {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, i);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            img_first.setImageBitmap(imageBitmap);
            video_first.setVisibility(View.GONE);
            img_first.setVisibility(View.VISIBLE);
        } else if (requestCode == REQUEST_IMAGE_CAPTURE_SECOND && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            img_second.setImageBitmap(imageBitmap);
            video_second.setVisibility(View.GONE);
            img_second.setVisibility(View.VISIBLE);
        } else if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            video_first.setVideoURI(videoUri);
            img_first.setVisibility(View.GONE);
            video_first.setVisibility(View.VISIBLE);
        } else if (requestCode == REQUEST_VIDEO_CAPTURE_SECOND && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            video_second.setVideoURI(videoUri);
            img_second.setVisibility(View.GONE);
            video_second.setVisibility(View.VISIBLE);
        }
    }
}
