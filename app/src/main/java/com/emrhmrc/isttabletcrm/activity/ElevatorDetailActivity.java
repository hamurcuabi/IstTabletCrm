package com.emrhmrc.isttabletcrm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.models.Elevator.Elevator;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorGetById;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorIdRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ElevatorDetailActivity extends AppCompatActivity {

    private static final String TAG = "ElevatorDetailActivity";
    private TextView txt_elevator_name, txt_asansorname, txt_apartadi, txt_blokadi, txt_asansorhizi,
            txt_duraksayisi, txt_serino, txt_sinif, txt_katsayisi, txt_kapasite, txt_kimlikno,
            txt_ozellikler, txt_yaptiranfirma, txt_arizadakisorun, txt_adsoyad, txt_hizmetyili,
            txt_anlikdurum, txt_asansormanike, txt_sozlemebitis, txt_etiketrengi, txt_tekniktakim,
            txt_panaromikmi;
    private JsonApi jsonApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elevator_detail);
        init();
        getElevatorById();
    }

    private void init() {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        txt_elevator_name = findViewById(R.id.txt_elevator_name);
        txt_asansorname = findViewById(R.id.txt_asansorname);
        txt_apartadi = findViewById(R.id.txt_apartadi);
        txt_blokadi = findViewById(R.id.txt_blokadi);
        txt_asansorhizi = findViewById(R.id.txt_asansorhizi);
        txt_duraksayisi = findViewById(R.id.txt_duraksayisi);
        txt_serino = findViewById(R.id.txt_serino);
        txt_sinif = findViewById(R.id.txt_sinif);
        txt_katsayisi = findViewById(R.id.txt_katsayisi);
        txt_kapasite = findViewById(R.id.txt_kapasite);
        txt_kimlikno = findViewById(R.id.txt_kimlikno);
        txt_ozellikler = findViewById(R.id.txt_ozellikler);
        txt_yaptiranfirma = findViewById(R.id.txt_yaptiranfirma);
        txt_arizadakisorun = findViewById(R.id.txt_arizadakisorun);
        txt_adsoyad = findViewById(R.id.txt_adsoyad);
        txt_hizmetyili = findViewById(R.id.txt_hizmetyili);
        txt_anlikdurum = findViewById(R.id.txt_anlikdurum);
        txt_asansormanike = findViewById(R.id.txt_asansormanike);
        txt_sozlemebitis = findViewById(R.id.txt_sozlemebitis);
        txt_etiketrengi = findViewById(R.id.txt_etiketrengi);
        txt_tekniktakim = findViewById(R.id.txt_tekniktakim);
        txt_panaromikmi = findViewById(R.id.txt_panaromikmi);
    }

    private void setTexts(Elevator model) {


        txt_blokadi.setText(model.getInv_BlockName());
        if (model.getInv_Capacity() == null)
            txt_kapasite.setText("-");
        else txt_kapasite.setText(model.getInv_Capacity().toString());
        if (model.getInv_BuilderFirmId() == null)
            txt_yaptiranfirma.setText("-");
        else txt_yaptiranfirma.setText(model.getInv_BuilderFirmId().getText());
        if (model.getInv_ColorCode() == null)
            txt_etiketrengi.setText("-");
        else txt_etiketrengi.setText(model.getInv_ColorCode().getText());
        if (model.getInv_SerialNumber() == null)
            txt_serino.setText("-");
        else txt_serino.setText(model.getInv_SerialNumber());
        /*txt_asansorhizi.setText();
        txt_duraksayisi.setText();
        txt_sinif.setText();
        txt_katsayisi.setText();
        txt_kimlikno.setText();
        txt_ozellikler.setText();
        txt_arizadakisorun.setText();
        txt_adsoyad.setText();
        txt_hizmetyili.setText();
        txt_anlikdurum.setText();
        txt_asansormanike.setText();
        txt_sozlemebitis.setText();
        txt_tekniktakim.setText();
        txt_panaromikmi.setText();
        txt_elevator_name.setText();
        txt_asansorname.setText();
        txt_apartadi.setText();*/

    }

    public void getElevatorById() {

        Call<ElevatorGetById> call = jsonApi.elevatorGetById(new ElevatorIdRequest("e7b36067-d7ca-e811-8103-005056b66d80"));
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
