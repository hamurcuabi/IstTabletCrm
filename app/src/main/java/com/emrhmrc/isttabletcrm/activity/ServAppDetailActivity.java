package com.emrhmrc.isttabletcrm.activity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvServAppDetailAdapter;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.fragment.BeforeAfterPicFragment;
import com.emrhmrc.isttabletcrm.fragment.ControlListFragment;
import com.emrhmrc.isttabletcrm.fragment.MapFragment;
import com.emrhmrc.isttabletcrm.fragment.ReasonOfBreakdownFragment;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.models.MapModel;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetById;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppIdRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppointment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServAppDetailActivity extends AppCompatActivity implements OnItemClickListener, View.OnClickListener {

    private static final String TAG = "ServAppDetailActivity";
    private JsonApi jsonApi;
    private RcvServAppDetailAdapter adapter;
    private RecyclerView rcv;
    private TextView txt_firmaismi, txt_firma_descp, txt_blokadi, txt_asansorno, txt_isemritipi,
            txt_oncelik, txt_ustaadi, txt_randevu, txt_ilgilisupervisor, txt_isebaslama,
            txt_ariza_nedeni, txt_arizakodu, txt_aciklamanot;
    private Button btn_beforeafter;
    private ShareData shareData;
    private ImageView img_gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serv_app_detail);
        init();
        getServAppById(shareData.getServAppId());
    }

    private void mapFragment() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x - 100;
        int height = size.y - 200;
        MapModel map = new MapModel(ShareData.getInstance().getLatitude(), ShareData
                .getInstance().getLongitude(),
                "Title",
                "Description");
        MapFragment fragment = MapFragment.newInstance(map, width, height);
        fragment.show(getSupportFragmentManager(), "MpaFragment");
    }

    private void init() {
        rcv = findViewById(R.id.rcv);
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        txt_firmaismi = findViewById(R.id.txt_firmaismi);
        txt_firma_descp = findViewById(R.id.txt_firma_descp);
        txt_blokadi = findViewById(R.id.txt_blokadi);
        txt_asansorno = findViewById(R.id.txt_asansorno);
        txt_isemritipi = findViewById(R.id.txt_isemritipi);
        txt_oncelik = findViewById(R.id.txt_oncelik);
        txt_ustaadi = findViewById(R.id.txt_ustaadi);
        txt_randevu = findViewById(R.id.txt_randevu);
        txt_ilgilisupervisor = findViewById(R.id.txt_ilgilisupervisor);
        txt_isebaslama = findViewById(R.id.txt_isebaslama);
        txt_ariza_nedeni = findViewById(R.id.txt_ariza_nedeni);
        txt_arizakodu = findViewById(R.id.txt_arizakodu);
        txt_aciklamanot = findViewById(R.id.txt_aciklamanot);
        btn_beforeafter = findViewById(R.id.btn_beforeafter);
        img_gps = findViewById(R.id.img_gps);
        btn_beforeafter.setOnClickListener(this);
        txt_arizakodu.setOnClickListener(this);
        img_gps.setOnClickListener(this);

        adapter = new RcvServAppDetailAdapter(getApplicationContext(), this);
        adapter.setListener(this);
        shareData = ShareData.getInstance();
    }

    public void newServApp(View view) {
        startActivity(new Intent(ServAppDetailActivity.this, CreateServAppActivity.class));
    }

    public void openControlList(View view) {

        ControlListFragment fragment = ControlListFragment.newInstance();
        fragment.setCancelable(false);
        fragment.show(getSupportFragmentManager(), "KontrolListesi");

    }

    private void getServAppById(String id) {
        Call<ServAppGetById> call = jsonApi.servAppGetById(new ServAppIdRequest(id));
        call.enqueue(new Callback<ServAppGetById>() {
            @Override
            public void onResponse(Call<ServAppGetById> call, Response<ServAppGetById> response) {
                if (response.isSuccessful()) {
                    ServAppGetById model = response.body();
                    setTexts(model.getServiceAppointment());
                    ShareData.getInstance().setLongitude(model.getServiceAppointment().getInv_Longitude());
                    ShareData.getInstance().setLatitude(model.getServiceAppointment().getInv_Latitude());
                    adapter.setItems(model.getServiceAppointment().getServAppGetByIdServAppDetails());
                }
            }

            @Override
            public void onFailure(Call<ServAppGetById> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });


    }

    private void setTexts(ServiceAppointment model) {
        // txt_firmaismi.setText();
        // txt_firma_descp.setText();
        //txt_blokadi.setText();
        if (model.getInv_ElevatorId() != null)
            txt_asansorno.setText(model.getInv_ElevatorId().getText());
        if (model.getInv_TypeCode() != null)
            txt_isemritipi.setText(model.getInv_TypeCode().getText());
        if (model.getPriortiyCode() != null)
            txt_oncelik.setText(model.getPriortiyCode().getText());
        // txt_ustaadi.setText();
        //  txt_randevu.setText();
        //  txt_ilgilisupervisor.setText();
        // txt_isebaslama.setText();
        //  txt_ariza_nedeni.setText();
        //  txt_arizakodu.setText();
        // txt_aciklamanot.setText();

    }

    @Override
    public void onItemClicked(Object item) {
        //Cilcked

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_beforeafter:
                openBeforeAfter();
                break;
            case R.id.txt_arizakodu:
                openReasonOfBreakdown();

                break;
            case R.id.img_gps:
                mapFragment();
                break;


        }

    }

    private void openBeforeAfter() {
        BeforeAfterPicFragment fragment = BeforeAfterPicFragment.newInstance();
        fragment.show(getSupportFragmentManager(), "beforeafter");
    }

    private void openReasonOfBreakdown() {
        ReasonOfBreakdownFragment fragment = ReasonOfBreakdownFragment.newInstance();
        fragment.show(getSupportFragmentManager(), "reasonbrekadown");
    }


}
