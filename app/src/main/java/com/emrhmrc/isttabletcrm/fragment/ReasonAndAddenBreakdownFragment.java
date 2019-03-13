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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvBreakdownTypeAdapter;
import com.emrhmrc.isttabletcrm.api.APIHelper;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakDownTypeListAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReasonAndAddenBreakdownFragment extends DialogFragment implements View.OnClickListener, OnItemClickListener {
    private static final String TAG = "DetailTechnicalFragment";

    private ImageView img_close;
    private JsonApi jsonApi;
    private RcvBreakdownTypeAdapter adapter;
    private RecyclerView rcv_reason;

    public static ReasonAndAddenBreakdownFragment newInstance() {

        Bundle args = new Bundle();
        ReasonAndAddenBreakdownFragment fragment = new ReasonAndAddenBreakdownFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.reason_and_added_breakdown_fragment, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        img_close = view.findViewById(R.id.img_close);
        rcv_reason = view.findViewById(R.id.rcv_reason);
        img_close.setOnClickListener(this);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        init();
        getAllReason();

    }

    private void getAllReason() {

        Call<BreakDownTypeListAll> call = jsonApi.breakDownTypeListAll();
        APIHelper.enqueueWithRetry(call, new Callback<BreakDownTypeListAll>() {
            @Override
            public void onResponse(Call<BreakDownTypeListAll> call, Response<BreakDownTypeListAll> response) {
                if (response.isSuccessful()) {

                    final BreakDownTypeListAll model = response.body();
                    adapter.setItems(model.getBreakdownTypes());

                } else Log.d(TAG, "onResponse: " + response.errorBody());
            }

            @Override
            public void onFailure(Call<BreakDownTypeListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    private void init() {
        rcv_reason.setHasFixedSize(true);
        rcv_reason.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RcvBreakdownTypeAdapter(getActivity(), this);
        adapter.setListener(this);
        rcv_reason.setAdapter(adapter);
        rcv_reason.setLayoutManager(new LinearLayoutManager(getActivity()));
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
                dismiss();
            default:
                ;
        }

    }

    @Override
    public void onItemClicked(Object item, int positon) {

    }
}
