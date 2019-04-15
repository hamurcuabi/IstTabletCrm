package com.emrhmrc.isttabletcrm.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvChangingPartAdapter;
import com.emrhmrc.isttabletcrm.adapter.RcvElevatorRequestAdapter;
import com.emrhmrc.isttabletcrm.api.APIHelper;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.models.CommonClass.FilterModel;
import com.emrhmrc.isttabletcrm.models.Elevator.Elevator;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorChangingPart;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorGetById;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorIdRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.ServappGetByElevatorId;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppointmentElevator;
import com.emrhmrc.isttabletcrm.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ElevatorDetailActivity extends AppCompatActivity implements OnItemClickListener {

    private static final String TAG = "ElevatorDetailActivity";
    @BindView(R.id.txt_elevator_name)
    TextView txtElevatorName;
    @BindView(R.id.txt_asansorname)
    TextView txtAsansorname;
    @BindView(R.id.txt_apartadi)
    TextView txtApartadi;
    @BindView(R.id.txt_blokadi)
    TextView txtBlokadi;
    @BindView(R.id.txt_panaromikmi)
    TextView txtPanaromikmi;
    @BindView(R.id.txt_tekniktakim)
    TextView txtTekniktakim;
    @BindView(R.id.txt_etiketrengi)
    TextView txtEtiketrengi;
    @BindView(R.id.txt_sozlemebitis)
    TextView txtSozlemebitis;
    @BindView(R.id.txt_asansormanike)
    TextView txtAsansormanike;
    @BindView(R.id.txt_anlikdurum)
    TextView txtAnlikdurum;
    @BindView(R.id.txt_hizmetyili)
    TextView txtHizmetyili;
    @BindView(R.id.txt_adsoyad)
    TextView txtAdsoyad;
    @BindView(R.id.txt_arizadakisorun)
    TextView txtArizadakisorun;
    @BindView(R.id.txt_yaptiranfirma)
    TextView txtYaptiranfirma;
    @BindView(R.id.txt_ozellikler)
    TextView txtOzellikler;
    @BindView(R.id.txt_kimlikno)
    TextView txtKimlikno;
    @BindView(R.id.txt_kapasite)
    TextView txtKapasite;
    @BindView(R.id.txt_katsayisi)
    TextView txtKatsayisi;
    @BindView(R.id.txt_sinif)
    TextView txtSinif;
    @BindView(R.id.txt_serino)
    TextView txtSerino;
    @BindView(R.id.txt_duraksayisi)
    TextView txtDuraksayisi;
    @BindView(R.id.txt_asansorhizi)
    TextView txtAsansorhizi;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindDrawable(R.drawable.btn_malzeme_2)
    Drawable first;
    @BindDrawable(R.drawable.btn_taleplerim)
    Drawable second;
    @BindView(R.id.btn_wish)
    Button btnWish;
    @BindView(R.id.lnr1)
    LinearLayout lnr1;
    @BindView(R.id.lnr2)
    LinearLayout lnr2;
    @BindView(R.id.rcv_talep)
    RecyclerView rcvTalep;
    @BindView(R.id.spn_statu)
    Spinner spnStatu;
    @BindView(R.id.btn_changeproductlist)
    Button btnChangeproductlist;
    private JsonApi jsonApi;
    private RcvChangingPartAdapter adapter;
    private RcvElevatorRequestAdapter adapter_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elevator_detail);
        ButterKnife.bind(this);
        init();
        getElevatorById();
        getChangingPart();
        getMyRequest();
    }

    private void getChangingPart() {
        Call<ElevatorChangingPart> call = jsonApi.getElevatorChangingPartCall(new ElevatorIdRequest(ShareData
                .getInstance().getElevatorId()));
        call.enqueue(new Callback<ElevatorChangingPart>() {
            @Override
            public void onResponse(Call<ElevatorChangingPart> call, Response<ElevatorChangingPart> response) {
                if (response.isSuccessful()) {
                    final ElevatorChangingPart model = response.body();
                    adapter.setItems(model.getElevatorChangingParts());
                } else Log.d(TAG, "onResponse: " + response.message());
            }

            @Override
            public void onFailure(Call<ElevatorChangingPart> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void getMyRequest() {
        Call<ServappGetByElevatorId> call = jsonApi.getServappGetByElevatorIdCall(new ElevatorIdRequest(ShareData
                .getInstance().getElevatorId()));
        APIHelper.enqueueWithRetry(call, new Callback<ServappGetByElevatorId>() {
            @Override
            public void onResponse(Call<ServappGetByElevatorId> call, Response<ServappGetByElevatorId> response) {

                if (response.isSuccessful()) {
                    final ServappGetByElevatorId model = response.body();
                    adapter_request.setItems(model.getServiceAppointment());
                    setupFilters(model.getServiceAppointment());
                }

            }

            @Override
            public void onFailure(Call<ServappGetByElevatorId> call, Throwable t) {

            }
        });
    }

    private void init() {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        adapter = new RcvChangingPartAdapter(this, this::onItemClicked);
        adapter_request = new RcvElevatorRequestAdapter(this, this::onItemClicked);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(adapter);
        rcvTalep.setLayoutManager(new LinearLayoutManager(this));
        rcvTalep.setAdapter(adapter_request);

    }

    private void setTexts(Elevator model) {
        txtAsansorname.setText(StringUtil.nullToString(model.getInv_ElevatorNumber()));
        txtApartadi.setText(StringUtil.nullToString(model.getInv_AccountId().getText()));
        txtBlokadi.setText(StringUtil.nullToString(model.getInv_BlockName()));
        txtEtiketrengi.setText(StringUtil.nullToString(model.getInv_ColorCode().getText()));
        txtSozlemebitis.setText(StringUtil.nullToString(model.getInv_ContractEndDate()));
        txtAnlikdurum.setText(StringUtil.nullToString(model.getInv_WorkingStCode().getText()));
        txtHizmetyili.setText(StringUtil.nullToString(model.getInv_StartDate()));
        txtYaptiranfirma.setText(StringUtil.nullToString(model.getInv_BuilderFirmId().getText()));
        txtKapasite.setText(StringUtil.convertIntToString(model.getInv_Capacity()));
        txtSinif.setText(StringUtil.nullToString(model.getInv_ClassId().getText()));
        txtAsansorhizi.setText(StringUtil.convertIntToString(model.getInv_Speed()));
        txtAsansormanike.setText(StringUtil.nullToString(model.getInv_Location()));
        txtKatsayisi.setText(StringUtil.convertIntToString(model.getInv_Floor()));
        txtSerino.setText(StringUtil.nullToString(model.getInv_SerialNumber()));
        txtKimlikno.setText(StringUtil.nullToString(model.getInv_ElevatorNumber()));


    }

    public void getElevatorById() {
        Log.d(TAG, "getElevatorById: " + ShareData
                .getInstance().getElevatorId());
        Call<ElevatorGetById> call = jsonApi.elevatorGetById(new ElevatorIdRequest(ShareData
                .getInstance().getElevatorId()));
        call.enqueue(new Callback<ElevatorGetById>() {
            @Override
            public void onResponse(Call<ElevatorGetById> call, Response<ElevatorGetById> response) {
                if (response.isSuccessful()) {

                    ElevatorGetById model = response.body();
                    setTexts(model.getElevator());

                }
            }

            @Override
            public void onFailure(Call<ElevatorGetById> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    private void rcvFirst() {

        btnChangeproductlist.setBackground(first);
        rcv.setVisibility(View.VISIBLE);
        lnr1.setVisibility(View.VISIBLE);

        btnWish.setBackground(second);
        lnr2.setVisibility(View.GONE);
        spnStatu.setVisibility(View.GONE);
        rcvTalep.setVisibility(View.GONE);


    }

    private void rcvSecond() {

        btnChangeproductlist.setBackground(second);
        rcv.setVisibility(View.GONE);
        lnr1.setVisibility(View.GONE);

        btnWish.setBackground(first);
        lnr2.setVisibility(View.VISIBLE);
        spnStatu.setVisibility(View.VISIBLE);
        rcvTalep.setVisibility(View.VISIBLE);

    }

    private void setupFilters(List<ServiceAppointmentElevator> model) {

        HashMap<Integer, String> statuTypeHash = new HashMap<>();
        List<FilterModel> statuType = new ArrayList<>();

        for (ServiceAppointmentElevator current : model
        ) {


            if (current.getStatusCode() != null) {

                if (!statuTypeHash.containsKey(current.getStatusCode().getValue())) {
                    statuType.add(new FilterModel(current.getStatusCode().getValue(),
                            current.getStatusCode().getText()));
                    statuTypeHash.put(current.getStatusCode().getValue(), "");
                }
            }
        }

        statuType.add(0, new FilterModel(-1, "Statü"));

        //Spn Statu

        ArrayAdapter<FilterModel> spinnerArrayAdapter2 = new ArrayAdapter<>(
                this, R.layout.spinner_item_white, statuType);
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnStatu.setAdapter(spinnerArrayAdapter2);


        spnStatu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FilterModel filterModel = (FilterModel) adapterView.getItemAtPosition(i);
                adapter_request.getFilter().filter("" + filterModel.getValue());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onItemClicked(Object item, int positon) {

    }

    @OnClick({R.id.btn_changeproductlist, R.id.btn_wish, R.id.img_menu, R.id.txt_menu_header})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_changeproductlist:
                rcvFirst();
                break;
            case R.id.btn_wish:
                rcvSecond();
                break;
            case R.id.img_menu:
                onBackPressed();
                break;
            case R.id.txt_menu_header:
                onBackPressed();
                break;
        }
    }
}
