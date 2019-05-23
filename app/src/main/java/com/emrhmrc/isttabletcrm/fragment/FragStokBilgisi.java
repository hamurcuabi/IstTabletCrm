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
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.SweetDialog.DialogCreater;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvWarehouseAdapter;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.helper.SlideInterface;
import com.emrhmrc.isttabletcrm.models.CommonClass.FilterModel;
import com.emrhmrc.isttabletcrm.models.User.UserIdRequest;
import com.emrhmrc.isttabletcrm.models.Warehouse.WarehouseItem;
import com.emrhmrc.isttabletcrm.models.Warehouse.WarehouseItemListAll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragStokBilgisi extends Fragment implements OnItemClickListener, SlideInterface {

    private static final String TAG = "FragStokBilgisi";
    @BindView(R.id.spn_sort)
    Spinner spnSort;
    @BindView(R.id.lnr_worker)
    RelativeLayout lnrWorker;
    @BindView(R.id.search)
    SearchView search;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    Unbinder unbinder;
    @BindString(R.string.try_again)
    String try_again;
    private JsonApi jsonApi;
    private RcvWarehouseAdapter adapter;
    private Call<WarehouseItemListAll> call;

    private ImageSliderWarehouseFragment fragment;

    public static FragStokBilgisi newInstance() {

        Bundle args = new Bundle();

        FragStokBilgisi fragment = new FragStokBilgisi();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_stok_bilgisi, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spnSort = view.findViewById(R.id.spn_sort);
        rcv = view.findViewById(R.id.rcv);
        search = view.findViewById(R.id.search);
        adapter = new RcvWarehouseAdapter(getActivity(), this);
        init();
        getAll();
        setupFilter();

    }

    private void getAll() {
        Log.d(TAG, "USerID: "+SingletonUser.getInstance().getUser().getUserId());
        UserIdRequest userIdRequest = new UserIdRequest(SingletonUser.getInstance().getUser().getUserId());
        call = jsonApi.getWarehouseItemListAllCall(userIdRequest);
        call.enqueue(new Callback<WarehouseItemListAll>() {
            @Override
            public void onResponse(Call<WarehouseItemListAll> call, Response<WarehouseItemListAll> response) {
                if (response.isSuccessful()) {
                    if (response.body().Success) {

                        final WarehouseItemListAll temp = response.body();
                        adapter.setItems(temp.getWarehouseItems());
                        adapter.setItemsFilter(temp.getWarehouseItems());
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
            public void onFailure(Call<WarehouseItemListAll> call, Throwable t) {
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

    private void filterwithSpinner() {

        spnSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                FilterModel filterModel = (FilterModel) adapterView.getItemAtPosition(i);

                switch (i) {
                    case 0:
                        adapter.getFilter().filter("0");
                        break;
                    case 1:
                        adapter.getFilter().filter("1");
                        break;
                    case 2:
                        Collections.sort(adapter.getItems(),
                                (WarehouseItem lhs, WarehouseItem rhs) -> {
                                    if (lhs.getMainProductGroupid() != null && rhs.getMainProductGroupid() != null)
                                        return lhs.getMainProductGroupid().getText().compareTo(rhs.getMainProductGroupid().getText());
                                    else return 0;
                                });
                        break;
                    case 3:
                        Collections.sort(adapter.getItems(),
                                (WarehouseItem lhs, WarehouseItem rhs) -> {
                                    if (lhs.getSubProductGroupid() != null && rhs.getSubProductGroupid() != null)
                                        return lhs.getSubProductGroupid().getText().compareTo(rhs.getSubProductGroupid().getText());
                                    else return 0;
                                });
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

    private void openCreateFragment() {
        CreateNewWareRequestFragment fragment = CreateNewWareRequestFragment.newInstance();
        fragment.show(getActivity().getSupportFragmentManager(), "CreateNewWareRequestFragment");
    }

    @Override
    public void onItemClicked(Object item, int positon) {

        if (item instanceof WarehouseItem) {
            openSilder(((WarehouseItem) item), positon);
        }

    }

    private void openSilder(WarehouseItem product, int position) {
        fragment = ImageSliderWarehouseFragment.newInstance(product, position);
        fragment.show(getActivity().getSupportFragmentManager(), "slider");
    }

    @Override
    public void slideTo(int position) {
        if (position < 0) openSilder(adapter.getItems().get(0), 0);
        else if (position > adapter.getItems().size() - 1)
            openSilder(adapter.getItems().get(adapter.getItems().size() - 1),
                    adapter.getItems().size() - 1);
        else openSilder(adapter.getItems().get(position), position);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (call != null) call.cancel();
        unbinder.unbind();
    }

    private void setupFilter() {
        List<FilterModel> servappType = new ArrayList<>();
        servappType.add(new FilterModel(0, "Tümü"));
        servappType.add(new FilterModel(1, "Üzerimdeki Stoklar"));
        servappType.add(new FilterModel(2, "Ana Ürün Grubu"));
        servappType.add(new FilterModel(3, "Alt Ürün Grubu"));

        ArrayAdapter<FilterModel> spinnerArrayAdapter = new ArrayAdapter<>(
                getActivity(), R.layout.spinner_item_white, servappType);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSort.setAdapter(spinnerArrayAdapter);
    }

    @OnClick(R.id.lnr_worker)
    public void onViewClicked() {
        openCreateFragment();
    }
}
