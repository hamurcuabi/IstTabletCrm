package com.emrhmrc.isttabletcrm.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.SweetDialog.SweetAlertDialog;
import com.emrhmrc.isttabletcrm.api.APIHelper;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.AddManuelProduct;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;
import com.emrhmrc.isttabletcrm.models.CommonClass.UomListAll;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppDetails;
import com.emrhmrc.isttabletcrm.util.StringUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddManuelProductFragment extends DialogFragment implements View.OnClickListener {

    private static final String TAG = "AddManuelProductFragmen";
    private JsonApi jsonApi;
    private ImageView img_close;
    private AutoCompleteTextView spn_birim;
    private Spinner spn_ucretli;
    private EditText edt_urunadi, edt_miktar, edt_fiyat, edt_descp;
    private Button btn_save;
    private Call<UomListAll> call;
    private AddManuelProduct addManuelProduct;
    private Inv_Id selected;

    public static AddManuelProductFragment newInstance() {
        Bundle args = new Bundle();
        AddManuelProductFragment fragment = new AddManuelProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.addproduct_fragment, container);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        addManuelProduct = (AddManuelProduct) getActivity();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);


    }

    private void init(View view) {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        img_close = view.findViewById(R.id.img_close);
        img_close.setOnClickListener(this);
        spn_birim = view.findViewById(R.id.spn_birim);
        spn_ucretli = view.findViewById(R.id.spn_ucretli);
        edt_descp = view.findViewById(R.id.edt_descp);
        edt_urunadi = view.findViewById(R.id.edt_urunadi);
        edt_miktar = view.findViewById(R.id.edt_miktar);
        edt_fiyat = view.findViewById(R.id.edt_fiyat);
        btn_save = view.findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        spn_ucretli.setEnabled(false);
        spn_ucretli.setSelection(0);
        getUoms();

    }

    private void getUoms() {

        call = jsonApi.getUomListAllCall();
        APIHelper.enqueueWithRetry(call, new Callback<UomListAll>() {
            @Override
            public void onResponse(Call<UomListAll> call, Response<UomListAll> response) {
                if (response.isSuccessful()) {
                    final UomListAll model = response.body();
                    fillSpinnerUom(model.getUomList());
                } else Log.d(TAG, "onResponse: " + response.errorBody());

            }

            @Override
            public void onFailure(Call<UomListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                if (getDialog() != null && getDialog().isShowing()) {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(getResources().getString(R.string.toast_error))
                            .show();
                }
            }
        });
    }

    private void fillSpinnerUom(List<Inv_Id> list) {
        if (list.size() > 0 && list != null) {
            ArrayAdapter<Inv_Id> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            spn_birim.setAdapter(spinnerArrayAdapter);
            spn_birim.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    selected = list.get(i);
                }
            });
        } else {
            spn_birim.setAdapter(null);
            spn_birim.setOnClickListener(null);
        }
        focusing();

    }

    private void focusing() {
        spn_birim.setOnFocusChangeListener((view, b) -> {
            if (b) spn_birim.showDropDown();
            else spn_birim.dismissDropDown();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        WindowManager.LayoutParams p = getDialog().getWindow().getAttributes();
        p.width = ViewGroup.LayoutParams.MATCH_PARENT;
        p.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes(p);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.img_close:
                dismiss();
                break;
            case R.id.btn_save:
                addProduct();
                break;

        }
    }

    private void addProduct() {
        if (checkFields()) {
            ServAppGetByIdServAppDetails add = new ServAppGetByIdServAppDetails();
            add.setInv_ProductId(new Inv_Id("inv_subproductgroup", edt_urunadi.getText().toString(), null));
            add.setManuel(true);
            add.setInv_Description(edt_descp.getText().toString());
            add.setInv_Uomid(selected);
            add.setInv_Quantity(StringUtil.convertStringToınt(edt_miktar.getText().toString()));
            addManuelProduct.addProduct(add);
        } else {
            if (getDialog() != null && getDialog().isShowing()) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText(getResources().getString(R.string.bos_alan))
                        .show();
            }
        }
    }

    private boolean checkFields() {
        if (TextUtils.isEmpty(edt_descp.getText())) return false;
        else if (TextUtils.isEmpty(edt_miktar.getText())) return false;
        else if (TextUtils.isEmpty(edt_fiyat.getText())) return false;
        else if (TextUtils.isEmpty(edt_urunadi.getText())) return false;
        else if (selected == null) return false;
        else return true;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (call != null) call.cancel();
    }
}
