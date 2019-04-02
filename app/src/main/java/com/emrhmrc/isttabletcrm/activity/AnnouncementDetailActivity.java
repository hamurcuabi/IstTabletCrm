package com.emrhmrc.isttabletcrm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.models.Notification.Notification;
import com.emrhmrc.isttabletcrm.util.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnnouncementDetailActivity extends AppCompatActivity {

    @BindView(R.id.txt_header)
    TextView txtHeader;
    @BindView(R.id.txt_date)
    TextView txtDate;
    @BindView(R.id.txt_descp)
    TextView txtDescp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_detail);
        ButterKnife.bind(this);
        setTexts();
    }

    private void setTexts() {
        Notification n = ShareData.getInstance().getNotificationObject();
        txtHeader.setText(StringUtil.nullToString(n.getSubject()));
        txtDate.setText(StringUtil.nullToString(n.getScheduledStart()));
        txtDescp.setText(StringUtil.nullToString(n.getDescription()));
    }

    @OnClick({R.id.img_return, R.id.txt_return,R.id.img_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_return:
                super.onBackPressed();
                break;
            case R.id.txt_return:
                super.onBackPressed();
                break;
                case R.id.img_menu:
                super.onBackPressed();
                break;
        }
    }
}
