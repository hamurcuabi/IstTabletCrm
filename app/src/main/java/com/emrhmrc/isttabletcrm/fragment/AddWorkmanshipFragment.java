package com.emrhmrc.isttabletcrm.fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.SweetDialog.SweetAlertDialog;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.ProductAdapter;
import com.emrhmrc.isttabletcrm.api.APIHelper;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.ViewDialog;
import com.emrhmrc.isttabletcrm.models.Product.Product;
import com.emrhmrc.isttabletcrm.models.Product.ProductListAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddWorkmanshipFragment extends DialogFragment implements View.OnClickListener, OnItemClickListener {

    private static final String TAG = "AddWorkmanshipFragment";
    private ImageView img_close;
    private RecyclerView rcv;
    private ProductAdapter adapter;
    private JsonApi jsonApi;
    private ViewDialog viewDialog;
    private ProgressBar prog;
    private Call<ProductListAll> productListAllCall;
    private SearchView searchView;

    public static AddWorkmanshipFragment newInstance() {
        Bundle args = new Bundle();
        AddWorkmanshipFragment fragment = new AddWorkmanshipFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.addworkman_fragment, container);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewDialog = new ViewDialog(getActivity());
        rcv = view.findViewById(R.id.rcv);
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        img_close = view.findViewById(R.id.img_close);
        searchView = view.findViewById(R.id.search);
        prog = view.findViewById(R.id.prog);
        adapter = new ProductAdapter(getActivity(), this);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcv.setAdapter(adapter);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
        img_close.setOnClickListener(view1 -> {
            dismiss();

        });
        getProducts();
    }

    private void getProducts() {
        rcv.setVisibility(View.GONE);
        prog.setVisibility(View.VISIBLE);
        productListAllCall = jsonApi.productListAllNoParam();
        APIHelper.enqueueWithRetry(productListAllCall, new Callback<ProductListAll>() {
            @Override
            public void onResponse(Call<ProductListAll> call, Response<ProductListAll> response) {
                prog.setVisibility(View.GONE);
                rcv.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    final ProductListAll listAll = response.body();
                    fillProducts(listAll.getProducts());
                } else {
                    if (getDialog() != null && getDialog().isShowing()) {
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                .setTitleText(response.message())
                                .show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                prog.setVisibility(View.GONE);
                rcv.setVisibility(View.VISIBLE);
                if (getDialog() != null && getDialog().isShowing()) {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(t.getMessage())
                            .show();
                }

            }
        });
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

    private void fillProducts(List<Product> products) {

        List<Product> items = new ArrayList<>();
        for (Product item : products
        ) {
            if (item.getInv_TypeCode() != null) {
                if (item.getInv_TypeCode().getValue() == 1) items.add(item);
            }

        }
        adapter.setItems(items);
        adapter.setItemsFilter(items);
        search();
    }

    @Override
    public void onResume() {
        super.onResume();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = 9 * width / 10;
        params.height = 9 * height / 10;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {


        }
    }

    @Override
    public void onItemClicked(Object item, int positon) {

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(TAG, "onDismiss: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (productListAllCall != null) productListAllCall.cancel();
    }
}
