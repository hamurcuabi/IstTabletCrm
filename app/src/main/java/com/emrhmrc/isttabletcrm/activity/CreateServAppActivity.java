package com.emrhmrc.isttabletcrm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.api.APIHelper;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.bindingModel.ServiceAppointment;
import com.emrhmrc.isttabletcrm.helper.CreateSubServAppSingleton;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.models.Account.Account;
import com.emrhmrc.isttabletcrm.models.Account.AccountListAll;
import com.emrhmrc.isttabletcrm.models.Elevator.CustomerIdRequest;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorListAll;
import com.emrhmrc.isttabletcrm.models.Elevator.Elevators;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorsCustomer;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetById;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateServAppActivity extends AppCompatActivity {

    private static final String TAG = "CreateServAppActivity";
    @BindView(R.id.spinner_musteri)
    AutoCompleteTextView spinner_musteri;
    @BindView(R.id.edt_konu)
    EditText edtKonu;
    @BindView(R.id.spn_asansor)
    AutoCompleteTextView spnAsansor;
    @BindView(R.id.spn_ariza)
    Spinner spnAriza;
    @BindView(R.id.spn_oncelik)
    Spinner spnOncelik;
    @BindView(R.id.edt_isimsoyad)
    EditText edtIsimsoyad;
    @BindView(R.id.edt_onceki)
    EditText edtOnceki;
    @BindView(R.id.lnr_onceki)
    LinearLayout lnrOnceki;
    @BindView(R.id.edt_aciklama)
    EditText edtAciklama;
    private JsonApi jsonApi;
    private ServAppGetById servAppGetById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_serv_app);
        ButterKnife.bind(this);
        init();
        subOrNew();

    }

    private void subOrNew() {
        if (CreateSubServAppSingleton.getInstance().getServAppGetById() != null) {
            servAppGetById = CreateSubServAppSingleton.getInstance().getServAppGetById();
            fillAll(servAppGetById);
        } else {
            getAccountAll();
            lnrOnceki.setVisibility(View.GONE);
            edtIsimsoyad.setText(SingletonUser.getInstance().getUser().getUserName());
        }
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

            }
        });
    }

    private void fillAll(ServAppGetById model) {
        ServiceAppointment item = model.getServiceAppointment();
        Account account = new Account();
        account.setAccountId(item.getInv_CustomerId().getId());
        account.setName(item.getInv_CustomerId().getText());
        List<Account> list = new ArrayList<>();
        list.add(account);
        fillSpinner(list);
        edtKonu.setText(item.getSubject());
        edtKonu.setEnabled(false);
        fillElevatorSpinner(item.getInv_ElevatorId().getId(), item.getInv_ElevatorId().getText());
        spnAsansor.setEnabled(false);
        if(item.getInv_TypeCode()!=null)
        spnAriza.setSelection(item.getInv_TypeCode().getValue() - 1);
        if(item.getPriortiyCode()!=null)
        spnOncelik.setSelection(item.getPriortiyCode().getValue() - 1);
        edtIsimsoyad.setText(item.getOwnerId().getText());
        edtIsimsoyad.setEnabled(false);
        edtAciklama.setText(item.getInv_Description());
        edtAciklama.setEnabled(false);
        edtOnceki.setText(item.getActivityId());
        edtOnceki.setEnabled(false);
        lnrOnceki.setVisibility(View.VISIBLE);

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
               /* Account account = new Account();
                account.setAccountId("123456");
                account.setName("NameName");
                List<Account> list = new ArrayList<>();
                list.add(account);
                fillSpinner(list);*/
            }
        });
    }

    private void init() {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
    }

    private void fillSpinner(List<Account> list) {
        if (list.size() > 0 && list != null) {
            ArrayAdapter<Account> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,
                    list);
            //spinnerArrayAdapter.setDropDownViewResource(android.R.layout
            // .simple_spinner_dropdown_item);
            spinner_musteri.setAdapter(spinnerArrayAdapter);
            spinner_musteri.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    spinner_musteri.showDropDown();
                }
            });
            spinner_musteri.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    getElevatorByCustomerAll(list.get(i).getAccountId());
                }
            });
        } else {
            spinner_musteri.setAdapter(null);
            spinner_musteri.setOnClickListener(null);
        }

    }

    private void fillElevatorSpinner(List<ElevatorsCustomer> list) {
        if (list.size() > 0 && list != null) {
            ArrayAdapter<ElevatorsCustomer> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,
                    list);
            // spinnerArrayAdapter.setDropDownViewResource(android.R.layout
            // .simple_spinner_dropdown_item);
            spnAsansor.setAdapter(spinnerArrayAdapter);
            spnAsansor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    spnAsansor.showDropDown();
                }
            });
        } else {
            spnAsansor.setAdapter(null);
            spnAsansor.setOnClickListener(null);
        }

    }

    private void fillElevatorSpinner(String id, String name) {
        List<Elevators> list = new ArrayList<>();
        Elevators elevators = new Elevators();
        elevators.setInv_ElevatorId(id);
        elevators.setInv_ElevatorName(name);
        ArrayAdapter<Elevators> spinnerArrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item,
                list);
        // spinnerArrayAdapter.setDropDownViewResource(android.R.layout
        // .simple_spinner_dropdown_item);
        spnAsansor.setAdapter(spinnerArrayAdapter);


    }


    private void createNew() {

    }

    @OnClick({R.id.img_menu, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_menu:
                onBackPressed();
                break;
            case R.id.btn_save:
                createNew();
                break;
        }
    }
}
