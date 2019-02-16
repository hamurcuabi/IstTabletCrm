package com.emrhmrc.isttabletcrm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvUnstabilityAdapter;
import com.emrhmrc.isttabletcrm.fragment.NewUnstabilityFragment;
import com.emrhmrc.isttabletcrm.helper.SingletonListUnsuitability;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppUnsuitabilities;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UnsuitabilityActivity extends AppCompatActivity implements OnItemClickListener {

    @BindView(R.id.search)
    SearchView search;
    @BindView(R.id.rcw_servapp)
    RecyclerView rcwServapp;
    private List<ServAppGetByIdServAppUnsuitabilities> list;
    private RcvUnstabilityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unsuitability);
        ButterKnife.bind(this);
        list = SingletonListUnsuitability.getInstance().getByIdServAppUnsuitabilities();
        init();
    }

    private void init() {
        rcwServapp.setHasFixedSize(true);
        rcwServapp.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RcvUnstabilityAdapter(getApplicationContext(), this);
        adapter.setListener(this);
        rcwServapp.setAdapter(adapter);
        adapter.setItems(list);
        adapter.setItemsFilter(list);
        search();

    }

    private void openNewUnstability() {
        NewUnstabilityFragment fragment = NewUnstabilityFragment.newInstance();
        fragment.show(getSupportFragmentManager(), "reasonbrekadown");
    }

    @OnClick({R.id.img_back, R.id.txt_add, R.id.img_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                super.onBackPressed();
                break;
            case R.id.txt_add:
                openNewUnstability();
                break;
            case R.id.img_add:
                openNewUnstability();
                break;
        }
    }

    private void search() {
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }

    @Override
    public void onItemClicked(Object item, int positon) {

    }
}
