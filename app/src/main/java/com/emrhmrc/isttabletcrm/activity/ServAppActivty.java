package com.emrhmrc.isttabletcrm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.SweetDialog.AnyDialog;
import com.emrhmrc.isttabletcrm.SweetDialog.SweetAlertDialog;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvServAppListAllAdapter;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.bindingModel.ServiceAppointments;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppListAll;
import com.emrhmrc.isttabletcrm.models.User.UserIdRequest;
import com.emrhmrc.isttabletcrm.util.StringUtil;

import java.util.List;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServAppActivty extends AppCompatActivity implements OnItemClickListener {

    private static final String TAG = "ServAppActivty";
    @BindView(R.id.spn_servapp)
    Spinner spinner;
    @BindView(R.id.rcw_servapp)
    RecyclerView rcw;
    @BindArray(R.array.spn_servapp)
    String[] items;
    @BindString(R.string.loading)
    String loading;
    private List<ServiceAppointments> model;
    private JsonApi jsonApi;
    private RcvServAppListAllAdapter adapter;
    private ShareData shareData;
    private SweetAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serv_app_activty);
        ButterKnife.bind(this);
        init();
        initDialog();


    }

    private void initDialog() {
        AnyDialog anyDialog = new AnyDialog(this);
        dialog = anyDialog.loading(loading);
    }

    private void filterwithSpinner() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.getFilter().filter(StringUtil.convertIntToString(i));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void init() {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        rcw.setHasFixedSize(true);
        rcw.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RcvServAppListAllAdapter(getApplicationContext(), this);
        adapter.setListener(this);
        rcw.setAdapter(adapter);
        rcw.setLayoutManager(new LinearLayoutManager(this));


        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(
                this, R.layout.spinner_item_white, items);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        shareData = ShareData.getInstance();


    }

    private void getServAppListAll() {
        dialog.show();
        UserIdRequest userIdRequest = new UserIdRequest(shareData.getUserId());
        Call<ServAppListAll> call = jsonApi.servAppListAll(userIdRequest);
        call.enqueue(new Callback<ServAppListAll>() {
            @Override
            public void onResponse(Call<ServAppListAll> call, Response<ServAppListAll> response) {
                if (response.isSuccessful()) {

                    final ServAppListAll temp = response.body();
                    model = temp.getServiceAppointments();
                    adapter.setItems(model);
                    adapter.setItemsFilter(model);
                    filterwithSpinner();


                }
                dialog.dismissWithAnimation();
            }

            @Override
            public void onFailure(Call<ServAppListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                dialog.dismissWithAnimation();
            }
        });

    }

    @Override
    public void onItemClicked(Object item, int positon) {

        ServiceAppointments current = (ServiceAppointments) item;
        shareData.setServAppId(current.getActivityId());
        //shareData.setServAppId("8C096C30-14C3-E811-8103-005056B66D80");
        Log.d(TAG, "onItemClicked: " + current.getActivityId());
        goServAppDetail();

    }

    public void goServAppDetail() {
        dialog.cancel();
        startActivity(new Intent(ServAppActivty.this, ServAppDetailActivity.class));
    }

    public void goCreateServApp() {
        dialog.cancel();
        startActivity(new Intent(ServAppActivty.this, CreateServAppActivity.class));
    }

    @OnClick({R.id.txt_add, R.id.img_add, R.id.img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_add:
                goCreateServApp();
                break;
            case R.id.img_add:
                goCreateServApp();
                break;
            case R.id.img_back:
                super.onBackPressed();
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initDialog();
        getServAppListAll();


    }
}
