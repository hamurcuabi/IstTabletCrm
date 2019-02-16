package com.emrhmrc.isttabletcrm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.emrhmrc.isttabletcrm.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnnouncementDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_detail);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_return, R.id.txt_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_return:
                super.onBackPressed();
                break;
            case R.id.txt_return:
                super.onBackPressed();
                break;
        }
    }
}
