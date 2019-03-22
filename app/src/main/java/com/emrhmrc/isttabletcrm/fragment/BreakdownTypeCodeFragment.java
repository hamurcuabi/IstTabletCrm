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
import android.widget.ProgressBar;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakdownCode;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakdownCodeGetByFilter;
import com.emrhmrc.isttabletcrm.models.Product.MainList;
import com.emrhmrc.isttabletcrm.models.Product.MainProductList;
import com.emrhmrc.isttabletcrm.models.Product.Product;
import com.emrhmrc.isttabletcrm.models.Product.ProductListAll;
import com.emrhmrc.isttabletcrm.models.Product.SubGroupProductsRequest;
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
    private Call<BreakdownCodeGetByFilter> breakdownCodeListAllCall;
    private Call<MainProductList> mainProductListCall;
    private Call<SubProductList> subProductListCall;
    private Call<ProductListAll> productListAllCall;
    private ProgressBar prog_ana, prog_alt, prog_tanim, prog_ariza;
    private ArrayAdapter<MainList> mainListArrayAdapter;
    private ArrayAdapter<SubList> subListArrayAdapter;
    private ArrayAdapter<Product> productArrayAdapter;
    private ArrayAdapter<BreakdownCode> breakdownCodeArrayAdapter;
    private ArrayAdapter<BreakdownCode> codeArrayAdapter;

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
        prog_ana = view.findViewById(R.id.prog_ana);
        prog_alt = view.findViewById(R.id.prog_alt);
        prog_tanim = view.findViewById(R.id.prog_tanim);
        prog_ariza = view.findViewById(R.id.prog_ariza);
        btn_save.setOnClickListener(this);
        img_close.setOnClickListener(this);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
        getMainProduct();
        getBreakdownCodes();

    }

    private void getBreakdownCodes() {

        prog_ariza.setVisibility(View.VISIBLE);
        ServAppIdRequest request = new ServAppIdRequest(shareData.getServAppId());
        breakdownCodeListAllCall = jsonApi.getBreakdownCodeListAllCall(request);
        breakdownCodeListAllCall.enqueue(new Callback<BreakdownCodeGetByFilter>() {
            @Override
            public void onResponse(Call<BreakdownCodeGetByFilter> call, Response<BreakdownCodeGetByFilter> response) {
                if (response.isSuccessful()) {
                    final BreakdownCodeGetByFilter model = response.body();
                    fillspnCode(model.getBreakdownCodes());
                    codeList = model.getBreakdownCodes();
                } else Log.d(TAG, "onResponse: " + response.errorBody());
                prog_ariza.setVisibility(View.GONE);
                spn_breakdowntype.setOnFocusChangeListener((view, b) -> {
                    if (b) spn_breakdowntype.showDropDown();
                    else spn_breakdowntype.dismissDropDown();
                });
            }

            @Override
            public void onFailure(Call<BreakdownCodeGetByFilter> call, Throwable t) {

                Log.d(TAG, "onFailure: " + t.getMessage());
                prog_ariza.setVisibility(View.GONE);
            }
        });


    }

    private void getMainProduct() {
        prog_ana.setVisibility(View.VISIBLE);
        mainProductListCall = jsonApi.getMainProductListCall();
        mainProductListCall.enqueue(new Callback<MainProductList>() {
            @Override
            public void onResponse(Call<MainProductList> call, Response<MainProductList> response) {
                if (response.isSuccessful()) {
                    MainProductList model = response.body();
                    mainList = model.getMainProductGroups();
                    fillspnMain(model.getMainProductGroups());
                    spn_main.setOnFocusChangeListener((view, b) -> {
                        if (b) spn_main.showDropDown();
                        else spn_main.dismissDropDown();
                    });

                } else {
                    Log.d(TAG, "onResponse: " + response.errorBody().toString());
                }
                prog_ana.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<MainProductList> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                prog_ana.setVisibility(View.GONE);

            }
        });
    }

    private void getSubProduct(String id) {
        if (subListArrayAdapter != null) {
            subListArrayAdapter.clear();
            spn_sub.setText("");
            if (productArrayAdapter != null) {
                productArrayAdapter.clear();
                spn_descp_code.setText("");
            }

        }
        prog_alt.setVisibility(View.VISIBLE);
        SubGroupRequest request = new SubGroupRequest(id);
        subProductListCall = jsonApi.getSubProductListCall(request);
        subProductListCall.enqueue(new Callback<SubProductList>() {
            @Override
            public void onResponse(Call<SubProductList> call, Response<SubProductList> response) {
                if (response.isSuccessful()) {
                    final SubProductList list = response.body();
                    subList = list.getSubProductGroups();
                    fillspnSub(list.getSubProductGroups());
                    spn_sub.setOnFocusChangeListener((view, b) -> {
                        if (b) spn_sub.showDropDown();
                        else spn_sub.dismissDropDown();
                    });

                } else {
                    Log.d(TAG, "onResponse: ");
                }
                prog_alt.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<SubProductList> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                prog_alt.setVisibility(View.GONE);

            }
        });
    }

    private void getSubProductProduct(String id) {
        if (productArrayAdapter != null) {
            productArrayAdapter.clear();
            spn_descp_code.setText("");
        }


        prog_tanim.setVisibility(View.VISIBLE);
        SubGroupProductsRequest request = new SubGroupProductsRequest(id);
        productListAllCall = jsonApi.productListAll(request);
        productListAllCall.enqueue(new Callback<ProductListAll>() {
            @Override
            public void onResponse(Call<ProductListAll> call, Response<ProductListAll> response) {
                if (response.isSuccessful()) {
                    ProductListAll temp = response.body();
                    fillspnSubSub(temp.getProducts());
                    spn_descp_code.setOnFocusChangeListener((view, b) -> {
                        if (b) spn_descp_code.showDropDown();
                        else spn_descp_code.dismissDropDown();
                    });
                }
                prog_tanim.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ProductListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                prog_tanim.setVisibility(View.GONE);

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

            mainListArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            spn_main.setAdapter(mainListArrayAdapter);
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
            subListArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            spn_sub.setAdapter(subListArrayAdapter);
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
                    //filterSpnCode(subList.get(i).getInv_SubProductGroupid());
                    getSubProductProduct(subList.get(i).getInv_SubProductGroupid());

                }
            });
        } else {
            spn_sub.setAdapter(null);
            spn_sub.setOnClickListener(null);
        }

    }

    private void fillspnSubSub(List<Product> list) {
        if (list.size() > 0 && list != null) {
            productArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            spn_descp_code.setAdapter(productArrayAdapter);
            spn_descp_code.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    spn_descp_code.showDropDown();
                }
            });
            spn_descp_code.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //Get Code
                    // filterSpnCode(subList.get(i).getInv_SubProductGroupid());

                }
            });
        } else {
            spn_descp_code.setAdapter(null);
            spn_descp_code.setOnClickListener(null);
        }

    }

    private void fillspnCode(List<BreakdownCode> list) {
        if (list.size() > 0 && list != null) {

            breakdownCodeArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            spn_breakdowntype.setAdapter(breakdownCodeArrayAdapter);
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
            codeArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    filtered);
            spn_breakdowntype.setAdapter(codeArrayAdapter);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (breakdownCodeListAllCall != null)
            breakdownCodeListAllCall.cancel();
        if (mainProductListCall != null)
            mainProductListCall.cancel();
        if (subProductListCall != null)
            subProductListCall.cancel();
        if (productListAllCall != null)
            productListAllCall.cancel();
    }
}
