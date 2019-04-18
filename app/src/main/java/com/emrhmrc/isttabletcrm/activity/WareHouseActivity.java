package com.emrhmrc.isttabletcrm.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.ViewPagerAdapter;
import com.emrhmrc.isttabletcrm.fragment.FragStokBilgisi;
import com.emrhmrc.isttabletcrm.fragment.FragTaleplerim;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WareHouseActivity extends AppCompatActivity {

    private static final String TAG = "WareHouseActivity";
    @BindView(R.id.btn_info)
    Button btnInfo;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.btn_wish)
    Button btnWish;
    @BindDrawable(R.drawable.btn_malzeme_2)
    Drawable first;
    @BindDrawable(R.drawable.btn_taleplerim)
    Drawable second;
    @BindString(R.string.loading)
    String loading;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ware_house);
        ButterKnife.bind(this);
        setupViewPager();

    }

    private void rcvFirst() {
        btnInfo.setBackground(first);
        btnWish.setBackground(second);
        viewpager.setCurrentItem(0);
    }

    private void rcvSecond() {
        btnInfo.setBackground(second);
        btnWish.setBackground(first);
        viewpager.setCurrentItem(1);

    }

    @OnClick({R.id.btn_info, R.id.btn_wish, R.id.img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_info:
                rcvFirst();
                break;
            case R.id.btn_wish:
                rcvSecond();
                break;
            case R.id.img_back:
                super.onBackPressed();
                break;

        }
    }

    private void setupViewPager() {

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(FragStokBilgisi.newInstance(), "FragStokBilgisi");
        viewPagerAdapter.addFragment(FragTaleplerim.newInstance(), "FragTaleplerim");
        viewpager.setAdapter(viewPagerAdapter);
        viewpager.setCurrentItem(0);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rcvFirst();
                        break;
                    case 1:
                        rcvSecond();
                        break;
                    default:
                        rcvFirst();
                        break;
                }
            }
        });
    }


}
