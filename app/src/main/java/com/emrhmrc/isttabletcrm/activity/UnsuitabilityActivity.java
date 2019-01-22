package com.emrhmrc.isttabletcrm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.fragment.NewUnstabilityFragment;

public class UnsuitabilityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unsuitability);
    }

    private void openNewUnstability() {
        NewUnstabilityFragment fragment = NewUnstabilityFragment.newInstance();
        fragment.show(getSupportFragmentManager(), "reasonbrekadown");
    }
}
