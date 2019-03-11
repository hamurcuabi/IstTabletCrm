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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.api.APIHelper;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.models.Account.Account;
import com.emrhmrc.isttabletcrm.models.Account.AccountListAll;
import com.emrhmrc.isttabletcrm.models.Elevator.CustomerIdRequest;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorListAll;
import com.emrhmrc.isttabletcrm.models.Elevator.Elevators;
import com.emrhmrc.isttabletcrm.models.ServApp.CreateUnsuitability;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse2;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewUnstabilityFragment extends DialogFragment implements View.OnClickListener {

    private static final String TAG = "NewUnstabilityFragment";
    private ImageView img_close;
    private EditText edt_descp;
    private TextView edt_tarih;
    private Button btn_send;
    private JsonApi jsonApi;
    private AutoCompleteTextView spn_account, spnElevator;

    public static NewUnstabilityFragment newInstance() {
        Bundle args = new Bundle();
        NewUnstabilityFragment fragment = new NewUnstabilityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.newunstability_fragment, container);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        btn_send = view.findViewById(R.id.btn_send);
        img_close = view.findViewById(R.id.img_close);
        edt_descp = view.findViewById(R.id.edt_descp);
        edt_tarih = view.findViewById(R.id.edt_tarih);
        spnElevator = view.findViewById(R.id.spnElevator);
        spn_account = view.findViewById(R.id.spn_account);
        btn_send.setOnClickListener(this);
        img_close.setOnClickListener(this);
        edt_tarih.setOnClickListener(this);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
        getAccountAll();

    }

    @Override
    public void onResume() {
        super.onResume();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = width / 2;
        params.height = height;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                send();
                break;
            case R.id.img_close:
                dismiss();
                break;
            case R.id.edt_tarih:
                openDatePicker();
                break;


        }

    }

    private void openDatePicker() {
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
                        String monthString = String.valueOf(month);
                        if (monthString.length() == 1) {
                            monthString = "0" + monthString;
                        }
                        edt_tarih.setText(dayOfMonth + "." + monthString + "." + year);

                    }
                }, yil, ay, gun);
        dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", dpd);
        dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", dpd);
        dpd.show();
    }

    private void send() {
        CreateUnsuitability createUnsuitability = new CreateUnsuitability();
        createUnsuitability.setUserId(SingletonUser.getInstance().getUser().getUserId());
        createUnsuitability.setDescription(edt_descp.getText().toString());
        createUnsuitability.setSentOn(edt_tarih.getText().toString());
        createUnsuitability.setSubject("Test Subject");
        createUnsuitability.setServAppId(ShareData.getInstance().getServAppId());
        Call<DefaultResponse2> call = jsonApi.createUnsuitabilityCall(createUnsuitability);
        call.enqueue(new Callback<DefaultResponse2>() {
            @Override
            public void onResponse(Call<DefaultResponse2> call, Response<DefaultResponse2> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Succes");
                } else Log.d(TAG, "onResponse: Fail");
            }

            @Override
            public void onFailure(Call<DefaultResponse2> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    private void getElevatorByCustomerAll(String id) {


        Log.d(TAG, "getElevatorByCustomerAll: " + id);
        CustomerIdRequest request = new CustomerIdRequest(id);
        Call<ElevatorListAll> call = jsonApi.elevatorGetByCustomerId(request);
        APIHelper.enqueueWithRetry(call, new Callback<ElevatorListAll>() {
            @Override
            public void onResponse(Call<ElevatorListAll> call, Response<ElevatorListAll> response) {
                if (response.isSuccessful()) {

                    ElevatorListAll listAll = response.body();
                    fillElevatorSpinner(listAll.getElevators());


                } else Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<ElevatorListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });
    }

    private void fillElevatorSpinner(List<Elevators> list) {
        if (list.size() > 0 && list != null) {
            ArrayAdapter<Elevators> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            // spinnerArrayAdapter.setDropDownViewResource(android.R.layout
            // .simple_spinner_dropdown_item);
            spnElevator.setAdapter(spinnerArrayAdapter);
            spnElevator.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    spnElevator.showDropDown();
                }
            });
        } else {
            spnElevator.setAdapter(null);
            spnElevator.setOnClickListener(null);
        }

    }

    private void getAccountAll() {

        Call<AccountListAll> call = jsonApi.geAccountListAllCall();
        APIHelper.enqueueWithRetry(call, new Callback<AccountListAll>() {
            @Override
            public void onResponse(Call<AccountListAll> call, Response<AccountListAll> response) {
                if (response.isSuccessful()) {

                    AccountListAll listAll = response.body();
                    fillSpinner(listAll.getAccounts());

                } else Log.d(TAG, "onResponse: ");

            }

            @Override
            public void onFailure(Call<AccountListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void fillSpinner(List<Account> list) {
        if (list.size() > 0 && list != null) {
            ArrayAdapter<Account> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            //spinnerArrayAdapter.setDropDownViewResource(android.R.layout
            // .simple_spinner_dropdown_item);
            spn_account.setAdapter(spinnerArrayAdapter);
            spn_account.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    spn_account.showDropDown();
                }
            });
            spn_account.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    getElevatorByCustomerAll(list.get(i).getAccountId());
                }
            });
        } else {
            spn_account.setAdapter(null);
            spn_account.setOnClickListener(null);
        }

    }

}
