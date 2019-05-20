package com.emrhmrc.isttabletcrm.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.SweetDialog.DialogCreater;
import com.emrhmrc.isttabletcrm.SweetDialog.SweetAlertDialog;
import com.emrhmrc.isttabletcrm.api.APIHelper;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.helper.ViewDialog;
import com.emrhmrc.isttabletcrm.models.CommonClass.Code;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Uom;
import com.emrhmrc.isttabletcrm.models.CommonClass.UomListAll;
import com.emrhmrc.isttabletcrm.models.Product.MainList;
import com.emrhmrc.isttabletcrm.models.Product.MainProductList;
import com.emrhmrc.isttabletcrm.models.Product.Product;
import com.emrhmrc.isttabletcrm.models.Product.ProductListAll;
import com.emrhmrc.isttabletcrm.models.Product.SubGroupProductsRequest;
import com.emrhmrc.isttabletcrm.models.Product.SubGroupRequest;
import com.emrhmrc.isttabletcrm.models.Product.SubList;
import com.emrhmrc.isttabletcrm.models.Product.SubProductList;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse;
import com.emrhmrc.isttabletcrm.models.User.UserIdRequest;
import com.emrhmrc.isttabletcrm.models.Warehouse.WareHouseListAll;
import com.emrhmrc.isttabletcrm.models.Warehouse.WarehouseTransferCreateRequest;
import com.emrhmrc.isttabletcrm.models.Warehouse.Warehouses;

