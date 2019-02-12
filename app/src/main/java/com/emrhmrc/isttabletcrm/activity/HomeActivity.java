package com.emrhmrc.isttabletcrm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.emrhmrc.isttabletcrm.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

    }


    private void goCalendar() {
        startActivity(new Intent(HomeActivity.this, CalendarActivity.class));
    }

    private void goAnnouncement() {
        startActivity(new Intent(HomeActivity.this, AnnouncementActivity.class));
    }

    private void goTechnical() {
        startActivity(new Intent(HomeActivity.this, TechnicalDocumentActivity.class));
    }

    private void goServApp() {
        startActivity(new Intent(HomeActivity.this, ServAppActivty.class));
    }

    @OnClick({R.id.cons_isemirleri, R.id.cons_duyurular, R.id.cons_takvim, R.id.cons_teknik})
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
        }
    }
}
