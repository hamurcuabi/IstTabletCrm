package com.emrhmrc.isttabletcrm.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.api.APIHelper;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.models.ServApp.GetServFormById;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppIdRequest;
import com.emrhmrc.isttabletcrm.util.StringUtil;

import butterknife.BindString;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailServAppFormFragment extends DialogFragment implements View.OnClickListener {
    private static final String TAG = "DetailTechnicalFragment";
    @BindString(R.string.aciklama)
    String header;
    private ImageView img_close;
    private TextView txt_header, txt_content;
    private GetServFormById document;
    private JsonApi jsonApi;
    private String id;

    public static DetailServAppFormFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString("id", id);
        DetailServAppFormFragment fragment = new DetailServAppFormFragment();
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
        txt_header.setText(header);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        Call<GetServFormById> call = jsonApi.getServFormById(new ServiceAppIdRequest(id));
        APIHelper.enqueueWithRetry(call, new Callback<GetServFormById>() {
            @Override
            public void onResponse(Call<GetServFormById> call, Response<GetServFormById> response) {
                if (response.isSuccessful()) {
                    document = response.body();
                    txt_content.setText(StringUtil.base64ToString(StringUtil.nullToString(document.getDocumentBody())));
                }
            }

            @Override
            public void onFailure(Call<GetServFormById> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

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
            case R.id.img_close:
                dismiss();
            default:
                ;
        }

    }
}
