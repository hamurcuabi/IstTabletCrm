package com.emrhmrc.isttabletcrm.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.SweetDialog.DialogCreater;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvElevatorOmurluAdapter;
import com.emrhmrc.isttabletcrm.api.APIHelper;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorIdRequest;
import com.emrhmrc.isttabletcrm.models.Elevator.PeriodicalProductListAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragOmurluParcalar extends Fragment implements OnItemClickListener {


    RecyclerView rcv;
    private JsonApi jsonApi;
    private RcvElevatorOmurluAdapter adapter;
    private Call<PeriodicalProductListAll> call;

    public static FragOmurluParcalar newInstance() {

        Bundle args = new Bundle();

        FragOmurluParcalar fragment = new FragOmurluParcalar();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_omurlu, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv = view.findViewById(R.id.rcv);
        adapter = new RcvElevatorOmurluAdapter(getActivity(), this);
        init();
        getList();

    }

    private void getList() {
        call = jsonApi.periodicalProductListAllCall(new ElevatorIdRequest(ShareData
                .getInstance().getElevatorId()));
        APIHelper.enqueueWithRetry(call, new Callback<PeriodicalProductListAll>() {
            @Override
            public void onResponse(Call<PeriodicalProductListAll> call, Response<PeriodicalProductListAll> response) {

                if (response.isSuccessful()) {
                    if (response.body().Success) {
                        final PeriodicalProductListAll model = response.body();
                        adapter.setItems(model.getPeriodicalProducts());

                    }
                } else {
                    DialogCreater.errorDialog(getActivity(), response.body().ErrorMsg);
                }

            }

            @Override
            public void onFailure(Call<PeriodicalProductListAll> call, Throwable t) {
                DialogCreater.errorDialog(getActivity(), getString(R.string.try_again));

            }
        });
    }


    private void init() {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcv.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(Object item, int positon) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (call != null) call.cancel();
    }
}
