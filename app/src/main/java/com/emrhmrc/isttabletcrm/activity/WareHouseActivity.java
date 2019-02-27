package com.emrhmrc.isttabletcrm.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvWarehouseAdapter;
import com.emrhmrc.isttabletcrm.adapter.RcvWarehouseTransferAdapter;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.models.User.UserIdRequest;
import com.emrhmrc.isttabletcrm.models.Warehouse.WarehouseItemListAll;
import com.emrhmrc.isttabletcrm.models.Warehouse.WarehouseTransferListAll;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WareHouseActivity extends AppCompatActivity implements OnItemClickListener {

    private static final String TAG = "WareHouseActivity";
    @BindView(R.id.txt_add)
    Spinner txtAdd;
    @BindView(R.id.search)
    SearchView search;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.rcv_talep)
    RecyclerView rcvTalep;
    @BindView(R.id.cons_content)
    ConstraintLayout consContent;
    @BindView(R.id.lnr_worker)
    RelativeLayout lnrWorker;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @BindView(R.id.search2)
    SearchView search2;
    @BindView(R.id.btn_info)
    Button btnInfo;
    @BindView(R.id.btn_wish)
    Button btnWish;
    @BindDrawable(R.drawable.btn_malzeme)
    Drawable first;
    @BindDrawable(R.drawable.btn_taleplerim)
    Drawable second;
    private JsonApi jsonApi;
    private RcvWarehouseAdapter adapter;
    private RcvWarehouseTransferAdapter adapter_talep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ware_house);
        ButterKnife.bind(this);
        init();
        getAll();
        getAllTrasnefer();

    }

    private void getAllTrasnefer() {
        UserIdRequest userIdRequest = new UserIdRequest(SingletonUser.getInstance().getUser().getUserId());
        Call<WarehouseTransferListAll> call = jsonApi.getWarehouseTransferListAllCall(userIdRequest);
        call.enqueue(new Callback<WarehouseTransferListAll>() {
            @Override
            public void onResponse(Call<WarehouseTransferListAll> call, Response<WarehouseTransferListAll> response) {
                if (response.isSuccessful()) {

                    final WarehouseTransferListAll temp = response.body();
                    adapter_talep.setItems(temp.getWarehouseTransfers());
                    adapter_talep.setItemsFilter(temp.getWarehouseTransfers());
                    search2();

                }
            }

            @Override
            public void onFailure(Call<WarehouseTransferListAll> call, Throwable t) {

            }
        });
    }

    private void getAll() {
        UserIdRequest userIdRequest = new UserIdRequest(SingletonUser.getInstance().getUser().getUserId());
        Call<WarehouseItemListAll> call = jsonApi.getWarehouseItemListAllCall(userIdRequest);
        call.enqueue(new Callback<WarehouseItemListAll>() {
            @Override
            public void onResponse(Call<WarehouseItemListAll> call, Response<WarehouseItemListAll> response) {
                if (response.isSuccessful()) {

                    final WarehouseItemListAll temp = response.body();
                    adapter.setItems(temp.getWarehouseItems());
                    adapter.setItemsFilter(temp.getWarehouseItems());
                    search();
                }
            }

            @Override
            public void onFailure(Call<WarehouseItemListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
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

    private void search2() {
        search2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter_talep.getFilter().filter(s);
                return false;
            }
        });
    }

    private void init() {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        adapter = new RcvWarehouseAdapter(getApplicationContext(), this);
        adapter.setListener(this);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(adapter);

        adapter_talep = new RcvWarehouseTransferAdapter(getApplicationContext(), this);
        adapter_talep.setListener(this);
        rcvTalep.setLayoutManager(new LinearLayoutManager(this));
        rcvTalep.setAdapter(adapter_talep);
    }

    private void rcvFirst() {
        rcv.setVisibility(View.VISIBLE);
        rcvTalep.setVisibility(View.GONE);
        lnrWorker.setVisibility(View.VISIBLE);
        search.setVisibility(View.VISIBLE);
        search2.setVisibility(View.GONE);
        btnInfo.setBackground(first);
        btnWish.setBackground(second);
        linearLayout.setVisibility(View.VISIBLE);
        linearLayout2.setVisibility(View.GONE);

    }

    private void rcvSecond() {
        rcv.setVisibility(View.GONE);
        rcvTalep.setVisibility(View.VISIBLE);
        lnrWorker.setVisibility(View.GONE);
        search.setVisibility(View.GONE);
        search2.setVisibility(View.VISIBLE);
        btnInfo.setBackground(second);
        btnWish.setBackground(first);
        linearLayout2.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
    }

    @OnClick({R.id.btn_info, R.id.btn_wish, R.id.img_add_2, R.id.add_job, R.id.rcv_talep})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_info:
                rcvFirst();
                break;
            case R.id.btn_wish:
                rcvSecond();
                break;
            case R.id.img_add_2:
                break;
            case R.id.add_job:
                break;
            case R.id.rcv_talep:

                break;
        }
    }

    @Override
    public void onItemClicked(Object item, int positon) {

    }
}
