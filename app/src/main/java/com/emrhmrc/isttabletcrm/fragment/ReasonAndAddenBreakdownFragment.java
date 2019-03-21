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
import android.widget.ProgressBar;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.SweetDialog.SweetAlertDialog;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvBreakdownTypeAdapter;
import com.emrhmrc.isttabletcrm.adapter.RcvBreakdownTypeAddAdapter;
import com.emrhmrc.isttabletcrm.api.APIHelper;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.StateHandler;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakDownTypeListAll;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakdownType;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppBreakdownTypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReasonAndAddenBreakdownFragment extends DialogFragment implements View.OnClickListener {
    private static final String TAG = "DetailTechnicalFragment";

    private ImageView img_close;
    private JsonApi jsonApi;
    private RcvBreakdownTypeAdapter adapter;
    private RcvBreakdownTypeAddAdapter adapter_add;
    private RecyclerView rcv_reason, rcv_add;
    private List<BreakdownType> list, allreason;
    private Call<BreakDownTypeListAll> breakDownTypeListAllCall;
    private List<ServAppGetByIdServAppBreakdownTypes> servAppBreakdownTypes;
    private ProgressBar prog_1;


    public static ReasonAndAddenBreakdownFragment newInstance(List<ServAppGetByIdServAppBreakdownTypes> model) {

        Bundle args = new Bundle();
        args.putSerializable("servAppBreakdownTypes", (Serializable) model);
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
        rcv_add = view.findViewById(R.id.rcv_added);
        rcv_reason = view.findViewById(R.id.rcv_reason);
        prog_1 = view.findViewById(R.id.prog_1);
        img_close.setOnClickListener(this);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        servAppBreakdownTypes = (List<ServAppGetByIdServAppBreakdownTypes>) getArguments().getSerializable("servAppBreakdownTypes");
        init();
        getAllReason();

    }

    private void getAllReason() {
        prog_1.setVisibility(View.VISIBLE);
        breakDownTypeListAllCall = jsonApi.breakDownTypeListAll();
        APIHelper.enqueueWithRetry(breakDownTypeListAllCall, new Callback<BreakDownTypeListAll>() {
            @Override
            public void onResponse(Call<BreakDownTypeListAll> call, Response<BreakDownTypeListAll> response) {
                if (response.isSuccessful()) {
                    final BreakDownTypeListAll model = response.body();
                    adapter.setItems(model.getBreakdownTypes());
                    allreason = model.getBreakdownTypes();
                    checkAddedReasons();
                    prog_1.setVisibility(View.GONE);

                } else Log.d(TAG, "onResponse: " + response.errorBody());
            }

            @Override
            public void onFailure(Call<BreakDownTypeListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                prog_1.setVisibility(View.GONE);
                if (getDialog() != null && getDialog().isShowing()) {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(getResources().getString(R.string.toast_error))
                            .show();
                }
            }
        });

    }

    private void init() {
        list = new ArrayList<>();
        rcv_add.setHasFixedSize(true);
        rcv_add.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter_add = new RcvBreakdownTypeAddAdapter(getActivity(), (item, positon) -> {
            final ServAppGetByIdServAppBreakdownTypes current = (ServAppGetByIdServAppBreakdownTypes) item;
            adapter_add.remove(current);
            for (int i = 0; i < adapter.getItems().size(); i++) {
                if (adapter.getItems().get(i).getInv_BreakdownTypeId().equals(current.getInv_BreakdownTypeId().getId())) {
                    StateHandler.getInstance().getStateList().get(i).setState(false);
                    adapter.notifyItemChanged(i);
                    return;
                }
            }
        });
        rcv_add.setAdapter(adapter_add);
        adapter_add.setItemsWithoutState(servAppBreakdownTypes);
        rcv_add.setLayoutManager(new LinearLayoutManager(getActivity()));


        rcv_reason.setHasFixedSize(true);
        rcv_reason.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RcvBreakdownTypeAdapter(getActivity(), new OnItemClickListener() {
            @Override
            public void onItemClicked(Object item, int positon) {
                final BreakdownType current = (BreakdownType) item;
                final ServAppGetByIdServAppBreakdownTypes breakdownTypes =
                        new ServAppGetByIdServAppBreakdownTypes();
                breakdownTypes.setDeleted(false);
                breakdownTypes.setInv_ServAppBreakdownTypeId("");
                breakdownTypes.setInv_BreakdownTypeId(new Inv_Id("inv_breakdowntype", current.getInv_BreakdownTypeName(),
                        current.getInv_BreakdownTypeId()));
                adapter_add.add(breakdownTypes);
                if (!StateHandler.getInstance().getStateList().get(positon).isState()) {
                    StateHandler.getInstance().getStateList().get(positon).setState(true);

                } else {
                    StateHandler.getInstance().getStateList().get(positon).setState(false);

                }
                adapter.notifyItemChanged(positon);
            }
        });

        rcv_reason.setAdapter(adapter);
        rcv_reason.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void checkAddedReasons() {
        for (int i = 0; i < adapter.getItems().size() - 1; i++) {

            for (int k = 0; k < adapter_add.getItems().size() - 1; k++) {

                if (adapter.getItems().get(i).getInv_BreakdownTypeId().equals(adapter_add.getItems().get(k).getInv_BreakdownTypeId().getId())) {

                    if (!StateHandler.getInstance().getStateList().get(i).isState()) {
                        StateHandler.getInstance().getStateList().get(i).setState(true);

                    } else {
                        StateHandler.getInstance().getStateList().get(i).setState(false);

                    }
                    adapter.notifyItemChanged(i);

                }
            }

        }


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
    public void onDestroy() {
        super.onDestroy();
        if (breakDownTypeListAllCall != null) breakDownTypeListAllCall.cancel();
    }
}
