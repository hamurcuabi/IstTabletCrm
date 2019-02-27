package com.emrhmrc.isttabletcrm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.models.CommonClass.NotificationCountResponse;
import com.emrhmrc.isttabletcrm.models.CommonClass.ServAppCountResponse;
import com.emrhmrc.isttabletcrm.models.User.UserIdRequest;
import com.emrhmrc.isttabletcrm.util.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    @BindView(R.id.txt_user_name)
    TextView txtUserName;
    @BindView(R.id.txt_user_descp)
    TextView txtUserDescp;
    @BindView(R.id.txt_servapcount_notif)
    TextView txtServapcountNotif;
    @BindView(R.id.txt_notif_notif)
    TextView txtNotifNotif;
    private JsonApi jsonApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        setTexts();
        setNotifCount();

    }

    private void setNotifCount() {
        getServAppListAll();
        getNotificationsAll();

    }

    private void getNotificationsAll() {
        UserIdRequest userIdRequest = new UserIdRequest(SingletonUser.getInstance().getUser().getUserId());
        Call<NotificationCountResponse> call = jsonApi.getNotifCount(userIdRequest);
        call.enqueue(new Callback<NotificationCountResponse>() {
            @Override
            public void onResponse(Call<NotificationCountResponse> call, Response<NotificationCountResponse> response) {
                if (response.isSuccessful()) {
                    final NotificationCountResponse countResponse = response.body();
                    setNotifNotifCount(countResponse.getNotificationCount());
                }
            }

            @Override
            public void onFailure(Call<NotificationCountResponse> call, Throwable t) {

            }
        });

    }

    private void setNotifNotifCount(int count) {
        txtNotifNotif.setText(StringUtil.convertIntToString(count));
    }

    public void getServAppListAll() {
        UserIdRequest userIdRequest = new UserIdRequest(SingletonUser.getInstance().getUser().getUserId());
        Call<ServAppCountResponse> call = jsonApi.getServappCount(userIdRequest);
        call.enqueue(new Callback<ServAppCountResponse>() {
            @Override
            public void onResponse(Call<ServAppCountResponse> call, Response<ServAppCountResponse> response) {
                if (response.isSuccessful()) {
                    final ServAppCountResponse countResponse = response.body();
                    setServappNotifCount(countResponse.getServAppCount());
                }
            }

            @Override
            public void onFailure(Call<ServAppCountResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });


    }

    private void setServappNotifCount(int count) {

        txtServapcountNotif.setText(StringUtil.convertIntToString(count));
    }

    private void setTexts() {
        txtUserName.setText(SingletonUser.getInstance().getUser().getUserName());
        txtUserDescp.setText(SingletonUser.getInstance().getUser().getDescription());

    }

    private void goCalendar() {
        startActivity(new Intent(HomeActivity.this, CalendarActivity.class));
    }

    private void goAnnouncement() {
        startActivity(new Intent(HomeActivity.this, AnnouncementActivity.class));
    }

    private void goUnstability() {
        startActivity(new Intent(HomeActivity.this, UnsuitabilityActivity.class));
    }

    private void goTechnical() {
        startActivity(new Intent(HomeActivity.this, TechnicalDocumentActivity.class));
    }

    private void goServApp() {
        startActivity(new Intent(HomeActivity.this, ServAppActivty.class));
    }

    @OnClick({R.id.cons_isemirleri, R.id.cons_duyurular, R.id.cons_takvim, R.id.cons_teknik,
            R.id.img_uygunsuzluk, R.id.cons_uygunsuzluk, R.id.cons_bilgilendirme, R.id.img_bilgilendirme})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cons_isemirleri:
                goServApp();
                break;
            case R.id.cons_duyurular:
                goAnnouncement();
                break;
            case R.id.cons_takvim:
                goCalendar();
                break;
            case R.id.cons_teknik:
                goTechnical();
                break;
            case R.id.img_uygunsuzluk:
                goUnstability();
                break;
            case R.id.cons_uygunsuzluk:
                goUnstability();
                break;
            case R.id.cons_bilgilendirme:
                goWareHouse();
                break;
            case R.id.img_bilgilendirme:
                goWareHouse();
                break;
        }
    }

    private void goWareHouse() {
        startActivity(new Intent(HomeActivity.this, WareHouseActivity.class));
    }

}
