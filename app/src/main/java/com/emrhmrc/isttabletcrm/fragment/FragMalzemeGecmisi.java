package com.emrhmrc.isttabletcrm.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.SweetDialog.DialogCreater;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvChangingPartAdapter;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorChangingPart;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorIdRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragMalzemeGecmisi extends Fragment implements OnItemClickListener {

    private static final String TAG = "FragMalzemeGecmisi";

    RecyclerView rcv;
    private JsonApi jsonApi;
    private RcvChangingPartAdapter adapter;
    private Call<ElevatorChangingPart> call;

    public static FragMalzemeGecmisi newInstance() {

        Bundle args = new Bundle();

        FragMalzemeGecmisi fragment = new FragMalzemeGecmisi();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_malzeme_gecmisi, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv = view.findViewById(R.id.rcv);
        init();
        getList();
    }

    private void getList() {
        call = jsonApi.getElevatorChangingPartCall(new ElevatorIdRequest(ShareData
                .getInstance().getElevatorId()));
        call.enqueue(new Callback<ElevatorChangingPart>() {
            @Override
            public void onResponse(Call<ElevatorChangingPart> call, Response<ElevatorChangingPart> response) {
                if (response.isSuccessful()) {
                    final ElevatorChangingPart model = response.body();
                    adapter.setItems(model.getElevatorChangingParts());
                } else Log.d(TAG, "onResponse: " + response.message());
            }

            @Override
            public void onFailure(Call<ElevatorChangingPart> call, Throwable t) {
                DialogCreater.errorDialog(getActivity(), t.getMessage());
            }
        });
    }

    private void init() {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        adapter = new RcvChangingPartAdapter(getActivity(), this);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcv.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (call != null) call.cancel();
    }

    @Override
    public void onItemClicked(Object item, int positon) {

    }
}
