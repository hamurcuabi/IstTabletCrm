package com.emrhmrc.isttabletcrm.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvServAppDetailAdapter;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.bindingModel.ServiceAppointment;
import com.emrhmrc.isttabletcrm.databinding.ActivityServAppDetailBinding;
import com.emrhmrc.isttabletcrm.fragment.BeforeAfterPicFragment;
import com.emrhmrc.isttabletcrm.fragment.ControlListFragment;
import com.emrhmrc.isttabletcrm.fragment.MapFragment;
import com.emrhmrc.isttabletcrm.fragment.ReasonOfBreakdownFragment;
import com.emrhmrc.isttabletcrm.helper.CreateSubServAppSingleton;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.helper.SingletonListUnsuitability;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.models.MapModel;
import com.emrhmrc.isttabletcrm.models.ServApp.CompleteByIdRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetById;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppDetails;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppIdRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServAppDetailActivity extends AppCompatActivity implements OnItemClickListener {

    private static final String TAG = "ServAppDetailActivity";
    ActivityServAppDetailBinding binding;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    private JsonApi jsonApi;
    private RcvServAppDetailAdapter adapter;
    private ShareData shareData;
    private List<ServAppGetByIdServAppDetails> model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_serv_app_detail);
        ButterKnife.bind(this);
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
        ArrayList<MapModel> mapModels = new ArrayList<>();
        mapModels.add(map);
        MapFragment fragment = MapFragment.newInstance(mapModels, width, height);
        fragment.show(getSupportFragmentManager(), "MpaFragment");
    }

    private void init() {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        adapter = new RcvServAppDetailAdapter(getApplicationContext(), this);
        adapter.setListener(this);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(adapter);
        shareData = ShareData.getInstance();
    }

    private void getServAppById(String id) {
        Call<ServAppGetById> call = jsonApi.servAppGetById(new ServAppIdRequest(id));
        call.enqueue(new Callback<ServAppGetById>() {
            @Override
            public void onResponse(Call<ServAppGetById> call, Response<ServAppGetById> response) {
                if (response.isSuccessful()) {
                    ServAppGetById model = response.body();
                    CreateSubServAppSingleton.getInstance().setServAppGetById(model);
                    setModelToBind(model.getServiceAppointment());
                    SingletonListUnsuitability.getInstance().setUnsuitabilities(model.getServiceAppointment().getServAppGetByIdServAppUnsuitabilities());
                    ShareData.getInstance().setLongitude(model.getServiceAppointment().getInv_Longitude());
                    ShareData.getInstance().setLatitude(model.getServiceAppointment().getInv_Latitude());
                    adapter.setItems(model.getServiceAppointment().getServAppGetByIdServAppDetails());
                }
            }

            @Override
            public void onFailure(Call<ServAppGetById> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                CreateSubServAppSingleton.getInstance().setServAppGetById(null);
            }
        });


    }

    private void setModelToBind(ServiceAppointment model) {
        binding.setModel(model);
        ShareData.getInstance().setElevatorId(model.getInv_ElevatorId().getId());
        adapter.setItems(model.getServAppGetByIdServAppDetails());

    }

    private void cancelServApp() {

        CompleteByIdRequest request = new CompleteByIdRequest();
        request.setServiceAppId(ShareData.getInstance().getServAppId());
        request.setUserId(SingletonUser.getInstance().getUser().getUserId());
        request.setCompleteType(false);


    }

    private void closeServApp() {

        CompleteByIdRequest request = new CompleteByIdRequest();
        request.setServiceAppId(ShareData.getInstance().getServAppId());
        request.setUserId(SingletonUser.getInstance().getUser().getUserId());
        request.setCompleteType(true);


    }

    @Override
    public void onItemClicked(Object item, int positon) {

    }


    public void newServApp() {

        startActivity(new Intent(ServAppDetailActivity.this, CreateServAppActivity.class));
    }

    public void openControlList() {

        ControlListFragment fragment = ControlListFragment.newInstance();
        fragment.setCancelable(false);
        fragment.show(getSupportFragmentManager(), "KontrolListesi");

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

    @OnClick({R.id.img_cancel, R.id.txt_cancel, R.id.btn_closejob, R.id.txt_yeni, R.id.img_yeni,
            R.id.img_add,R.id.txt_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_cancel:
                cancelServApp();
                break;
            case R.id.txt_cancel:
                cancelServApp();
                break;
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
            case R.id.btn_kontrol_listesi:
                openControlList();
                break;
            case R.id.txt_yeni:
                newServApp();
                break;
            case R.id.img_yeni:
                newServApp();
                break;
            case R.id.btn_closejob:
                closeServApp();
                break;
            case R.id.img_add:
                openAddPiece();
                break;
            case R.id.txt_add:
                openAddPiece();
                break;
        }
    }
}
