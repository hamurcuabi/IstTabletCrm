package com.emrhmrc.isttabletcrm.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.emrhmrc.isttabletcrm.R;

public class ServAppDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serv_app_detail);
    }

    public void newServApp(View view){
        startActivity(new Intent(ServAppDetailActivity.this,CreateServAppActivity.class));
    }
}
