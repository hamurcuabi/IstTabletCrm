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
import com.emrhmrc.isttabletcrm.bindingModel.ServiceAppointments;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.models.Notification.Notification;
import com.emrhmrc.isttabletcrm.models.Notification.NotificationListAll;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppListAll;
import com.emrhmrc.isttabletcrm.models.User.UserIdRequest;
import com.emrhmrc.isttabletcrm.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

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
        Call<NotificationListAll> call = jsonApi.getNotificationListAll(userIdRequest);
        call.enqueue(new Callback<NotificationListAll>() {
            @Override
            public void onResponse(Call<NotificationListAll> call, Response<NotificationListAll> response) {
                if (response.isSuccessful()) {

                    NotificationListAll temp = response.body();
                    setNotifNotifCount(temp.getNotifications());
                }
            }

            @Override
            public void onFailure(Call<NotificationListAll> call, Throwable t) {

            }
        });
    }

    private void setNotifNotifCount(List<Notification> notifications) {
        txtNotifNotif.setText(StringUtil.convertIntToString(notifications.size()));
    }

    public void getServAppListAll() {
        UserIdRequest userIdRequest = new UserIdRequest(SingletonUser.getInstance().getUser().getUserId());
        Call<ServAppListAll> call = jsonApi.servAppListAll(userIdRequest);
        call.enqueue(new Callback<ServAppListAll>() {
            @Override
            public void onResponse(Call<ServAppListAll> call, Response<ServAppListAll> response) {
                if (response.isSuccessful()) {

                    final ServAppListAll temp = response.body();
                    setServappNotifCount(temp.getServiceAppointments());


                }
            }

            @Override
            public void onFailure(Call<ServAppListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    private void setServappNotifCount(List<ServiceAppointments> serviceAppointments) {

        List<ServiceAppointments> model = new ArrayList<>();
        for (ServiceAppointments current : serviceAppointments
        ) {
            if (current.getStatusCode().getValue() != 8 && current.getStatusCode().getValue() != 9)
                model.add(current);
        }
        txtServapcountNotif.setText(StringUtil.convertIntToString(model.size()));
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

    @OnClick({R.id.cons_isemirleri, R.id.cons_duyurular, R.id.cons_takvim, R.id.cons_teknik, R.id.img_uygunsuzluk, R.id.cons_uygunsuzluk})
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
               // goUnstability();
                break;
            case R.id.cons_uygunsuzluk:
               // goUnstability();
                break;
        }
    }

}
