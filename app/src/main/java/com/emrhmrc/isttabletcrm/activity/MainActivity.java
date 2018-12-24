package com.emrhmrc.isttabletcrm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.models.Product.ProductListAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Button btn_list_all;
    private TextView txt_json;
    private JsonApi jsonApi;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initClicks();
    }

    private void initClicks() {
        btn_list_all.setOnClickListener(this);
    }

    private void init() {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        btn_list_all = findViewById(R.id.btn_list_all);
        txt_json = findViewById(R.id.txt_json);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_list_all:
                getProductListAll();
                break;

        }
    }

    private void getProductListAll() {

        Call<ProductListAll> call = jsonApi.getProductListAll();
        call.enqueue(new Callback<ProductListAll>() {
            @Override
            public void onResponse(Call<ProductListAll> call, Response<ProductListAll> response) {
                if (response.isSuccessful()) {
                    ProductListAll temp = response.body();

                }

            }

            @Override
            public void onFailure(Call<ProductListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());


            }
        });


    }

}