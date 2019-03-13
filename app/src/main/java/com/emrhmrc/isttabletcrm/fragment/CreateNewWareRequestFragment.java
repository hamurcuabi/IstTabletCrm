package com.emrhmrc.isttabletcrm.fragment;

import android.app.DatePickerDialog;
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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.api.APIHelper;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.models.Product.Product;
import com.emrhmrc.isttabletcrm.models.Product.ProductListAll;
import com.emrhmrc.isttabletcrm.models.User.UserIdRequest;
import com.emrhmrc.isttabletcrm.models.Warehouse.WareHouseListAll;
import com.emrhmrc.isttabletcrm.models.Warehouse.Warehouses;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateNewWareRequestFragment extends DialogFragment {
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
    @BindView(R.id.edt_birim2)
    EditText edtBirim2;
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
    private JsonApi jsonApi;


    public static CreateNewWareRequestFragment newInstance() {

        Bundle args = new Bundle();

        CreateNewWareRequestFragment fragment = new CreateNewWareRequestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_warehouse_request_create_fragment, container);
        ButterKnife.bind(this, view);
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        getDepo1();
        getProduct1();
        return view;

    }

    private void getDepo1() {
        UserIdRequest request = new UserIdRequest(SingletonUser.getInstance().getUser().getUserId());
        Call<WareHouseListAll> call = jsonApi.getWareHouseListAllCall(request);
        APIHelper.enqueueWithRetry(call, new Callback<WareHouseListAll>() {
            @Override
            public void onResponse(Call<WareHouseListAll> call, Response<WareHouseListAll> response) {
                if (response.isSuccessful()) {

                    WareHouseListAll listAll = response.body();
                    fillSpinner1(listAll.getWarehouses());
                    fillSpinner2(listAll.getWarehouses());

                } else Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<WareHouseListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void getProduct1() {
        UserIdRequest request = new UserIdRequest(SingletonUser.getInstance().getUser().getUserId());
        Call<ProductListAll> call = jsonApi.productListAll(request);
        APIHelper.enqueueWithRetry(call, new Callback<ProductListAll>() {
            @Override
            public void onResponse(Call<ProductListAll> call, Response<ProductListAll> response) {
                if (response.isSuccessful()) {

                    ProductListAll listAll = response.body();
                    fillSpinner11(listAll.getProducts());
                    fillSpinner22(listAll.getProducts());

                } else Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<ProductListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
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

    private void visibil2() {
        lnr1.setVisibility(View.GONE);
        lnr11.setVisibility(View.GONE);
        lnr2.setVisibility(View.VISIBLE);
        lnr22.setVisibility(View.VISIBLE);
        btnSend.setVisibility(View.GONE);
        btnSend2.setVisibility(View.VISIBLE);
    }

    private void visibil1() {
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
                visibil1();
                break;
            case R.id.rd_hurda:
                visibil2();
                break;
            case R.id.btn_send:
                createNewTransfer(1);
                break;
            case R.id.btn_send2:
                createNewTransfer(2);
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
                        if (i == 1) edtTarih.setText(dayOfMonth + "." + monthString + "." + year);
                        else if (i == 2)
                            edtTarih2.setText(dayOfMonth + "." + monthString + "." + year);

                    }
                }, yil, ay, gun);
        dpd.getDatePicker().setMinDate(takvim.getTime().getTime());
        dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", dpd);
        dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", dpd);
        dpd.show();
    }

    private void createNewTransfer(int i) {

    }

    private void fillSpinner1(List<Warehouses> list) {
        if (list.size() > 0 && list != null) {
            ArrayAdapter<Warehouses> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            spnIstenilen.setAdapter(spinnerArrayAdapter);
            spnIstenilen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    spnIstenilen.showDropDown();
                }
            });
            spnIstenilen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //getElevatorByCustomerAll(list.get(i).getAccountId());
                }
            });
        } else {
            spnIstenilen.setAdapter(null);
            spnIstenilen.setOnClickListener(null);
        }

    }

    private void fillSpinner11(List<Product> list) {
        if (list.size() > 0 && list != null) {
            ArrayAdapter<Product> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            spnUrunadi.setAdapter(spinnerArrayAdapter);
            spnUrunadi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    spnUrunadi.showDropDown();
                }
            });
            spnUrunadi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //getElevatorByCustomerAll(list.get(i).getAccountId());
                }
            });
        } else {
            spnUrunadi.setAdapter(null);
            spnUrunadi.setOnClickListener(null);
        }

    }

    private void fillSpinner22(List<Product> list) {
        if (list.size() > 0 && list != null) {
            ArrayAdapter<Product> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            spnUrunadi2.setAdapter(spinnerArrayAdapter);
            spnUrunadi2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    spnUrunadi2.showDropDown();
                }
            });
            spnUrunadi2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //getElevatorByCustomerAll(list.get(i).getAccountId());
                }
            });
        } else {
            spnUrunadi2.setAdapter(null);
            spnUrunadi2.setOnClickListener(null);
        }

    }

    private void fillSpinner2(List<Warehouses> list) {
        if (list.size() > 0 && list != null) {
            ArrayAdapter<Warehouses> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            spnCikisdepo.setAdapter(spinnerArrayAdapter);
            spnCikisdepo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    spnCikisdepo.showDropDown();
                }
            });
            spnCikisdepo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //getElevatorByCustomerAll(list.get(i).getAccountId());
                }
            });
        } else {
            spnCikisdepo.setAdapter(null);
            spnCikisdepo.setOnClickListener(null);
        }

    }


}
