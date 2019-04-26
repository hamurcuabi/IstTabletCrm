package com.emrhmrc.isttabletcrm.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.SweetDialog.AnyDialog;
import com.emrhmrc.isttabletcrm.SweetDialog.DialogCreater;
import com.emrhmrc.isttabletcrm.SweetDialog.SweetAlertDialog;
import com.emrhmrc.isttabletcrm.api.APIHelper;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.bindingModel.ServiceAppointment;
import com.emrhmrc.isttabletcrm.helper.CreateSubServAppSingleton;
import com.emrhmrc.isttabletcrm.helper.Methodes;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.models.Account.Account;
import com.emrhmrc.isttabletcrm.models.Account.AccountListAll;
import com.emrhmrc.isttabletcrm.models.CommonClass.Code_Id;
import com.emrhmrc.isttabletcrm.models.CommonClass.FilterModel;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id_Id;
import com.emrhmrc.isttabletcrm.models.Elevator.CustomerIdRequest;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorListAll;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorsCustomer;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse2;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetById;
import com.emrhmrc.isttabletcrm.models.ServApp.UpsertByIdCreateRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.serviceAppointment;
import com.emrhmrc.isttabletcrm.util.StringUtil;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateServAppActivity extends AppCompatActivity {

    private static final String TAG = "CreateServAppActivity";
    private static final String TAG_DATETIME_FRAGMENT = "TAG_DATETIME_FRAGMENT";
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
    @BindView(R.id.edt_saat)
    TextView edtSaat;
    @BindView(R.id.prog_acoount)
    ProgressBar progAcoount;
    @BindView(R.id.prog_elevator)
    ProgressBar progElevator;
    @BindView(R.id.edt_supervisor)
    EditText edtSupervisor;
    @BindString(R.string.loading)
    String loading;
    @BindString(R.string.succes)
    String succes;
    @BindString(R.string.fillblanks)
    String blanks;
    @BindString(R.string.sub_servapp)
    String sub;
    @BindString(R.string.create_sub_servapp)
    String crete_sub;
    @BindString(R.string.try_again)
    String try_again;
    @BindView(R.id.txt_menu_header)
    TextView txtMenuHeader;
    @BindView(R.id.txt_yeni)
    TextView txtYeni;
    @BindView(R.id.edt_konum)
    EditText edtKonum;
    @BindString(R.string.no_elevator)
    String no_elevator;
    private JsonApi jsonApi;
    private ServAppGetById servAppGetById;
    private serviceAppointment request;
    private SweetAlertDialog dialog;
    private SwitchDateTimeDialogFragment dateTimeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_serv_app);
        ButterKnife.bind(this);
        init();
        initDateAndTime();
        subOrNew();
        focusing();
        getSpnOncelik();
        initDialog();
        if (ShareData.getInstance().isSub_servapp()) {
            txtMenuHeader.setText(sub);
            txtYeni.setText(crete_sub);
        }

    }

    private void fillServAppTypeAndPrioritySpinner() {

        List<FilterModel> servApptype = new ArrayList<>();
        List<FilterModel> priorty = new ArrayList<>();

        servApptype.add(new FilterModel(1, "Yedek Parça Değişimi"));
        servApptype.add(new FilterModel(2, "Bakım"));
        servApptype.add(new FilterModel(5, "Durum Tespit"));
        servApptype.add(new FilterModel(7, "Açık Servis"));

        priorty.add(new FilterModel(0, "Düşük"));
        priorty.add(new FilterModel(1, "Normal"));
        priorty.add(new FilterModel(2, "Yüksek"));

        ArrayAdapter<FilterModel> spinnerArrayAdapter = new ArrayAdapter<>(
                this, R.layout.spinner_item_white, servApptype);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnAriza.setAdapter(spinnerArrayAdapter);

        ArrayAdapter<FilterModel> spinnerArrayAdapter2 = new ArrayAdapter<>(
                this, R.layout.spinner_item_white, priorty);
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnOncelik.setAdapter(spinnerArrayAdapter2);

        //OnSelected
        spnAriza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FilterModel filterModel = (FilterModel) adapterView.getItemAtPosition(i);
                //Seçilen

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spnOncelik.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FilterModel filterModel = (FilterModel) adapterView.getItemAtPosition(i);
                //Seçilen

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void initDialog() {
        AnyDialog anyDialog = new AnyDialog(this);
        dialog = anyDialog.loading(loading);
    }

    private void getSpnOncelik() {

        spnOncelik.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // request.setInv_TypeCode(new Code_Id(i + 1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void focusing() {
        spnAsansor.setOnFocusChangeListener((view, b) -> {
            if (b) spnAsansor.showDropDown();
            else spnAsansor.dismissDropDown();
        });
        spinner_musteri.setOnFocusChangeListener((view, b) -> {
            if (b) spinner_musteri.showDropDown();
            else spinner_musteri.dismissDropDown();
        });
        spnAsansor.setOnClickListener(view -> {
            spnAsansor.showDropDown();
        });
        spinner_musteri.setOnClickListener(view -> {
            spinner_musteri.showDropDown();
        });
    }

    private void subOrNew() {
        if (CreateSubServAppSingleton.getInstance().getServAppGetById() != null) {
            servAppGetById = CreateSubServAppSingleton.getInstance().getServAppGetById();
            fillAll(servAppGetById);

        } else {
            request.setInv_TypeCode(new Code_Id(2));
            request.setInv_RelatedServiceAppointmentId(new Inv_Id_Id());
            getAccountAll();
            edtSupervisor.setText(SingletonUser.getInstance().getUser().getSuperVisorId().getText());
            lnrOnceki.setVisibility(View.GONE);
            edtIsimsoyad.setText(SingletonUser.getInstance().getUser().getUserName());
            fillServAppTypeAndPrioritySpinner();
        }
    }

    private void getElevatorByCustomerAll(String id) {
        progElevator.setVisibility(View.VISIBLE);
        Log.d(TAG, "getElevatorByCustomerAll: " + id);
        CustomerIdRequest request = new CustomerIdRequest(id);
        Call<ElevatorListAll> call = jsonApi.elevatorGetByCustomerId(request);
        APIHelper.enqueueWithRetry(call, new Callback<ElevatorListAll>() {
            @Override
            public void onResponse(Call<ElevatorListAll> call, Response<ElevatorListAll> response) {
                if (response.isSuccessful()) {

                    if (response.body().isSuccess()) {
                        ElevatorListAll listAll = response.body();
                        fillElevatorSpinner(listAll.getElevators());

                    } else {
                        DialogCreater.errorDialog(CreateServAppActivity.this, response.body().getErrorMsg());
                    }

                } else {
                    DialogCreater.errorDialog(CreateServAppActivity.this, try_again);
                }
                progElevator.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ElevatorListAll> call, Throwable t) {
                progElevator.setVisibility(View.GONE);
                DialogCreater.errorDialog(CreateServAppActivity.this, try_again);
            }
        });
    }

    private void fillAll(ServAppGetById model) {
        ServiceAppointment item = model.getServiceAppointment();
        Account account = new Account();
        if (item.getInv_CustomerId() != null) {
            account.setAccountId(item.getInv_CustomerId().getId());
            account.setName(item.getInv_CustomerId().getText());
            fillSpinnerOneItem(account);
        }

        edtKonu.setText(item.getSubject());
        fillElevatorSpinner(item.getInv_ElevatorId().getId(), item.getInv_ElevatorId().getText());
        if (item.getInv_TypeCode() != null) {

            List<FilterModel> servApptype = new ArrayList<>();

            servApptype.add(new FilterModel(item.getInv_TypeCode().getValue(),
                    item.getInv_TypeCode().getText()));

            ArrayAdapter<FilterModel> spinnerArrayAdapter = new ArrayAdapter<>(
                    this, R.layout.spinner_item_white, servApptype);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnAriza.setAdapter(spinnerArrayAdapter);

        }
        if (item.getPriortiyCode() != null) {
            List<FilterModel> priorty = new ArrayList<>();

            priorty.add(new FilterModel(item.getPriortiyCode().getValue(),
                    item.getPriortiyCode().getText()));

            ArrayAdapter<FilterModel> spinnerArrayAdapter = new ArrayAdapter<>(
                    this, R.layout.spinner_item_white, priorty);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnOncelik.setAdapter(spinnerArrayAdapter);
        }
        edtIsimsoyad.setText(item.getOwnerId().getText());
        edtIsimsoyad.setEnabled(false);
        edtAciklama.setText(item.getInv_Description());
        edtOnceki.setText(item.getActivityId());
        edtOnceki.setEnabled(false);
        if (model.getServiceAppointment().getInv_Supervisorid() != null)
            edtSupervisor.setText(model.getServiceAppointment().getInv_Supervisorid().getText());
        edtSaat.setText(model.getServiceAppointment().getScheduledStart());
        lnrOnceki.setVisibility(View.VISIBLE);
        request.setInv_RelatedServiceAppointmentId(new Inv_Id_Id(item.getActivityId()));
        spinner_musteri.setEnabled(false);
        spnAsansor.setEnabled(false);

    }

    private void getAccountAll() {
        if (ShareData.getInstance().getAccountListAll() != null && ShareData.getInstance().getAccountListAll().getAccounts().size() > 0) {
            fillSpinner(ShareData.getInstance().getAccountListAll().getAccounts());
        } else {
            progAcoount.setVisibility(View.VISIBLE);
            Call<AccountListAll> call = jsonApi.geAccountListAllCall();
            APIHelper.enqueueWithRetry(call, new Callback<AccountListAll>() {
                @Override
                public void onResponse(Call<AccountListAll> call, Response<AccountListAll> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess()) {
                            AccountListAll listAll = response.body();
                            fillSpinner(listAll.getAccounts());
                            ShareData.getInstance().setAccountListAll(listAll);
                        } else {
                            DialogCreater.errorDialog(CreateServAppActivity.this, response.body().getErrorMsg());
                        }

                    } else {
                        DialogCreater.errorDialog(CreateServAppActivity.this, try_again);
                    }
                    progAcoount.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<AccountListAll> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                    progAcoount.setVisibility(View.GONE);
                    DialogCreater.errorDialog(CreateServAppActivity.this, try_again);

                }
            });
        }

    }

    private void init() {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        request = new serviceAppointment();

    }

    private void initDateAndTime() {
        dateTimeFragment = (SwitchDateTimeDialogFragment) getSupportFragmentManager().findFragmentByTag(TAG_DATETIME_FRAGMENT);
        if (dateTimeFragment == null) {
            dateTimeFragment = SwitchDateTimeDialogFragment.newInstance(
                    getString(R.string.label_datetime_dialog),
                    getString(android.R.string.ok),
                    getString(android.R.string.cancel)
                    //getString(R.string.clean) // Optional
            );
        }
    }

    private void fillSpinner(List<Account> list) {
        if (list != null && list.size() > 0) {
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
                    final Account account = (Account) list.get(i);
                    getElevatorByCustomerAll(account.getAccountId());
                    request.setInv_CustomerId(new Inv_Id_Id(account.getAccountId()));
                    Methodes.hideKeyboard(CreateServAppActivity.this);
                    edtKonum.setText(StringUtil.convertIntToString(account.getInv_LocationSequence()));

                }
            });
            spinner_musteri.setListSelection(0);
        } else {
            DialogCreater.warningDialog(CreateServAppActivity.this, "Liste Boş");
            spinner_musteri.setAdapter(null);
            spinner_musteri.setOnClickListener(null);
        }

    }

    private void fillSpinnerOneItem(Account account) {
        spinner_musteri.setText(account.getName());
        // spinner_musteri.setEnabled(false);
        request.setInv_CustomerId(new Inv_Id_Id(account.getAccountId()));

    }

    private void fillElevatorSpinner(List<ElevatorsCustomer> list) {
        if (list != null && list.size() > 0) {
            ArrayAdapter<ElevatorsCustomer> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,
                    list);
            spnAsansor.setAdapter(spinnerArrayAdapter);
            spnAsansor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    request.setInv_ElevatorId(new Inv_Id_Id(list.get(i).getInv_ElevatorId()));
                    Methodes.hideKeyboard(CreateServAppActivity.this);
                }
            });
            spnAsansor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    spnAsansor.showDropDown();
                }
            });
            spnAsansor.setListSelection(0);
        } else {
            DialogCreater.warningDialog(CreateServAppActivity.this, no_elevator);
            spnAsansor.setAdapter(null);
            spnAsansor.setOnClickListener(null);
        }

    }

    private void fillElevatorSpinner(String id, String name) {
        // spnAsansor.setEnabled(false);
        spnAsansor.setText(name);
        request.setInv_ElevatorId(new Inv_Id_Id(id));

    }

    private void createNew() {

        request.setSubject(edtKonu.getText().toString());
        request.setScheduledStart(edtSaat.getText().toString());
        //  request.setInv_RelatedServiceAppointmentId(new Inv_Id_Id());
        //check everything
        if (checkFields()) {
            final UpsertByIdCreateRequest createRequest = new UpsertByIdCreateRequest();
            createRequest.setServiceAppointment(request);
            createRequest.setUserId(SingletonUser.getInstance().getUser().getUserId());
            dialog.show();
            Call<DefaultResponse2> call = jsonApi.createServapp(createRequest);
            APIHelper.enqueueWithRetry(call, new Callback<DefaultResponse2>() {
                @Override
                public void onResponse(Call<DefaultResponse2> call, Response<DefaultResponse2> response) {
                    dialog.dismissWithAnimation();
                    if (response.isSuccessful()) {

                        if (response.body().isSucces()) {
                            new SweetAlertDialog(CreateServAppActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText(succes)
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            onBackPressed();
                                        }
                                    })
                                    .show();
                        } else {
                            DialogCreater.warningDialog(CreateServAppActivity.this,
                                    response.body().getErrorMsg());
                        }


                    } else {
                        DialogCreater.warningDialog(CreateServAppActivity.this, try_again);
                    }
                }

                @Override
                public void onFailure(Call<DefaultResponse2> call, Throwable t) {
                    dialog.dismissWithAnimation();
                    DialogCreater.warningDialog(CreateServAppActivity.this, try_again);
                }
            });
        } else {
            DialogCreater.warningDialog(CreateServAppActivity.this, blanks);
        }


    }

    private boolean checkFields() {
        if (request.getInv_CustomerId() == null) return false;
        else if (request.getInv_ElevatorId() == null) return false;
        else if (request.getInv_RelatedServiceAppointmentId() == null) return false;
        else if (TextUtils.isEmpty((request.getScheduledStart()))) return false;
        else if (TextUtils.isEmpty(request.getSubject())) return false;
        else if (request.getInv_TypeCode() == null) return false;
        else return true;
    }

    private void openDatePicker() {
        // Şimdiki zaman bilgilerini alıyoruz. güncel yıl, güncel ay, güncel gün.
        final Calendar takvim = Calendar.getInstance();
        int yil = takvim.get(Calendar.YEAR);
        int ay = takvim.get(Calendar.MONTH);
        int gun = takvim.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this,
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
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                        String currentDateandTime = sdf.format(Calendar.getInstance().getTime());

                        edtSaat.setText(dayOfMonth + "." + monthString + "." + year + " " + currentDateandTime);


                    }
                }, yil, ay, gun);
        dpd.getDatePicker().setMinDate(takvim.getTime().getTime());
        dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", dpd);
        dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", dpd);
        dpd.show();
    }

    private void openDateAndTime() {
        final SimpleDateFormat myDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm",
                java.util.Locale.getDefault());
        dateTimeFragment.setHighlightAMPMSelection(false);
        dateTimeFragment.setMinimumDateTime(Calendar.getInstance().getTime());
        dateTimeFragment.setDefaultDateTime(Calendar.getInstance().getTime());
        dateTimeFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Date date) {
                edtSaat.setText(myDateFormat.format(date));
            }

            @Override
            public void onNegativeButtonClick(Date date) {

            }
        });
        dateTimeFragment.startAtCalendarView();
        dateTimeFragment.show(getSupportFragmentManager(), TAG_DATETIME_FRAGMENT);
    }

    @OnClick({R.id.img_menu, R.id.btn_save, R.id.edt_saat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_menu:
                onBackPressed();
                break;
            case R.id.btn_save:
                createNew();
                break;
            case R.id.edt_saat:
                //openDatePicker();
                openDateAndTime();
                break;
        }
    }
}
