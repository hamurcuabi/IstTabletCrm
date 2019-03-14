package com.emrhmrc.isttabletcrm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.SweetDialog.AnyDialog;
import com.emrhmrc.isttabletcrm.SweetDialog.SweetAlertDialog;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvUnstabilityAdapter;
import com.emrhmrc.isttabletcrm.api.APIHelper;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.fragment.NewUnstabilityFragment;
import com.emrhmrc.isttabletcrm.helper.Methodes;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.models.Unsuitablity.Unsuitabilities;
import com.emrhmrc.isttabletcrm.models.Unsuitablity.UnsuitabilityListAll;
import com.emrhmrc.isttabletcrm.models.User.UserIdRequest;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnsuitabilityActivity extends AppCompatActivity implements OnItemClickListener {

    private static final String TAG = "UnsuitabilityActivity";
    @BindView(R.id.search)
    SearchView search;
    @BindView(R.id.rcw_servapp)
    RecyclerView rcwServapp;
    @BindString(R.string.loading)
    String loading;
    private RcvUnstabilityAdapter adapter;
    private JsonApi jsonApi;
    private SweetAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unsuitability);
        ButterKnife.bind(this);
        init();
        getAll();

    }

    private void getAll() {

        dialog.show();
        UserIdRequest userIdRequest = new UserIdRequest(SingletonUser.getInstance().getUser().getUserId());
        Call<UnsuitabilityListAll> call = jsonApi.getUnsuitabilityListAllCall(userIdRequest);
        APIHelper.enqueueWithRetry(call, 3, new Callback<UnsuitabilityListAll>() {
                    @Override
                    public void onResponse(Call<UnsuitabilityListAll> call, Response<UnsuitabilityListAll> response) {
                        if (response.isSuccessful()) {
                            List<Unsuitabilities> model = response.body().getUnsuitabilities();
                            adapter.setItemsFilter(model);
                            adapter.setItems(model);
                            search();
                        }
                        dialog.dismissWithAnimation();
                    }

                    @Override
                    public void onFailure(Call<UnsuitabilityListAll> call, Throwable t) {
                        dialog.dismissWithAnimation();
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                }
        );

    }

    private void init() {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        rcwServapp.setHasFixedSize(true);
        rcwServapp.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RcvUnstabilityAdapter(getApplicationContext(), this);
        adapter.setListener(this);
        rcwServapp.setAdapter(adapter);
        AnyDialog anyDialog = new AnyDialog(this);
        dialog = anyDialog.loading(loading);
        Methodes.checkAndRequestPermissions(this);


    }

    private void openNewUnstability() {
        NewUnstabilityFragment fragment = NewUnstabilityFragment.newInstance();
        fragment.show(getSupportFragmentManager(), "reasonbrekadown");
    }

    @OnClick({R.id.img_back, R.id.txt_add, R.id.img_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                super.onBackPressed();
                break;
            case R.id.txt_add:
                openNewUnstability();
                break;
            case R.id.img_add:
                openNewUnstability();
                break;
        }
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

    @Override
    public void onItemClicked(Object item, int positon) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
