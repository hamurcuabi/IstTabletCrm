package com.emrhmrc.isttabletcrm.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.SweetDialog.DialogCreater;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvWarehouseTransferAdapter;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.Methodes;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.models.CommonClass.FilterModel;
import com.emrhmrc.isttabletcrm.models.User.UserIdRequest;
import com.emrhmrc.isttabletcrm.models.Warehouse.WarehouseTransferItem;
import com.emrhmrc.isttabletcrm.models.Warehouse.WarehouseTransferListAll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragTaleplerim extends Fragment implements OnItemClickListener {

    private static final String TAG = "FragStokBilgisi";
    @BindView(R.id.spn_sort)
    Spinner spnSort;
    @BindView(R.id.search)
    SearchView search;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindString(R.string.try_again)
    String try_again;
    Unbinder unbinder;
    private JsonApi jsonApi;
    private RcvWarehouseTransferAdapter adapter;
    private Call<WarehouseTransferListAll> call;


    public static FragTaleplerim newInstance() {

        Bundle args = new Bundle();

        FragTaleplerim fragment = new FragTaleplerim();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_taleplerim, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new RcvWarehouseTransferAdapter(getActivity(), this);
        init();
        getAll();
        setupFilter();

    }

    private void getAll() {

        UserIdRequest userIdRequest = new UserIdRequest(SingletonUser.getInstance().getUser().getUserId());
        call = jsonApi.getWarehouseTransferListAllCall(userIdRequest);
        call.enqueue(new Callback<WarehouseTransferListAll>() {
            @Override
            public void onResponse(Call<WarehouseTransferListAll> call, Response<WarehouseTransferListAll> response) {
                if (response.isSuccessful()) {
                    if (response.body().Success) {

                        final WarehouseTransferListAll temp = response.body();
                        adapter.setItems(temp.getWarehouseTransfers());
                        adapter.setItemsFilter(temp.getWarehouseTransfers());
                        search();
                        filterwithSpinner();
                    } else {
                        DialogCreater.errorDialog(getActivity(), response.body().ErrorMsg);
                    }

                } else {
                    DialogCreater.errorDialog(getActivity(), try_again);
                }
            }

            @Override
            public void onFailure(Call<WarehouseTransferListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                DialogCreater.errorDialog(getActivity(), try_again);

            }
        });
    }

    private void search() {
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }

    private void setupFilter() {
        List<FilterModel> servappType = new ArrayList<>();
        servappType.add(new FilterModel(0, "Sırala"));
        servappType.add(new FilterModel(1, "Ürün Adına Göre"));
        servappType.add(new FilterModel(2, "Transfer Tipine Göre"));
        servappType.add(new FilterModel(3, "Tarihe Göre"));

        ArrayAdapter<FilterModel> spinnerArrayAdapter = new ArrayAdapter<>(
                getActivity(), R.layout.spinner_item_white, servappType);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSort.setAdapter(spinnerArrayAdapter);
    }

    private void filterwithSpinner() {

        spnSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // adapter.getFilter().filter(StringUtil.convertIntToString(i));
                switch (i) {
                    case 0:
                        break;
                    case 1:
                        Collections.sort(adapter.getItems(),
                                (lhs, rhs) -> lhs.getInv_Productid().getText().compareTo(rhs.getInv_Productid().getText()));
                        break;
                    case 2:
                        Collections.sort(adapter.getItems(),
                                (WarehouseTransferItem lhs, WarehouseTransferItem rhs) -> {
                                    if (lhs.getInv_TransferTypeCode() != null && rhs.getInv_TransferTypeCode() != null)
                                        return lhs.getInv_TransferTypeCode().getValue() - rhs.getInv_TransferTypeCode().getValue();
                                    else return 0;
                                });
                        break;
                    case 3:
                        Collections.sort(adapter.getItems(),
                                (WarehouseTransferItem lhs, WarehouseTransferItem rhs) -> Methodes.compareDateFromText(rhs.getInv_RequestDate(), lhs.getInv_RequestDate()));
                        break;
                }
                adapter.setItems(adapter.getItems());
                if (adapter.getItems().size() > 0)
                    rcv.scrollToPosition(0);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
        unbinder.unbind();
    }
}
