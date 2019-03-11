package com.emrhmrc.isttabletcrm.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Base64;
import android.util.Log;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.SweetDialog.AnyDialog;
import com.emrhmrc.isttabletcrm.SweetDialog.SweetAlertDialog;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvTechnicalAdapter;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.fragment.DetailTechnicalFragment;
import com.emrhmrc.isttabletcrm.models.Document.TechnicDocument;
import com.emrhmrc.isttabletcrm.models.Document.TechnicalDocument;
import com.emrhmrc.isttabletcrm.util.StringUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TechnicalDocumentActivity extends AppCompatActivity implements OnItemClickListener {

    private static final String TAG = "TechnicalDocumentActivi";
    @BindView(R.id.search)
    SearchView search;
    @BindView(R.id.rcw_servapp)
    RecyclerView rcwServapp;
    @BindString(R.string.loading)
    String loading;
    private JsonApi jsonApi;
    private RcvTechnicalAdapter adapter;
    private SweetAlertDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecncal_document);
        ButterKnife.bind(this);
        init();
        getAllList();
    }

    private void getAllList() {

        pDialog.show();
        Call<TechnicalDocument> call = jsonApi.getTechnicalDocumentAll();
        call.enqueue(new Callback<TechnicalDocument>() {
            @Override
            public void onResponse(Call<TechnicalDocument> call, Response<TechnicalDocument> response) {
                if (response.isSuccessful()) {
                    final TechnicalDocument model = response.body();
                    adapter.setItems(model.getTechnicDocuments());
                    adapter.setItemsFilter(model.getTechnicDocuments());
                    search();
                    pDialog.dismissWithAnimation();
                }
            }

            @Override
            public void onFailure(Call<TechnicalDocument> call, Throwable t) {

                Log.d(TAG, "onFailure: " + t.getMessage());
                pDialog.dismissWithAnimation();
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
        AnyDialog anyDialog = new AnyDialog(this);
        pDialog = anyDialog.loading(loading);
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
        super.onBackPressed();
    }

    @Override
    public void onItemClicked(Object item, int positon) {

        final TechnicDocument document = (TechnicDocument) item;

        //if File PDF
        if (document.getMimeType().equals(StringUtil.PDF)) {
            final File dwldsPath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + document.getFileName());
            byte[] pdfAsBytes = Base64.decode(document.getDocumentBody(), 0);
            FileOutputStream os = null;
            try {
                os = new FileOutputStream(dwldsPath, false);
                os.write(pdfAsBytes);
                os.flush();
                os.close();
            } catch (IOException e) {
                Log.d(TAG, "Create File Error " + e.getMessage());
                e.printStackTrace();
            }
            //Open
            String filename = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) +
                    "/" + document.getFileName();
            File file = new File(filename);
            if (file.exists()) {
                openDetail(document);

            } else {
                new SweetAlertDialog(TechnicalDocumentActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Hata...")
                        .setContentText("Dosya Bozuk !")
                        .show();
            }
        } else {
            openDetail(document);
        }


    }


}
