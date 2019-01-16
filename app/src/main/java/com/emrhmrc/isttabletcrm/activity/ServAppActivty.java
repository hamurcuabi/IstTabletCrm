package com.emrhmrc.isttabletcrm.activity;

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
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppListAll;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppointments;
import com.emrhmrc.isttabletcrm.models.User.UserIdRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServAppActivty extends AppCompatActivity implements OnItemClickListener {

    private static final String TAG = "ServAppActivty";
    private RecyclerView rcw;
    private List<ServiceAppointments> model;
    private JsonApi jsonApi;
    private RcvServAppListAllAdapter adapter;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serv_app_activty);
        init();
        getServAppListAll();
    }

    private void init() {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        rcw = findViewById(R.id.rcw_servapp);
        rcw.setHasFixedSize(true);
        rcw.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RcvServAppListAllAdapter(getApplicationContext(), this);
        adapter.setListener(this);
        rcw.setAdapter(adapter);
        rcw.setLayoutManager(new LinearLayoutManager(this));
        spinner = findViewById(R.id.spn_servapp);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(
                this, R.layout.spinner_item, getResources().getStringArray(R.array.spn_servapp)
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);


    }

    private void getServAppListAll() {
        UserIdRequest userIdRequest = new UserIdRequest("206b43b9-75bd-e811-8103-005056b66d80");
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

        Log.d(TAG, "onItemClicked: "+item.toString());
    }
}
