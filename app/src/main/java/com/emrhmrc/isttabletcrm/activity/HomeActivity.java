package com.emrhmrc.isttabletcrm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.emrhmrc.isttabletcrm.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "HomeActivity";
    private ConstraintLayout cons_isemirleri, cons_teknik, cons_takvim, cons_bilgilendirme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

    }

    private void init() {
        cons_isemirleri = findViewById(R.id.cons_isemirleri);
        cons_teknik = findViewById(R.id.cons_teknik);
        cons_takvim = findViewById(R.id.cons_takvim);
        cons_bilgilendirme = findViewById(R.id.cons_bilgilendirme);


        cons_isemirleri.setOnClickListener(this);
        cons_teknik.setOnClickListener(this);
        cons_takvim.setOnClickListener(this);
        cons_bilgilendirme.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cons_takvim:
                goCalendar();
                break;
            case R.id.cons_teknik:
                goTechnical();
                break;
            case R.id.cons_isemirleri:
                goServApp();
                break;
            case R.id.cons_bilgilendirme:
                goAnnouncement();
                break;

        }
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
}
