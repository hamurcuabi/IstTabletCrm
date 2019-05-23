package com.emrhmrc.isttabletcrm.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;
import com.emrhmrc.isttabletcrm.R;

public class FullScreenImageFragment extends DialogFragment {

    private Uri uri;

    public static FullScreenImageFragment newInstance(Uri uri) {

        Bundle args = new Bundle();
        args.putParcelable("uri", uri);
        FullScreenImageFragment fragment = new FullScreenImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fullscreen_image_frag, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = view.findViewById(R.id.img);
        imageView.setImageURI((Uri) getArguments().getParcelable("uri"));
        imageView.setOnTouchListener(new ImageMatrixTouchHandler(view.getContext()));
    }
}
