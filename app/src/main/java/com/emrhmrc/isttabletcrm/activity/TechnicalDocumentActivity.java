package com.emrhmrc.isttabletcrm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvTechnicalAdapter;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.fragment.DetailTechnicalFragment;
import com.emrhmrc.isttabletcrm.models.Document.TechnicDocument;
import com.emrhmrc.isttabletcrm.models.Document.TechnicalDocument;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TechnicalDocumentActivity extends AppCompatActivity implements OnItemClickListener {

    @BindView(R.id.search)
    SearchView search;
    @BindView(R.id.rcw_servapp)
    RecyclerView rcwServapp;
    private JsonApi jsonApi;
    private RcvTechnicalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecncal_document);
        ButterKnife.bind(this);
        init();
        getAllList();
    }

    private void getAllList() {

        Call<TechnicalDocument> call = jsonApi.getTechnicalDocumentAll();
        call.enqueue(new Callback<TechnicalDocument>() {
            @Override
            public void onResponse(Call<TechnicalDocument> call, Response<TechnicalDocument> response) {
                if (response.isSuccessful()) {
                    final TechnicalDocument model = response.body();
                    adapter.setItems(model.getTechnicDocuments());
                    adapter.setItemsFilter(model.getTechnicDocuments());
                    search();
                }
            }

            @Override
            public void onFailure(Call<TechnicalDocument> call, Throwable t) {

            }
        });

    }

    private void openDetail(TechnicDocument document) {
        DetailTechnicalFragment fragment = DetailTechnicalFragment.newInstance(document);
        fragment.show(getSupportFragmentManager(), "DetailTechnicalFragment");
    }

    private void init() {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        adapter = new RcvTechnicalAdapter(getApplicationContext(), this);
        adapter.setListener(this);
        rcwServapp.setLayoutManager(new LinearLayoutManager(this));
        rcwServapp.setAdapter(adapter);
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

    @OnClick(R.id.img_back)
    public void onViewClicked() {
    }

    @Override
    public void onItemClicked(Object item, int positon) {

        final TechnicDocument document = (TechnicDocument) item;
        openDetail(document);
    }
}
