package com.emrhmrc.isttabletcrm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvServAppListAllAdapter;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppListAll;
import com.emrhmrc.isttabletcrm.models.ServiceAppointments;
import com.emrhmrc.isttabletcrm.models.User.UserIdRequest;

import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
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
    private List<ServiceAppointments> model;
    private JsonApi jsonApi;
    private RcvServAppListAllAdapter adapter;
    private ShareData shareData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serv_app_activty);
        ButterKnife.bind(this);
        init();
        getServAppListAll();
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
                this, R.layout.spinner_item, items);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
        shareData = ShareData.getInstance();

    }

    private void getServAppListAll() {
        UserIdRequest userIdRequest = new UserIdRequest(shareData.getUserId());
        Call<ServAppListAll> call = jsonApi.servAppListAll(userIdRequest);
        call.enqueue(new Callback<ServAppListAll>() {
            @Override
            public void onResponse(Call<ServAppListAll> call, Response<ServAppListAll> response) {
                if (response.isSuccessful()) {

                    final ServAppListAll temp = response.body();
                    model = temp.getServiceAppointments();
                    adapter.setItems(model);


                }
            }

            @Override
            public void onFailure(Call<ServAppListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    public void onItemClicked(Object item) {
        ServiceAppointments current = (ServiceAppointments) item;
        shareData.setServAppId(current.getActivityId());
        goServAppDetail();

    }

    public void goServAppDetail() {

        startActivity(new Intent(ServAppActivty.this, ServAppDetailActivity.class));
    }
}
