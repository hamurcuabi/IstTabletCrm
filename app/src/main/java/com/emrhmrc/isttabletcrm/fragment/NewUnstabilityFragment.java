package com.emrhmrc.isttabletcrm.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.models.ServApp.CreateUnsuitability;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse2;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewUnstabilityFragment extends DialogFragment implements View.OnClickListener {

    private static final String TAG = "NewUnstabilityFragment";
    private ImageView img_close;
    private Button btn_send;
    private JsonApi jsonApi;

    public static NewUnstabilityFragment newInstance() {
        Bundle args = new Bundle();
        NewUnstabilityFragment fragment = new NewUnstabilityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.newunstability_fragment, container);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        btn_send = view.findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                send();
                break;

        }

    }

    private void send() {
        CreateUnsuitability createUnsuitability = new CreateUnsuitability();
        createUnsuitability.setUserId(SingletonUser.getInstance().getUser().getUserId());
        createUnsuitability.setDescription("Test Description");
        createUnsuitability.setSentOn(Calendar.getInstance().getTime());
        createUnsuitability.setSubject("Test Subject");
        createUnsuitability.setServAppId(ShareData.getInstance().getServAppId());
        Call<DefaultResponse2> call = jsonApi.createUnsuitabilityCall(createUnsuitability);
        call.enqueue(new Callback<DefaultResponse2>() {
            @Override
            public void onResponse(Call<DefaultResponse2> call, Response<DefaultResponse2> response) {
              if(response.isSuccessful()){
                  Log.d(TAG, "onResponse: Succes");
              }
              else Log.d(TAG, "onResponse: Fail");
            }

            @Override
            public void onFailure(Call<DefaultResponse2> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}
