package com.emrhmrc.isttabletcrm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvAnnouncementAdapter;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.models.Notification.Notification;
import com.emrhmrc.isttabletcrm.models.Notification.NotificationListAll;
import com.emrhmrc.isttabletcrm.models.User.UserIdRequest;
import com.emrhmrc.isttabletcrm.util.StringUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnnouncementActivity extends AppCompatActivity implements OnItemClickListener {

    private RecyclerView rcw;
    private List<Notification> model;
    private JsonApi jsonApi;
    private RcvAnnouncementAdapter adapter;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        init();
        getAnnouncementList();
    }

    private void init() {
        spinner = findViewById(R.id.spn_notif);
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        rcw = findViewById(R.id.rcv);
        rcw.setHasFixedSize(true);
        rcw.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RcvAnnouncementAdapter(getApplicationContext(), this);
        adapter.setListener(this);
        rcw.setAdapter(adapter);
    }

    private void getAnnouncementList() {
        UserIdRequest userIdRequest = new UserIdRequest(SingletonUser.getInstance().getUser().getUserId());
        Call<NotificationListAll> call = jsonApi.getNotificationListAll(userIdRequest);
        call.enqueue(new Callback<NotificationListAll>() {
            @Override
            public void onResponse(Call<NotificationListAll> call, Response<NotificationListAll> response) {
                if (response.isSuccessful()) {

                    final NotificationListAll temp = response.body();
                    model = temp.getNotifications();
                    adapter.setItems(model);
                    adapter.setItemsFilter(model);
                    spinnerListener();

                }
            }

            @Override
            public void onFailure(Call<NotificationListAll> call, Throwable t) {

            }
        });

    }

    @Override
    public void onItemClicked(Object item, int positon) {
        Notification current = (Notification) item;
        ShareData.getInstance().setNotificationIdRequest(((Notification) item).getActivityId());
        goDetail();
    }

    private void goDetail() {

        startActivity(new Intent(AnnouncementActivity.this, AnnouncementDetailActivity.class));
    }

    private void spinnerListener() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.getFilter().filter(StringUtil.convertIntToString(i));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
