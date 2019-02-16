package com.emrhmrc.isttabletcrm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.models.Elevator.Elevator;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorGetById;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorIdRequest;
import com.emrhmrc.isttabletcrm.util.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ElevatorDetailActivity extends AppCompatActivity {

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


    private JsonApi jsonApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elevator_detail);
        ButterKnife.bind(this);
        init();
        getElevatorById();
    }

    private void init() {
        jsonApi = ApiClient.getClient().create(JsonApi.class);

    }

    private void setTexts(Elevator model) {

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
}
