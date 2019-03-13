package com.emrhmrc.isttabletcrm.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.api.APIHelper;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakdownCode;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakdownCodeListAll;
import com.emrhmrc.isttabletcrm.models.Product.MainList;
import com.emrhmrc.isttabletcrm.models.Product.MainProductList;
import com.emrhmrc.isttabletcrm.models.Product.SubGroupRequest;
import com.emrhmrc.isttabletcrm.models.Product.SubList;
import com.emrhmrc.isttabletcrm.models.Product.SubProductList;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppIdRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreakdownTypeCodeFragment extends DialogFragment implements View.OnClickListener {

    private static final String TAG = "NewUnstabilityFragment";
    private ImageView img_close;
    private Button btn_save;
    private JsonApi jsonApi;
    private AutoCompleteTextView spn_main, spn_sub, spn_descp_code, spn_breakdowntype;
    private ShareData shareData;
    private List<MainList> mainList;
    private List<SubList> subList;
    private List<BreakdownCode> codeList;

    public static BreakdownTypeCodeFragment newInstance() {
        Bundle args = new Bundle();
        BreakdownTypeCodeFragment fragment = new BreakdownTypeCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.brekdowntype_code_fragment, container);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shareData = ShareData.getInstance();
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        btn_save = view.findViewById(R.id.btn_save);
        img_close = view.findViewById(R.id.img_close);
        spn_breakdowntype = view.findViewById(R.id.spn_breakdowntype);
        spn_descp_code = view.findViewById(R.id.spn_descp_code);
        spn_sub = view.findViewById(R.id.spn_sub);
        spn_main = view.findViewById(R.id.spn_main);
        btn_save.setOnClickListener(this);
        img_close.setOnClickListener(this);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
        getMainProduct();
        getBreakdownCodes();

    }

    private void getBreakdownCodes() {

        ServAppIdRequest request = new ServAppIdRequest(shareData.getServAppId());
        Call<BreakdownCodeListAll> call = jsonApi.getBreakdownCodeListAllCall(request);
        APIHelper.enqueueWithRetry(call, new Callback<BreakdownCodeListAll>() {
            @Override
            public void onResponse(Call<BreakdownCodeListAll> call, Response<BreakdownCodeListAll> response) {
                if (response.isSuccessful()) {
                    final BreakdownCodeListAll model = response.body();
                    fillspnCode(model.getBreakdownCodes());
                    codeList = model.getBreakdownCodes();
                } else Log.d(TAG, "onResponse: " + response.errorBody());
            }

            @Override
            public void onFailure(Call<BreakdownCodeListAll> call, Throwable t) {

                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });


    }

    private void getMainProduct() {
        Call<MainProductList> call = jsonApi.getMainProductListCall();
        call.enqueue(new Callback<MainProductList>() {
            @Override
            public void onResponse(Call<MainProductList> call, Response<MainProductList> response) {
                if (response.isSuccessful()) {
                    MainProductList model = response.body();
                    mainList = model.getMainProductGroups();
                    fillspnMain(model.getMainProductGroups());

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

    private void getSubProduct(String id) {

        SubGroupRequest request = new SubGroupRequest(id);
        Call<SubProductList> call = jsonApi.getSubProductListCall(request);
        call.enqueue(new Callback<SubProductList>() {
            @Override
            public void onResponse(Call<SubProductList> call, Response<SubProductList> response) {
                if (response.isSuccessful()) {
                    final SubProductList list = response.body();
                    subList = list.getSubProductGroups();
                    fillspnSub(list.getSubProductGroups());

                } else {
                    Log.d(TAG, "onResponse: ");
                }

            }

            @Override
            public void onFailure(Call<SubProductList> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = 3 * width / 5;
        params.height = height;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:

                break;
            case R.id.img_close:
                dismiss();
                break;


        }

    }

    private void fillspnMain(List<MainList> list) {
        if (list.size() > 0 && list != null) {
            ArrayAdapter<MainList> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            spn_main.setAdapter(spinnerArrayAdapter);
            spn_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    spn_main.showDropDown();
                }
            });
            spn_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //Get Sub
                    getSubProduct(mainList.get(i).getInv_MainProductGroupid());
                }
            });
        } else {
            spn_main.setAdapter(null);
            spn_main.setOnClickListener(null);
        }

    }

    private void fillspnSub(List<SubList> list) {
        if (list.size() > 0 && list != null) {
            ArrayAdapter<SubList> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            spn_sub.setAdapter(spinnerArrayAdapter);
            spn_sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    spn_sub.showDropDown();
                }
            });
            spn_sub.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //Get Code
                    filterSpnCode(subList.get(i).getInv_SubProductGroupid());

                }
            });
        } else {
            spn_sub.setAdapter(null);
            spn_sub.setOnClickListener(null);
        }

    }

    private void fillspnCode(List<BreakdownCode> list) {
        if (list.size() > 0 && list != null) {

            ArrayAdapter<BreakdownCode> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            spn_breakdowntype.setAdapter(spinnerArrayAdapter);
            spn_breakdowntype.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    spn_breakdowntype.showDropDown();
                }
            });
            spn_breakdowntype.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //Get Sub
                }
            });
        } else {
            spn_breakdowntype.setAdapter(null);
            spn_breakdowntype.setOnClickListener(null);
        }

    }

    private void filterSpnCode(String filter) {
        if (codeList.size() > 0 && codeList != null) {
            spn_breakdowntype.setEnabled(true);
            List<BreakdownCode> filtered = new ArrayList<>();
            for (BreakdownCode code : codeList
            ) {
                if (code.getInv_SubProductGroupId().equals(filter)) filtered.add(code);

            }
            ArrayAdapter<BreakdownCode> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    filtered);
            spn_breakdowntype.setAdapter(spinnerArrayAdapter);
            spn_breakdowntype.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    spn_breakdowntype.showDropDown();
                }
            });
            spn_breakdowntype.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //Get Sub
                }
            });
        } else {
            spn_breakdowntype.setAdapter(null);
            spn_breakdowntype.setOnClickListener(null);
        }

    }


}
