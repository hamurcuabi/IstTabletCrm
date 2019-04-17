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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.SweetDialog.DialogCreater;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvElevatorRequestAdapter;
import com.emrhmrc.isttabletcrm.api.APIHelper;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.models.CommonClass.FilterModel;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorIdRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.ServappGetByElevatorId;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppointmentElevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragServisler extends Fragment implements OnItemClickListener {


    Spinner spnStatu;
    RecyclerView rcvTalep;
    private JsonApi jsonApi;
    private RcvElevatorRequestAdapter adapter_request;
    private Call<ServappGetByElevatorId> call;

    public static FragServisler newInstance() {

        Bundle args = new Bundle();

        FragServisler fragment = new FragServisler();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_servisler, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spnStatu = view.findViewById(R.id.spn_statu);
        rcvTalep = view.findViewById(R.id.rcv_talep);
        adapter_request = new RcvElevatorRequestAdapter(getActivity(), this);
        init();
        getList();

    }

    private void getList() {
        call = jsonApi.getServappGetByElevatorIdCall(new ElevatorIdRequest(ShareData
                .getInstance().getElevatorId()));
        APIHelper.enqueueWithRetry(call, new Callback<ServappGetByElevatorId>() {
            @Override
            public void onResponse(Call<ServappGetByElevatorId> call, Response<ServappGetByElevatorId> response) {

                if (response.isSuccessful()) {
                    if (response.body().Success) {
                        final ServappGetByElevatorId model = response.body();
                        adapter_request.setItems(model.getServiceAppointment());
                        adapter_request.setItemsFilter(model.getServiceAppointment());
                        setupFilters(model.getServiceAppointment());
                    }
                } else {
                    DialogCreater.errorDialog(getActivity(), response.body().ErrorMsg);
                }

            }

            @Override
            public void onFailure(Call<ServappGetByElevatorId> call, Throwable t) {
                DialogCreater.errorDialog(getActivity(), getString(R.string.try_again));

            }
        });
    }

    private void setupFilters(List<ServiceAppointmentElevator> model) {

        HashMap<Integer, String> statuTypeHash = new HashMap<>();
        List<FilterModel> statuType = new ArrayList<>();

        for (ServiceAppointmentElevator current : model
        ) {

            if (current.getStatusCode() != null) {

                if (!statuTypeHash.containsKey(current.getStatusCode().getValue())) {
                    statuType.add(new FilterModel(current.getStatusCode().getValue(),
                            current.getStatusCode().getText()));
                    statuTypeHash.put(current.getStatusCode().getValue(), "");
                }
            }
        }

        statuType.add(0, new FilterModel(-1, "Statü"));

        //Spn Statu

        ArrayAdapter<FilterModel> spinnerArrayAdapter2 = new ArrayAdapter<>(
                getActivity(), R.layout.spinner_item_black, statuType);
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnStatu.setAdapter(spinnerArrayAdapter2);


        spnStatu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FilterModel filterModel = (FilterModel) adapterView.getItemAtPosition(i);
                adapter_request.getFilter().filter("" + filterModel.getValue());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void init() {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        rcvTalep.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvTalep.setAdapter(adapter_request);
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