import java.util.Calendar;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateNewWareRequestServappFragment extends DialogFragment {
    private static final String TAG = "CreateNewWareRequestFra";
    @BindView(R.id.spn_istenilen)
    AutoCompleteTextView spnIstenilen;
    @BindView(R.id.spn_urunadi)
    AutoCompleteTextView spnUrunadi;
    @BindView(R.id.spn_birim)
    AutoCompleteTextView spnBirim;
    @BindView(R.id.lnr1)
    LinearLayout lnr1;
    @BindView(R.id.edt_miktar)
    EditText edtMiktar;
    @BindView(R.id.edt_tarih)
    TextView edtTarih;
    @BindView(R.id.edt_aciklama)
    EditText edtAciklama;
    @BindView(R.id.lnr11)
    LinearLayout lnr11;
    @BindView(R.id.spn_cikisdepo)
    AutoCompleteTextView spnCikisdepo;
    @BindView(R.id.spn_urunadi2)
    AutoCompleteTextView spnUrunadi2;
    @BindView(R.id.spn_birim2)
    AutoCompleteTextView spnBirim2;
    @BindView(R.id.edt_aciklama2)
    EditText edtAciklama2;
    @BindView(R.id.lnr2)
    LinearLayout lnr2;
    @BindView(R.id.edt_miktar2)
    EditText edtMiktar2;
    @BindView(R.id.edt_tarih2)
    TextView edtTarih2;
    @BindView(R.id.edt_iadenedeni)
    EditText edtHurdanedeni;
    @BindView(R.id.lnr22)
    LinearLayout lnr22;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.btn_send2)
    Button btnSend2;
    @BindView(R.id.rd_new)
    RadioButton rdNew;
    @BindView(R.id.rd_hurda)
    RadioButton rdHurda;
    @BindView(R.id.rd_grup)
    RadioGroup rdGrup;
    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.edt_serino2)
    EditText edtSerino2;
    @BindView(R.id.spn_urunkodu)
    AutoCompleteTextView spnUrunkodu;
    @BindView(R.id.spn_anaurun)
    AutoCompleteTextView spnAnaurun;
    @BindView(R.id.spn_alturun)
    AutoCompleteTextView spnAlturun;
    @BindView(R.id.prog_depo)
    ProgressBar progDepo;
    @BindView(R.id.prog_ana)
    ProgressBar progAna;
    @BindView(R.id.prog_alt)
    ProgressBar progAlt;
    @BindView(R.id.prog_urun)
    ProgressBar progUrun;
    @BindView(R.id.progUrun2)
    ProgressBar progUrun2;
    @BindView(R.id.prog_birim)
    ProgressBar progBirim;
    @BindView(R.id.edt_baglioldugu_isemri)
    EditText edtBagliolduguIsemri;
    @BindView(R.id.spn_anaurun2)
    AutoCompleteTextView spnAnaurun2;
    @BindView(R.id.prog_ana2)
    ProgressBar progAna2;
    @BindView(R.id.spn_alturun2)
    AutoCompleteTextView spnAlturun2;
    @BindView(R.id.prog_alt2)
    ProgressBar progAlt2;
    @BindString(R.string.try_again)
    String try_again;
    private WarehouseTransferCreateRequest request_create_new;
    private WarehouseTransferCreateRequest request_return_back;
    private JsonApi jsonApi;
    private Call<UomListAll> uomListAllCall;
    private Call<WareHouseListAll> wareHouseListAllCall;
    private Call<DefaultResponse> createCall, returnCall;
    private ViewDialog viewDialog;
    private ArrayAdapter<MainList> mainListArrayAdapter;
    private ArrayAdapter<MainList> mainList2ArrayAdapter;
    private ArrayAdapter<SubList> subProductListArrayAdapter;
    private ArrayAdapter<SubList> subProductListArrayAdapter2;
    private ArrayAdapter<Product> productArrayAdapter;
    private Call<MainProductList> mainProductListCall;
    private Call<ProductListAll> productListAllCall2;
    private Call<SubProductList> subProductListCall;
    private Call<SubProductList> subProductListCall2;
    private Call<ProductListAll> productListAllCall3;
    private Call<ProductListAll> productListAllCall4;

    public static CreateNewWareRequestServappFragment newInstance() {

        Bundle args = new Bundle();

        CreateNewWareRequestServappFragment fragment = new CreateNewWareRequestServappFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_warehouse_request_create_fragment, container);
        ButterKnife.bind(this, view);
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        request_create_new = new WarehouseTransferCreateRequest();
        request_return_back = new WarehouseTransferCreateRequest();
        viewDialog = new ViewDialog(getActivity());
        edtBagliolduguIsemri.setText(ShareData.getInstance().getServAppId());
        getWarehouses();
        getMainProductGroup();
        getUoms();
        focusing();
        return view;

    }

    private void getMainProductGroup() {

        progAna.setVisibility(View.VISIBLE);
        progAna2.setVisibility(View.VISIBLE);
        mainProductListCall = jsonApi.getMainProductListCall();
        mainProductListCall.enqueue(new Callback<MainProductList>() {
            @Override
            public void onResponse(Call<MainProductList> call, Response<MainProductList> response) {
                if (response.isSuccessful()) {
                    if (response.body().Success) {
                        MainProductList model = response.body();
                        fillSpinnersMainProducts(model.getMainProductGroups());
                        fillSpinnersMain2Products(model.getMainProductGroups());
                    } else {
                        DialogCreater.errorDialog(getActivity(), response.body().ErrorMsg);
                    }

                } else {
                    DialogCreater.errorDialog(getActivity(), try_again);
                }
                progAna.setVisibility(View.GONE);
                progAna2.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<MainProductList> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                if (getDialog() != null && getDialog().isShowing()) {
                    DialogCreater.errorDialog(getActivity(), try_again);
                }
                progAna.setVisibility(View.GONE);
            }
        });
    }


    private void getSubProductGroup(String id) {

        progAlt.setVisibility(View.VISIBLE);
        SubGroupRequest request = new SubGroupRequest(id);
        subProductListCall = jsonApi.getSubProductListCall(request);
        subProductListCall.enqueue(new Callback<SubProductList>() {
            @Override
            public void onResponse(Call<SubProductList> call, Response<SubProductList> response) {
                if (response.isSuccessful()) {
                    if (response.body().Success) {
                        final SubProductList list = response.body();
                        fillSpinnersSubProducts(list.getSubProductGroups());
                    } else {
                        DialogCreater.errorDialog(getActivity(), try_again);
                    }
                } else {

                    DialogCreater.errorDialog(getActivity(), try_again);
                }
                progAlt.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<SubProductList> call, Throwable t) {
                if (getDialog() != null && getDialog().isShowing()) {
                    DialogCreater.errorDialog(getActivity(), try_again);
                }
                progAlt.setVisibility(View.GONE);
            }
        });
    }

    private void getSubProduct2Group(String id) {

        progAlt2.setVisibility(View.VISIBLE);
        SubGroupRequest request = new SubGroupRequest(id);
        subProductListCall2 = jsonApi.getSubProductListCall(request);
        subProductListCall2.enqueue(new Callback<SubProductList>() {
            @Override
            public void onResponse(Call<SubProductList> call, Response<SubProductList> response) {
                if (response.isSuccessful()) {
                    if (response.body().Success) {
                        final SubProductList list = response.body();
                        fillSpinnersSub2Products(list.getSubProductGroups());
                    } else {

                        DialogCreater.errorDialog(getActivity(), response.body().ErrorMsg);
                    }
                } else {

                    DialogCreater.errorDialog(getActivity(), try_again);

                }
                progAlt2.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<SubProductList> call, Throwable t) {
                if (getDialog() != null && getDialog().isShowing()) {
                    DialogCreater.errorDialog(getActivity(), try_again);
                }
                progAlt2.setVisibility(View.GONE);
            }
        });
    }

    private void getProducList(String id) {
        progUrun.setVisibility(View.VISIBLE);
        SubGroupProductsRequest request = new SubGroupProductsRequest(id);
        productListAllCall3 = jsonApi.productListAll(request);
        productListAllCall3.enqueue(new Callback<ProductListAll>() {
            @Override
            public void onResponse(Call<ProductListAll> call, Response<ProductListAll> response) {
                if (response.isSuccessful()) {
                    if (response.body().Success) {

                        ProductListAll temp = response.body();
                        fillSpinnersProducts(temp.getProducts());
                    } else {

                        DialogCreater.errorDialog(getActivity(), response.body().ErrorMsg);
                    }

                }
                progUrun.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ProductListAll> call, Throwable t) {
                if (getDialog() != null && getDialog().isShowing()) {
                    DialogCreater.errorDialog(getActivity(), try_again);
                }
                progUrun.setVisibility(View.GONE);
            }
        });

    }

    private void getProducList2(String id) {
        progUrun2.setVisibility(View.VISIBLE);
        SubGroupProductsRequest request = new SubGroupProductsRequest(id);
        productListAllCall4 = jsonApi.productListAll(request);
        productListAllCall4.enqueue(new Callback<ProductListAll>() {
            @Override
            public void onResponse(Call<ProductListAll> call, Response<ProductListAll> response) {
                if (response.isSuccessful()) {

                    if (response.body().Success) {
                        ProductListAll temp = response.body();
                        fillSpinnersProducts2(temp.getProducts());
                    } else {
                        DialogCreater.errorDialog(getActivity(), response.body().ErrorMsg);

                    }

                } else {

                    DialogCreater.errorDialog(getActivity(), try_again);
                }
                progUrun2.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ProductListAll> call, Throwable t) {
                if (getDialog() != null && getDialog().isShowing()) {
                    DialogCreater.errorDialog(getActivity(), try_again);
                }
                progUrun2.setVisibility(View.GONE);
            }
        });

    }

    private void fillSpinnersMainProducts(List<MainList> list) {
        if (list.size() > 0 && list != null) {
            mainListArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            spnAnaurun.setAdapter(mainListArrayAdapter);
            spnAnaurun.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    final MainList current = list.get(i);
                    getSubProductGroup(current.getInv_MainProductGroupid());

                    InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                }
            });
            spnAnaurun.showDropDown();

        } else {
            spnAnaurun.setAdapter(null);
            spnAnaurun.setOnClickListener(null);

        }

    }

    private void fillSpinnersMain2Products(List<MainList> list) {
        if (list.size() > 0 && list != null) {
            mainList2ArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            spnAnaurun2.setAdapter(mainList2ArrayAdapter);
            spnAnaurun2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    final MainList current = list.get(i);
                    getSubProduct2Group(current.getInv_MainProductGroupid());

                    InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                }
            });
            spnAnaurun2.showDropDown();

        } else {
            spnAnaurun2.setAdapter(null);
            spnAnaurun2.setOnClickListener(null);

        }

    }

    private void fillSpinnersSubProducts(List<SubList> list) {
        if (list.size() > 0 && list != null) {
            subProductListArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            spnAlturun.setAdapter(subProductListArrayAdapter);
            spnAlturun.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    final SubList current = list.get(i);
                    getProducList(current.getInv_SubProductGroupid());
                    InputMethodManager in =
                            (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                }
            });
            spnAlturun.showDropDown();
        } else {
            spnAlturun.setAdapter(null);
            spnAlturun.setOnClickListener(null);

        }

    }

    private void fillSpinnersSub2Products(List<SubList> list) {
        if (list.size() > 0 && list != null) {
            subProductListArrayAdapter2 = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            spnAlturun2.setAdapter(subProductListArrayAdapter2);
            spnAlturun2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    final SubList current = list.get(i);
                    getProducList2(current.getInv_SubProductGroupid());
                    InputMethodManager in =
                            (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                }
            });
            spnAlturun2.showDropDown();
        } else {
            spnAlturun2.setAdapter(null);
            spnAlturun2.setOnClickListener(null);

        }

    }

    private void fillSpinnersWareHouse(List<Warehouses> list) {
        if (list.size() > 0 && list != null) {
            ArrayAdapter<Warehouses> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            ArrayAdapter<Warehouses> spinnerArrayAdapter2 = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            spnIstenilen.setAdapter(spinnerArrayAdapter);
            spnIstenilen.showDropDown();
            spnIstenilen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    spnIstenilen.showDropDown();
                }
            });
            spnIstenilen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    request_create_new.setInv_ToWarehouseid(new Inv_Id("inv_warehouse",
                            list.get(i).getInv_WarehouseName(), list.get(i).getInv_WarehouseId()));
                    //request.setInv_FromWarehouseid(list.get(i).getInv_ParentWhid());
                    InputMethodManager in =
                            (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                }
            });

            spnCikisdepo.setAdapter(spinnerArrayAdapter2);
            spnCikisdepo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    spnCikisdepo.showDropDown();
                }
            });
            spnCikisdepo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    request_return_back.setInv_FromWarehouseid(new Inv_Id("inv_warehouse",
                            list.get(i).getInv_WarehouseName(), list.get(i).getInv_WarehouseId()));
                    InputMethodManager in =
                            (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                }
            });
        } else {
            spnCikisdepo.setAdapter(null);
            spnCikisdepo.setAdapter(null);
            spnIstenilen.setOnClickListener(null);
            spnIstenilen.setOnClickListener(null);
        }

    }

    private void fillSpinnersProducts(List<Product> list) {
        if (list.size() > 0 && list != null) {
            productArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            spnUrunadi.setAdapter(productArrayAdapter);
            spnUrunadi.showDropDown();
            spnUrunadi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    request_create_new.setInv_Productid(new Inv_Id("product", list.get(i).getName(),
                            list.get(i).getProductId()));
                    request_create_new.setInv_ProductSerialNumber(list.get(i).getProductNumber());
                    spnUrunkodu.setText(list.get(i).getProductNumber());
                    InputMethodManager in =
                            (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                }
            });


        } else {
            spnUrunadi.setAdapter(null);
            spnUrunadi.setOnClickListener(null);

        }

    }

    private void fillSpinnersProducts2(List<Product> list) {
        if (list.size() > 0 && list != null) {

            ArrayAdapter<Product> spinnerArrayAdapter2 = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            spnUrunadi2.setAdapter(spinnerArrayAdapter2);
            spnUrunadi2.showDropDown();
            spnUrunadi2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    request_return_back.setInv_Productid(new Inv_Id("product", list.get(i).getName(),
                            list.get(i).getProductId()));
                    request_return_back.setInv_ProductSerialNumber(list.get(i).getProductNumber());
                    // edtSerino2.setText(list.get(i).getProductNumber());
                    InputMethodManager in =
                            (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                }
            });

        } else {
            spnUrunadi.setAdapter(null);
            spnUrunadi.setOnClickListener(null);
            spnUrunadi2.setAdapter(null);
            spnUrunadi2.setOnClickListener(null);
        }

    }

    private void fillSpinnersUom(List<Inv_Uom> list) {
        if (list.size() > 0 && list != null) {
            ArrayAdapter<Inv_Uom> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            spnBirim.setAdapter(spinnerArrayAdapter);
            spnBirim.showDropDown();
            spnBirim.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    request_create_new.setInv_Uomid(new Inv_Id("uom", list.get(i).getName(), list.get(i).getUoMId()));
                    InputMethodManager in =
                            (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                }
            });

            ArrayAdapter<Inv_Uom> spinnerArrayAdapter2 = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            spnBirim2.setAdapter(spinnerArrayAdapter2);
            spnBirim2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    request_return_back.setInv_Uomid(new Inv_Id("uom", list.get(i).getName(),
                            list.get(i).getUoMId()));
                    InputMethodManager in =
                            (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                }
            });
        } else {
            spnBirim.setAdapter(null);
            spnBirim.setOnClickListener(null);
            spnBirim2.setAdapter(null);
            spnBirim2.setOnClickListener(null);
        }

    }

    private void getUoms() {
        progBirim.setVisibility(View.VISIBLE);
        uomListAllCall = jsonApi.getUomListAllCall();
        APIHelper.enqueueWithRetry(uomListAllCall, new Callback<UomListAll>() {
            @Override
            public void onResponse(Call<UomListAll> call, Response<UomListAll> response) {
                if (response.isSuccessful()) {
                    if (response.body().Success) {
                        final UomListAll model = response.body();
                        fillSpinnersUom(model.getUomList());
                    } else {
                        DialogCreater.errorDialog(getActivity(), response.body().ErrorMsg);
                    }
                } else {
                    DialogCreater.errorDialog(getActivity(), try_again);
                }
                progBirim.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<UomListAll> call, Throwable t) {
                progBirim.setVisibility(View.GONE);
                Log.d(TAG, "onFailure: " + t.getMessage());
                if (getDialog() != null && getDialog().isShowing()) {
                    DialogCreater.errorDialog(getActivity(), try_again);
                }
            }
        });
    }

    private void focusing() {
        spnIstenilen.setOnFocusChangeListener((view, b) -> {
            if (b) spnIstenilen.showDropDown();
            else spnIstenilen.dismissDropDown();
        });
        spnUrunadi2.setOnFocusChangeListener((view, b) -> {
            if (b) spnUrunadi2.showDropDown();
            else spnUrunadi2.dismissDropDown();
        });
        spnUrunadi.setOnFocusChangeListener((view, b) -> {
            if (b) spnUrunadi.showDropDown();
            else spnUrunadi.dismissDropDown();
        });
        spnCikisdepo.setOnFocusChangeListener((view, b) -> {
            if (b) spnCikisdepo.showDropDown();
            else spnCikisdepo.dismissDropDown();
        });
        spnBirim.setOnFocusChangeListener((view, b) -> {
            if (b) spnBirim.showDropDown();
            else spnBirim.dismissDropDown();
        });
        spnBirim2.setOnFocusChangeListener((view, b) -> {
            if (b) spnBirim2.showDropDown();
            else spnBirim2.dismissDropDown();
        });
        spnAlturun.setOnFocusChangeListener((view, b) -> {
            if (b) spnAlturun.showDropDown();
            else spnAlturun.dismissDropDown();
        });
        spnAnaurun2.setOnFocusChangeListener((view, b) -> {
            if (b) spnAnaurun2.showDropDown();
            else spnAnaurun2.dismissDropDown();
        });
        spnAnaurun.setOnFocusChangeListener((view, b) -> {
            if (b) spnAnaurun.showDropDown();
            else spnAnaurun.dismissDropDown();
        });
        spnAlturun2.setOnFocusChangeListener((view, b) -> {
            if (b) spnAlturun2.showDropDown();
            else spnAlturun2.dismissDropDown();
        });


        spnIstenilen.setOnClickListener(view -> {
            spnIstenilen.showDropDown();
        });
        spnUrunadi2.setOnClickListener(view -> {
            spnUrunadi2.showDropDown();
        });
        spnUrunadi.setOnClickListener(view -> {
            spnUrunadi.showDropDown();
        });
        spnCikisdepo.setOnClickListener(view -> {
            spnCikisdepo.showDropDown();
        });
        spnBirim.setOnClickListener(view -> {
            spnBirim.showDropDown();
        });
        spnBirim2.setOnClickListener(view -> {
            spnBirim2.showDropDown();
        });
        spnAlturun.setOnClickListener(view -> {
            spnAlturun.showDropDown();
        });
        spnAnaurun.setOnClickListener(view -> {
            spnAnaurun.showDropDown();
        });
        spnAnaurun2.setOnClickListener(view -> {
            spnAnaurun2.showDropDown();
        });
    }

    private void getWarehouses() {
        progDepo.setVisibility(View.VISIBLE);
        UserIdRequest request = new UserIdRequest(SingletonUser.getInstance().getUser().getUserId());
        wareHouseListAllCall = jsonApi.getWareHouseListAllCall(request);
        APIHelper.enqueueWithRetry(wareHouseListAllCall, new Callback<WareHouseListAll>() {
            @Override
            public void onResponse(Call<WareHouseListAll> call, Response<WareHouseListAll> response) {
                if (response.isSuccessful()) {
                    if (response.body().Success) {
                        WareHouseListAll listAll = response.body();
                        fillSpinnersWareHouse(listAll.getWarehouses());
                    } else {
                        DialogCreater.errorDialog(getActivity(), response.body().ErrorMsg);
                    }

                } else {
                    DialogCreater.errorDialog(getActivity(), try_again);

                }
                progDepo.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<WareHouseListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                progDepo.setVisibility(View.GONE);
                if (getDialog() != null && getDialog().isShowing()) {
                    DialogCreater.errorDialog(getActivity(), try_again);
                }
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);


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
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
    }

    private void visibilityReturn() {
        lnr1.setVisibility(View.GONE);
        lnr11.setVisibility(View.GONE);
        lnr2.setVisibility(View.VISIBLE);
        lnr22.setVisibility(View.VISIBLE);
        btnSend.setVisibility(View.GONE);
        btnSend2.setVisibility(View.VISIBLE);
    }

    private void visibilityCreate() {
        lnr1.setVisibility(View.VISIBLE);
        lnr11.setVisibility(View.VISIBLE);
        lnr2.setVisibility(View.GONE);
        lnr22.setVisibility(View.GONE);
        btnSend2.setVisibility(View.GONE);
        btnSend.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.rd_new, R.id.rd_hurda, R.id.btn_send, R.id.btn_send2, R.id.edt_tarih,
            R.id.edt_tarih2, R.id.img_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rd_new:
                visibilityCreate();
                break;
            case R.id.rd_hurda:
                visibilityReturn();
                break;
            case R.id.btn_send:
                createNewTransfer();
                break;
            case R.id.btn_send2:
                createReturnransfer();
                break;
            case R.id.edt_tarih:
                openDatePicker(1);
                break;
            case R.id.edt_tarih2:
                openDatePicker(2);
                break;
            case R.id.img_close:
                dismiss();
                break;

        }
    }

    private void openDatePicker(int i) {
        // Şimdiki zaman bilgilerini alıyoruz. güncel yıl, güncel ay, güncel gün.
        final Calendar takvim = Calendar.getInstance();
        int yil = takvim.get(Calendar.YEAR);
        int ay = takvim.get(Calendar.MONTH);
        int gun = takvim.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // ay değeri 0 dan başladığı için (Ocak=0, Şubat=1,..,Aralık=11)
                        // değeri 1 artırarak gösteriyoruz.
                        month += 1;
                        // year, month ve dayOfMonth değerleri seçilen tarihin değerleridir.
                        // Edittextte bu değerleri gösteriyoruz.
                        String monthString = String.valueOf(month);
                        if (monthString.length() == 1) {
                            monthString = "0" + monthString;
                        }
                        if (i == 1) edtTarih.setText(monthString + "." + dayOfMonth + "." + year);
                        else if (i == 2)
                            edtTarih2.setText(monthString + "." + dayOfMonth + "." + year);

                    }
                }, yil, ay, gun);
        dpd.getDatePicker().setMinDate(takvim.getTime().getTime());
        dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", dpd);
        dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", dpd);
        dpd.show();
    }

    private void createNewTransfer() {
        request_create_new.setUserId(SingletonUser.getInstance().getUser().getUserId());
        request_create_new.setInv_Description(edtAciklama.getText().toString());
        request_create_new.setInv_RequestDate(edtTarih.getText().toString());
        request_create_new.setInv_TransferTypeCode(new Code("Yeni Stok", 1));
        request_create_new.setInv_Quantity(edtMiktar.getText().toString());
        request_create_new.setInv_WarehouseTransferName("TEST TABLET");
        request_create_new.setInv_FromWarehouseid(null);
        Log.d(TAG, "createNewTransfer: " + request_create_new.getInv_ProductSerialNumber());
        if (checkFieldsToCreate(request_create_new)) {
            viewDialog.showDialog();
            createCall = jsonApi.createTransfer(request_create_new);
            APIHelper.enqueueWithRetry(createCall, new Callback<DefaultResponse>() {
                @Override
                public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                    if (response.isSuccessful()) {
                        viewDialog.hideDialog();
                        dismiss();
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Başarılı")
                                .show();

                    } else {
                        viewDialog.hideDialog();
                        if (getDialog() != null && getDialog().isShowing()) {
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText(response.message())
                                    .show();
                        }
                    }

                }

                @Override
                public void onFailure(Call<DefaultResponse> call, Throwable t) {
                    viewDialog.hideDialog();
                    Log.d(TAG, "onFailure: " + t.getMessage());
                    if (getDialog() != null && getDialog().isShowing()) {
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                .setTitleText(getResources().getString(R.string.toast_error))
                                .show();
                    }
                }
            });

        } else {
            if (getDialog() != null && getDialog().isShowing()) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Eksik Alanları Doldurunuz")
                        .show();
            }


        }

    }

    private void createReturnransfer() {
        request_return_back.setInv_ProductSerialNumber(edtSerino2.getText().toString());
        request_return_back.setUserId(SingletonUser.getInstance().getUser().getUserId());
        request_return_back.setInv_Description(edtAciklama2.getText().toString());
        request_return_back.setInv_RequestDate(edtTarih2.getText().toString());
        request_return_back.setInv_TransferTypeCode(new Code("İade Et", 2));
        request_return_back.setInv_Quantity(edtMiktar2.getText().toString());
        request_return_back.setInv_WarehouseTransferName("TEST TABLET");
        request_return_back.setInv_ToWarehouseid(null);
        if (checkFieldsToReturn(request_return_back)) {

            viewDialog.showDialog();
            returnCall = jsonApi.createTransfer(request_return_back);
            APIHelper.enqueueWithRetry(returnCall, new Callback<DefaultResponse>() {
                @Override
                public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                    if (response.isSuccessful()) {
                        viewDialog.hideDialog();
                        dismiss();
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Başarılı")
                                .show();

                    }
                    viewDialog.hideDialog();
                    if (getDialog() != null && getDialog().isShowing()) {
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                .setTitleText(response.message())
                                .show();
                    }
                }

                @Override
                public void onFailure(Call<DefaultResponse> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                    viewDialog.hideDialog();
                    if (getDialog() != null && getDialog().isShowing()) {
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                .setTitleText(getResources().getString(R.string.toast_error))
                                .show();
                    }

                }
            });

        } else {
            if (getDialog() != null && getDialog().isShowing()) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Eksik Alanları Doldurunuz")
                        .show();
            }
        }

    }

    private boolean checkFieldsToCreate(WarehouseTransferCreateRequest request) {
        if (request.getInv_Description() == null) {
            return false;
        } else if (request.getInv_Productid() == null) {
            return false;
        } else if (request.getInv_Quantity() == null) {
            return false;
        } else if (request.getInv_RequestDate() == null) {
            return false;
        } else if (request.getInv_WarehouseTransferName() == null) {
            return false;
        } else if (request.getInv_Uomid() == null) {
            return false;
        } else if (request.getInv_ToWarehouseid() == null) {
            return false;
        } else if (request.getInv_TransferTypeCode() == null) {
            return false;
        } else if (request.getInv_ProductSerialNumber() == null) {
            return false;
        } else return true;
    }

    private boolean checkFieldsToReturn(WarehouseTransferCreateRequest request) {
        if (request.getInv_Description() == null) {
            return false;
        } else if (request.getInv_FromWarehouseid() == null) {
            return false;
        } else if (request.getInv_Productid() == null) {
            return false;
        } else if (request.getInv_Quantity() == null) {
            return false;
        } else if (request.getInv_RequestDate() == null) {
            return false;
        } else if (request.getInv_WarehouseTransferName() == null) {
            return false;
        } else if (request.getInv_Uomid() == null) {
            return false;
        } else if (request.getInv_TransferTypeCode() == null) {
            return false;
        } else if (request.getInv_ProductSerialNumber() == null) {
            return false;
        } else if (TextUtils.isEmpty(request.getInv_ProductSerialNumber())) {
            return false;
        } else return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (uomListAllCall != null) uomListAllCall.cancel();
        if (wareHouseListAllCall != null) wareHouseListAllCall.cancel();
        if (productListAllCall2 != null) productListAllCall2.cancel();
        if (returnCall != null) returnCall.cancel();
        if (createCall != null) createCall.cancel();
        if (mainProductListCall != null) mainProductListCall.cancel();
        if (subProductListCall != null) subProductListCall.cancel();
        if (productListAllCall3 != null) productListAllCall3.cancel();
        if (productListAllCall4 != null) productListAllCall4.cancel();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
