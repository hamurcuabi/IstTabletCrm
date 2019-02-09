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
import com.emrhmrc.isttabletcrm.helper.Methodes;
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
            txt_ariza_nedeni, txt_arizakodu, txt_aciklamanot, txt_islem, add_job;
    private Button btn_beforeafter;
    private ShareData shareData;
    private ImageView img_gps, img_tip;

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
        txt_firmaismi = findViewById(R.id.txt_firmaismi);
        txt_firma_descp = findViewById(R.id.txt_firma_descp);
        txt_islem = findViewById(R.id.txt_islem);
        img_gps = findViewById(R.id.img_gps);
        img_tip = findViewById(R.id.img_tip);
        add_job = findViewById(R.id.add_job);
        btn_beforeafter.setOnClickListener(this);
        add_job.setOnClickListener(this);
        txt_arizakodu.setOnClickListener(this);
        img_gps.setOnClickListener(this);
        txt_asansorno.setOnClickListener(this);

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
        ShareData.getInstance().setElevatorId(model.getInv_ElevatorId().getId());
        txt_asansorno.setText(model.getInv_ElevatorId().getText());
        if (model.getInv_TypeCode() != null) {
            txt_isemritipi.setText(model.getInv_TypeCode().getText());
            switch (model.getInv_TypeCode().getValue()) {
                case 1:
                    img_tip.setImageResource(R.drawable.ic_bakim);
                    break;
                case 2:
                    img_tip.setImageResource(R.drawable.ic_ariza);
                    break;
                case 3:
                    img_tip.setImageResource(R.drawable.ic_modern);
                    break;
                case 4:
                    img_tip.setImageResource(R.drawable.ic_yedek);
                    break;
                case 5:
                    //Belirlenmedi
                    img_tip.setImageResource(R.drawable.ic_yedek);
                    break;
                case 7:
                    //Belirlenmedi
                    img_tip.setImageResource(R.drawable.ic_yedek);
                    break;
                default:
                    img_tip.setImageResource(R.drawable.ic_bakim);
                    break;
            }
        }

        if (model.getPriortiyCode() != null) {
            int img;
            txt_oncelik.setText(model.getPriortiyCode().getText());
            switch (model.getPriortiyCode().getValue()) {
                case 0:
                    img = R.drawable.ic_az;
                    break;
                case 1:
                    img = R.drawable.ic_orta;
                    break;
                case 2:
                    img = R.drawable.ic_yuksek;
                    break;
                default:
                    img = R.drawable.ic_az;
                    break;
            }
            txt_oncelik.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(img), null);
        }

        txt_firmaismi.setText(model.getInv_CustomerId().getText());
        txt_firma_descp.setText(model.getSubject());
        txt_islem.setText(model.getStatusCode().getText());
        txt_randevu.setText(Methodes.changeDateFormatToText(model.getScheduledStart()));
        txt_isebaslama.setText(Methodes.changeDateFormatToText(model.getScheduledEnd()));

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
            case R.id.txt_asansorno:
                openElevatorDetail();
                break;
            case R.id.add_job:
                openAddPiece();
                break;


        }

    }

    private void openAddPiece() {
        startActivity(new Intent(ServAppDetailActivity.this, AddPieceActivity.class));
    }

    private void openElevatorDetail() {
        startActivity(new Intent(ServAppDetailActivity.this, ElevatorDetailActivity.class));
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
