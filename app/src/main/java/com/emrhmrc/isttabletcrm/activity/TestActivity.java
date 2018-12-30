package com.emrhmrc.isttabletcrm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.TestAdapter;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.models.Account.AccountListAll;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakDownTypeListAll;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorListAll;
import com.emrhmrc.isttabletcrm.models.Product.ProductListAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TestActivity extends AppCompatActivity implements View.OnClickListener, OnItemClickListener {

    private static final String TAG = "TestActivity";

    private Button btn_product_list_all, btn_breakdown_type_list_all, btn_account_all, btn_elevator_list_all;
    private TextView txt_json;
    private JsonApi jsonApi;
    private RecyclerView rcw;
    private TestAdapter adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);


        init();
        initClicks();


    }

    private void initClicks() {
        btn_product_list_all.setOnClickListener(this);
        btn_breakdown_type_list_all.setOnClickListener(this);
        btn_account_all.setOnClickListener(this);
        btn_elevator_list_all.setOnClickListener(this);
    }

    private void init() {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        btn_product_list_all = findViewById(R.id.btn_product_list_all);
        btn_breakdown_type_list_all = findViewById(R.id.btn_breakdown_type_list_all);
        btn_account_all = findViewById(R.id.btn_account_all);
        btn_elevator_list_all = findViewById(R.id.btn_elevator_list_all);
        txt_json = findViewById(R.id.txt_json);
        rcw = findViewById(R.id.rcw_test);
        rcw.setHasFixedSize(true);
        adapter = new TestAdapter(getApplicationContext());
        adapter.setListener(this);
        rcw.setAdapter(adapter);
        rcw.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_product_list_all:
                getProductListAll();
                break;
            case R.id.btn_breakdown_type_list_all:
                getBreakdowTypeListAll();
                break;
            case R.id.btn_account_all:
                getAccountListAll();
                break;
            case R.id.btn_elevator_list_all:
                getEvelatorListAll();
                break;


        }
    }

    private void getProductListAll() {

        Call<ProductListAll> call = jsonApi.productListAll();
        call.enqueue(new Callback<ProductListAll>() {
            @Override
            public void onResponse(Call<ProductListAll> call, Response<ProductListAll> response) {
                if (response.isSuccessful()) {
                    ProductListAll temp = response.body();
                    txt_json.setText("" + temp.getSuccess());
                    adapter.setItems(temp.getProducts());

                }

            }

            @Override
            public void onFailure(Call<ProductListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());


            }
        });


    }

    private void getBreakdowTypeListAll() {

        Call<BreakDownTypeListAll> call = jsonApi.breakDownTypeListAll();
        call.enqueue(new Callback<BreakDownTypeListAll>() {
            @Override
            public void onResponse(Call<BreakDownTypeListAll> call, Response<BreakDownTypeListAll> response) {
                if (response.isSuccessful()) {

                    BreakDownTypeListAll temp = response.body();
                    txt_json.setText("" + temp.getSuccess());


                }
            }

            @Override
            public void onFailure(Call<BreakDownTypeListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });


    }

    private void getAccountListAll() {

        Call<AccountListAll> call = jsonApi.accountListAll();
        call.enqueue(new Callback<AccountListAll>() {
            @Override
            public void onResponse(Call<AccountListAll> call, Response<AccountListAll> response) {
                if (response.isSuccessful()) {
                    AccountListAll temp = response.body();
                    txt_json.setText("" + temp.getSuccess());

                }
            }

            @Override
            public void onFailure(Call<AccountListAll> call, Throwable t) {

                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });


    }

    private void getEvelatorListAll() {
        Call<ElevatorListAll> call = jsonApi.elevatorListAll();
        call.enqueue(new Callback<ElevatorListAll>() {
            @Override
            public void onResponse(Call<ElevatorListAll> call, Response<ElevatorListAll> response) {
                if (response.isSuccessful()) {

                    final ElevatorListAll temp = response.body();
                    // txt_json.setText("" + temp.getSuccess());


                }
            }

            @Override
            public void onFailure(Call<ElevatorListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }


    @Override
    public void onItemClicked(Object item) {
        Toast.makeText(getApplicationContext(), item.toString(), Toast.LENGTH_SHORT).show();
    }
}