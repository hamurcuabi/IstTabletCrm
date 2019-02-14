package com.emrhmrc.isttabletcrm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvProductMainAdapter;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.models.Product.MainList;
import com.emrhmrc.isttabletcrm.models.Product.MainProductList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPieceActivity extends AppCompatActivity implements OnItemClickListener {

    private static final String TAG = "AddPieceActivity";
    @BindView(R.id.rcw_servapp)
    RecyclerView rcwServapp;
    private List<MainList> model;
    private JsonApi jsonApi;
    private RcvProductMainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_piece);
        ButterKnife.bind(this);
        init();
        getMainProductGroup();
    }

    private void getMainProductGroup() {
        Call<MainProductList> call = jsonApi.getMainProductListCall();
        call.enqueue(new Callback<MainProductList>() {
            @Override
            public void onResponse(Call<MainProductList> call, Response<MainProductList> response) {
                if (response.isSuccessful()) {
                    MainProductList model = response.body();
                    adapter.setItems(model.getMainProductGroups());

                } else {
                    Log.d(TAG, "onResponse: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<MainProductList> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void init() {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        rcwServapp.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        rcwServapp.setLayoutManager(mLayoutManager);
        adapter = new RcvProductMainAdapter(getApplicationContext(), this);
        adapter.setListener(this);
        rcwServapp.setAdapter(adapter);
        jsonApi = ApiClient.getClient().create(JsonApi.class);


    }

    @Override
    public void onItemClicked(Object item) {

    }
}
