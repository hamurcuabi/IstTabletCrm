package com.emrhmrc.isttabletcrm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.bindingModel.ServiceAppointment;
import com.emrhmrc.isttabletcrm.helper.CreateSubServAppSingleton;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.models.Account.Account;
import com.emrhmrc.isttabletcrm.models.Account.AccountListAll;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetById;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateServAppActivity extends AppCompatActivity {

    private static final String TAG = "CreateServAppActivity";
    @BindView(R.id.spinner_musteri)
    Spinner spinner_musteri;
    @BindView(R.id.edt_konu)
    EditText edtKonu;
    @BindView(R.id.edt_asansor)
    EditText edtAsansor;
    @BindView(R.id.spn_saat)
    Spinner spnSaat;
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
        }
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
        edtAsansor.setText(item.getInv_ElevatorId().getText());
        edtAsansor.setEnabled(false);
        spnAriza.setSelection(item.getInv_TypeCode().getValue() - 1);
        spnOncelik.setSelection(item.getPriortiyCode().getValue()-1);
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
        call.enqueue(new Callback<AccountListAll>() {
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
        ArrayAdapter<Account> spinnerArrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item,
                list);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_musteri.setAdapter(spinnerArrayAdapter);


    }
}
