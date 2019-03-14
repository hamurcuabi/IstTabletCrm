package com.emrhmrc.isttabletcrm.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

public class FragmentStatePagerAdapter extends android.support.v4.app.FragmentStatePagerAdapter {

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public FragmentStatePagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
