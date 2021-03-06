package com.emrhmrc.isttabletcrm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.SweetDialog.AnyDialog;
import com.emrhmrc.isttabletcrm.SweetDialog.DialogCreater;
import com.emrhmrc.isttabletcrm.SweetDialog.SweetAlertDialog;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvProductMainAdapter;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.models.Product.MainList;
import com.emrhmrc.isttabletcrm.models.Product.MainProductList;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPieceActivity extends AppCompatActivity implements OnItemClickListener {

    private static final String TAG = "AddPieceActivity";
    @BindView(R.id.rcw_servapp)
    RecyclerView rcwServapp;
    @BindString(R.string.loading)
    String loading;
    @BindString(R.string.try_again)
    String try_again;
    @BindView(R.id.textView6)
    TextView txtHeader;
    private List<MainList> model;
    private JsonApi jsonApi;
    private RcvProductMainAdapter adapter;
    private SearchView searchView;
    private SweetAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_piece);
        ButterKnife.bind(this);
        init();

        if (ShareData.getInstance().getMainProductList() != null && ShareData.getInstance().getMainProductList().getMainProductGroups().size() > 0) {
            model = ShareData.getInstance().getMainProductList().getMainProductGroups();
            adapter.setItems(model);
            adapter.setItemsFilter(model);
            search();
        } else {
            getMainProductGroup();
        }
        if (!ShareData.getInstance().isAdd_sub_piece()) {
            txtHeader.setText(getResources().getString(R.string.ana_urun));

        }
    }

    private void getMainProductGroup() {
        dialog.show();
        Call<MainProductList> call = jsonApi.getMainProductListCall();
        call.enqueue(new Callback<MainProductList>() {
            @Override
            public void onResponse(Call<MainProductList> call, Response<MainProductList> response) {
                if (response.isSuccessful()) {
                    if (response.body().Success) {
                        MainProductList model = response.body();
                        ShareData.getInstance().setMainProductList(model);
                        adapter.setItems(model.getMainProductGroups());
                        adapter.setItemsFilter(model.getMainProductGroups());
                        search();
                    } else {
                        DialogCreater.errorDialog(AddPieceActivity.this, response.body().ErrorMsg);
                    }

                } else {
                    DialogCreater.errorDialog(AddPieceActivity.this, try_again);
                }
                dialog.dismissWithAnimation();
            }

            @Override
            public void onFailure(Call<MainProductList> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                dialog.dismissWithAnimation();
                DialogCreater.errorDialog(AddPieceActivity.this, try_again);
            }
        });
    }

    private void init() {
        searchView = findViewById(R.id.search);
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        rcwServapp.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        rcwServapp.setLayoutManager(mLayoutManager);
        adapter = new RcvProductMainAdapter(getApplicationContext(), this);
        adapter.setListener(this);
        rcwServapp.setAdapter(adapter);
        AnyDialog anyDialog = new AnyDialog(this);
        dialog = anyDialog.loading(loading);


    }

    private void search() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        final MainList model = (MainList) item;
        ShareData.getInstance().setProductMainId(model.getInv_MainProductGroupid());
        ShareData.getInstance().setProductMainName(model.getInv_MainProductGroupName());
        goSubGroup();
    }

    private void goSubGroup() {
        startActivity(new Intent(AddPieceActivity.this, AddSubPieceActivity.class));
    }

    @OnClick({R.id.img_back, R.id.img_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                super.onBackPressed();
                break;
            case R.id.img_close:
                super.onBackPressed();
                break;
        }

    }


}
