package com.emrhmrc.isttabletcrm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.model.Product;

import java.util.List;

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

    private void fillJsonToText(List<Product> list) {
        String json = "";
        for (Product product : list) {
            json += product.toString();
        }
        txt_json.setText(json);

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

        Call<List<Product>> call = jsonApi.getProductListAll();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> temp = response.body();
                    fillJsonToText(temp);
                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(MainActivity.this, getResources().getString(R.string.toast_error), Toast
                        .LENGTH_SHORT)
                        .show();

            }
        });
    }

}