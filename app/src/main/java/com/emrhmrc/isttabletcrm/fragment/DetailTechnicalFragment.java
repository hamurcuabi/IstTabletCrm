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
import com.emrhmrc.isttabletcrm.models.Document.TechnicDocument;
import com.emrhmrc.isttabletcrm.util.StringUtil;

public class DetailTechnicalFragment extends DialogFragment implements View.OnClickListener {
    private static final String TAG = "DetailTechnicalFragment";
    private ImageView img_close;
    private TextView txt_header, txt_content;
    private TechnicDocument document;

    public static DetailTechnicalFragment newInstance(TechnicDocument technicDocument) {

        Bundle args = new Bundle();
        args.putSerializable("obj", technicDocument);
        DetailTechnicalFragment fragment = new DetailTechnicalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_technical_fragment, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        img_close = view.findViewById(R.id.img_close);
        img_close.setOnClickListener(this);
        txt_header = view.findViewById(R.id.txt_header);
        txt_content = view.findViewById(R.id.txt_content);

        document = (TechnicDocument) getArguments().getSerializable("obj");
        txt_header.setText(StringUtil.nullToString(document.getInv_TechnicalDocumentName()));
        txt_content.setText(StringUtil.base64ToString(StringUtil.nullToString(document.getDocumentBody())));

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
        params.width = width / 2;
        params.height = height/2;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_close:
                dismiss();
            default:
                ;
        }

    }
}
