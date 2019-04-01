package com.emrhmrc.isttabletcrm.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.SweetDialog.AnyDialog;
import com.emrhmrc.isttabletcrm.SweetDialog.SweetAlertDialog;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvServAppDetailAdapter;
import com.emrhmrc.isttabletcrm.adapter.SwipeToDelete;
import com.emrhmrc.isttabletcrm.api.APIHelper;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.bindingModel.ServiceAppointment;
import com.emrhmrc.isttabletcrm.databinding.ActivityServAppDetailBinding;
import com.emrhmrc.isttabletcrm.fragment.AddManuelProductFragment;
import com.emrhmrc.isttabletcrm.fragment.AddWorkmanshipFragment;
import com.emrhmrc.isttabletcrm.fragment.BeforeAfterPicFragment;
import com.emrhmrc.isttabletcrm.fragment.BreakdownTypeCodeFragment;
import com.emrhmrc.isttabletcrm.fragment.ControlListFragment;
import com.emrhmrc.isttabletcrm.fragment.DefaultMapFragment;
import com.emrhmrc.isttabletcrm.fragment.DetailServAppFormFragment;
import com.emrhmrc.isttabletcrm.fragment.ReasonAndAddenBreakdownFragment;
import com.emrhmrc.isttabletcrm.fragment.ReasonOfBreakdownFragment;
import com.emrhmrc.isttabletcrm.helper.AddBreakdownTypeCode;
import com.emrhmrc.isttabletcrm.helper.AddManuelProduct;
import com.emrhmrc.isttabletcrm.helper.AddNotes;
import com.emrhmrc.isttabletcrm.helper.CreateSubServAppSingleton;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id_Id;
import com.emrhmrc.isttabletcrm.models.MapModel;
import com.emrhmrc.isttabletcrm.models.Product.Product;
import com.emrhmrc.isttabletcrm.models.ServApp.CompleteByIdRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse2;
import com.emrhmrc.isttabletcrm.models.ServApp.Notes;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppDetailsList;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetById;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdNotes;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppDetails;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppModernizationChecklists;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppIdRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppHelperIds;
import com.emrhmrc.isttabletcrm.models.ServApp.UpsertByIdUpdateRequest;
import com.emrhmrc.isttabletcrm.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServAppDetailActivity extends AppCompatActivity implements OnItemClickListener,
        AddManuelProduct, AddNotes, AddBreakdownTypeCode {

    private static final String TAG = "ServAppDetailActivity";

    ActivityServAppDetailBinding binding;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.btn_kontrol_listesi)
    Button btn_kontrol_listesi;
    @BindView(R.id.txt_aciklamanot)
    TextView txt_aciklamanot;
    @BindString(R.string.loading)
    String loading;
    @BindString(R.string.sure)
    String sure;
    @BindString(R.string.doit)
    String doit;
    @BindString(R.string.dont)
    String dont;
    @BindString(R.string.close_servapp)
    String close;
    @BindString(R.string.update)
    String update;
    @BindString(R.string.toast_error)
    String error;
    @BindString(R.string.succes)
    String succes;
    @BindString(R.string.fillblanks)
    String blanks;

    private JsonApi jsonApi;
    private RcvServAppDetailAdapter adapter;
    private ShareData shareData;
    private List<ServAppGetByIdNotes> notes;
    private SweetAlertDialog dialog;
    private ServAppGetById model;
    private UpsertByIdUpdateRequest updateRequest;
    private boolean isOk;
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            Product product = (Product) intent.getSerializableExtra("product");
            String id = intent.getStringExtra("id");

            if (product != null) {
                boolean exist = false;
                for (ServAppGetByIdServAppDetails details : adapter.getItems()
                ) {
                    if (details.getInv_ProductId() != null) {
                        if (details.getInv_ProductId().getId().equals(product.getProductId())) {
                            exist = true;

                        }
                    }
                }
                if (id != null) {

                    for (int k = 0; k < adapter.getItems().size(); k++) {
                        if (adapter.getItems().get(k).getInv_ProductId() != null)
                            if (adapter.getItems().get(k).getInv_ProductId().getId().equals(id)) {
                                adapter.remove(k);
                            }

                    }
                }

                if (!exist) {
                    Log.d(TAG, "Product Not Exist ");
                    ServAppGetByIdServAppDetails add = new ServAppGetByIdServAppDetails();
                    add.setInv_ProductId(new Inv_Id("inv_subproductgroup", product.getName(), product.getProductId()));
                    add.setManuel(true);
                    add.setInv_Quantity(1);
                    add.setInv_WillBeBilled(true);
                    if (product.getUoM() == null)
                        add.setInv_Uomid(new Inv_Id("", "ADET", "11697a17-fdbf-e811-8103-005056b66d80"));
                    else add.setInv_Uomid(product.getUoM());
                    add.setInv_ProductDescription(product.getName());
                    adapter.add(add);
                }
            }


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_serv_app_detail);
        ButterKnife.bind(this);
        init();
        initDialog();
        getServAppById(shareData.getServAppId());
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-event-name"));


    }

    private void initDialog() {
        AnyDialog anyDialog = new AnyDialog(this);
        dialog = anyDialog.loading(loading);
    }

    private void openServappFormFragment() {

        DetailServAppFormFragment fragment = DetailServAppFormFragment.newInstance(ShareData.getInstance().getServAppId());
        fragment.show(getSupportFragmentManager(), "DetailServAppFormFragment");
    }

    private void openBreakdownFormFragment() {

        BreakdownTypeCodeFragment fragment = BreakdownTypeCodeFragment.newInstance();
        fragment.show(getSupportFragmentManager(), "BreakdownTypeCodeFragment");
    }

    private void openAddManuelProductFragment() {

        AddManuelProductFragment fragment = AddManuelProductFragment.newInstance();
        fragment.show(getSupportFragmentManager(), "AddManuelProductFragment");
    }

    private void mapFragment() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x - 100;
        int height = size.y - 200;
        MapModel map = new MapModel(ShareData.getInstance().getLatitude(), ShareData
                .getInstance().getLongitude(),
                "Title",
                "Description");
        ArrayList<MapModel> mapModels = new ArrayList<>();
        mapModels.add(map);
        DefaultMapFragment fragment = DefaultMapFragment.newInstance(mapModels, width, height);
        fragment.show(getSupportFragmentManager(), "MpaFragment");
    }

    private void init() {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        adapter = new RcvServAppDetailAdapter(getApplicationContext(), this);
        adapter.setListener(this);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(adapter);
        shareData = ShareData.getInstance();
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new SwipeToDelete(adapter));
        itemTouchHelper.attachToRecyclerView(rcv);
        updateRequest = new UpsertByIdUpdateRequest();
    }

    private void getServAppById(String id) {
        dialog.show();
        Call<ServAppGetById> call = jsonApi.servAppGetById(new ServAppIdRequest(id));
        APIHelper.enqueueWithRetry(call, new Callback<ServAppGetById>() {
            @Override
            public void onResponse(Call<ServAppGetById> call, Response<ServAppGetById> response) {
                if (response.isSuccessful()) {
                    model = response.body();
                    CreateSubServAppSingleton.getInstance().setServAppGetById(model);
                    notes = new ArrayList<>();
                    notes = model.getServiceAppointment().getServAppGetByIdNotes();
                    if (notes.size() > 0)
                        txt_aciklamanot.setText(notes.get(notes.size() - 1).getNoteText());

                    setModelToBind(model.getServiceAppointment());
                    ShareData.getInstance().setLongitude(model.getServiceAppointment().getInv_Longitude());
                    ShareData.getInstance().setLatitude(model.getServiceAppointment().getInv_Latitude());
                    adapter.setItems(model.getServiceAppointment().getServAppGetByIdServAppDetails());
                }
                dialog.dismissWithAnimation();
            }

            @Override
            public void onFailure(Call<ServAppGetById> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                CreateSubServAppSingleton.getInstance().setServAppGetById(null);
                dialog.dismissWithAnimation();
            }
        });


    }

    private void setModelToBind(ServiceAppointment model) {
        binding.setModel(model);
        ShareData.getInstance().setElevatorId(model.getInv_ElevatorId().getId());
        Log.d(TAG, "setElevatorId: " + model.getInv_ElevatorId().getId());

        if (model.getInv_TypeCode() != null) {

            if (model.getInv_TypeCode().getValue() == 3) {
                for (ServAppGetByIdServAppModernizationChecklists item : model.getServAppGetByIdServAppModernizationChecklists()
                ) {
                    item.setIs_modernization(true);

                }
                btn_kontrol_listesi.setVisibility(View.VISIBLE);
            } else if (model.getInv_TypeCode().getValue() == 1)
                btn_kontrol_listesi.setVisibility(View.VISIBLE);

            else btn_kontrol_listesi.setVisibility(View.INVISIBLE);
        } else btn_kontrol_listesi.setVisibility(View.INVISIBLE);
        adapter.setItems(model.getServAppGetByIdServAppDetails());


    }

    private void cancelServApp() {

        CompleteByIdRequest request = new CompleteByIdRequest();
        request.setServiceAppId(ShareData.getInstance().getServAppId());
        request.setUserId(SingletonUser.getInstance().getUser().getUserId());
        request.setCompleteType(false);
        Call<DefaultResponse> call = jsonApi.servAppCompleteById(request);
        APIHelper.enqueueWithRetry(call, new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    new SweetAlertDialog(ServAppDetailActivity.this,
                            SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText(succes)
                            .setContentText("")
                            .show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                new SweetAlertDialog(ServAppDetailActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText(error)
                        .setContentText(t.getMessage())
                        .show();
            }
        });


    }

    private void closeServApp() {

        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(sure)
                .setContentText(close)
                .setCancelText(dont)
                .setConfirmText(doit)
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        CompleteByIdRequest request = new CompleteByIdRequest();
                        request.setServiceAppId(ShareData.getInstance().getServAppId());
                        request.setUserId(SingletonUser.getInstance().getUser().getUserId());
                        request.setCompleteType(true);
                        initDialog();
                        dialog.show();
                        Call<DefaultResponse> call = jsonApi.servAppCompleteById(request);
                        APIHelper.enqueueWithRetry(call, new Callback<DefaultResponse>() {
                            @Override
                            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                                dialog.dismissWithAnimation();
                                if (response.isSuccessful()) {

                                    new SweetAlertDialog(ServAppDetailActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                            .setTitleText(succes)
                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                    onBackPressed();
                                                }
                                            })
                                            .show();


                                } else {

                                    new SweetAlertDialog(ServAppDetailActivity.this, SweetAlertDialog.WARNING_TYPE)
                                            .setTitleText(response.message())
                                            .show();

                                }
                            }

                            @Override
                            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                                dialog.dismissWithAnimation();
                                new SweetAlertDialog(ServAppDetailActivity.this, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText(t.getMessage())
                                        .show();
                            }
                        });
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .show();


    }

    @Override
    public void onItemClicked(Object item, int positon) {

    }

    public void newServApp() {

        startActivity(new Intent(ServAppDetailActivity.this, CreateServAppActivity.class));
    }

    public void openControlList() {
        for (ServAppGetByIdServAppModernizationChecklists item : model.getServiceAppointment().getServAppGetByIdServAppModernizationChecklists()
        ) {
            if (model.getServiceAppointment().getInv_TypeCode().getValue() == 3)
                item.setIs_modernization(true);

        }
        ControlListFragment fragment =
                ControlListFragment.newInstance(model.getServiceAppointment().getServAppGetByIdServAppModernizationChecklists());
        fragment.setCancelable(false);
        fragment.show(getSupportFragmentManager(), "KontrolListesi");

    }

    private void openAddPiece() {
        ShareData.getInstance().setAdd_sub_piece(true);
        startActivity(new Intent(ServAppDetailActivity.this, AddPieceActivity.class));
    }

    private void openElevatorDetail() {
        startActivity(new Intent(ServAppDetailActivity.this, ElevatorDetailActivity.class));
    }

    private void openBeforeAfter() {
        BeforeAfterPicFragment fragment = BeforeAfterPicFragment.newInstance(updateRequest.getServAppNotesList());
        fragment.show(getSupportFragmentManager(), "beforeafter");
    }

    private void openReasonOfBreakdown() {
        ReasonOfBreakdownFragment fragment =
                ReasonOfBreakdownFragment.newInstance(new ArrayList<>(notes));
        fragment.show(getSupportFragmentManager(), "reasonbrekadown");
    }

    private void openReasonAndAddedBreakdown() {
        ReasonAndAddenBreakdownFragment fragment =
                ReasonAndAddenBreakdownFragment.newInstance(model.getServiceAppointment().getServAppGetByIdServAppBreakdownTypes());
        fragment.show(getSupportFragmentManager(), "ReasonAndAddenBreakdownFragment");
    }

    @OnClick({R.id.img_cancel, R.id.txt_cancel, R.id.btn_closejob, R.id.txt_yeni, R.id.img_yeni,
            R.id.img_add, R.id.txt_add, R.id.btn_beforeafter, R.id.txt_aciklamanot,
            R.id.txt_asansorno, R.id.txt_servis_raporu, R.id.img_servis_raporu, R.id.img_gps,
            R.id.img_menu, R.id.txt_kaydet, R.id.img_kaydet, R.id.btn_ariza_kodu,
            R.id.btn_ariza_nedeni, R.id.add_job_2, R.id.img_add_3, R.id.btn_kontrol_listesi,
            R.id.img_add_2, R.id.add_job})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_cancel:
                cancelServApp();
                break;
            case R.id.txt_cancel:
                cancelServApp();
                break;
            case R.id.btn_beforeafter:
                openBeforeAfter();
                break;
            case R.id.img_gps:
                mapFragment();
                break;
            case R.id.txt_asansorno:
                openElevatorDetail();
                break;
            case R.id.btn_kontrol_listesi:
                openControlList();
                break;
            case R.id.txt_yeni:
                newServApp();
                break;
            case R.id.img_yeni:
                newServApp();
                break;
            case R.id.btn_closejob:
                closeServApp();
                break;
            case R.id.img_add:
                openAddPiece();
                break;
            case R.id.txt_add:
                openAddPiece();
                break;
            case R.id.txt_aciklamanot:
                openReasonOfBreakdown();
                break;
            case R.id.txt_servis_raporu:
                openServappFormFragment();
                break;
            case R.id.img_servis_raporu:
                openServappFormFragment();
                break;
            case R.id.btn_ariza_kodu:
                openBreakdownFormFragment();
                break;
            case R.id.img_menu:
                onBackPressed();
                break;
            case R.id.txt_kaydet:
                checkUpsertById();
                break;
            case R.id.img_kaydet:
                checkUpsertById();
                break;
            case R.id.btn_ariza_nedeni:
                openReasonAndAddedBreakdown();
                break;
            case R.id.add_job_2:
                openAddManuelProductFragment();
                break;
            case R.id.img_add_3:
                openAddManuelProductFragment();
                break;
            case R.id.img_add_2:
                openWordkman();
                break;
            case R.id.add_job:
                openWordkman();
                break;
        }
    }

    private void openWordkman() {
        AddWorkmanshipFragment fragment = AddWorkmanshipFragment.newInstance();
        fragment.show(getSupportFragmentManager(), "AddWorkmanshipFragment");

    }

    @Override
    public void onBackPressed() {
        CreateSubServAppSingleton.getInstance().setServAppGetById(null);
        super.onBackPressed();
    }

    private void checkUpsertById() {
        isOk = false;
        if (checkProductAmount()) {

            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(sure)
                    .setContentText(update)
                    .setCancelText(dont)
                    .setConfirmText(doit)
                    .showCancelButton(true)
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            isOk = true;
                            sweetAlertDialog.cancel();
                            updateServApp();

                        }
                    })
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                        }
                    })
                    .show();
        }

    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        CreateSubServAppSingleton.getInstance().setServAppGetById(null);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    private void openDialog() {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Hata...")
                .setContentText("Miktarları belirleyiniz !")
                .show();
    }

    private boolean checkProductAmount() {


        for (ServAppGetByIdServAppDetails details : adapter.getItems()
        ) {
            if (details.getInv_Quantity() == 0) {
                openDialog();
                return false;
            }
            if (details.getInv_Uomid() == null) {
                details.setInv_Uomid(new Inv_Id("uom", "ADET", "11697a17-fdbf-e811-8103" +
                        "-005056b66d80"));
            }


        }


        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void addProduct(ServAppGetByIdServAppDetails product) {
        adapter.add(product);
        adapter.notifyItemInserted(adapter.getItems().size() - 1);

    }

    private void updateServApp() {
        initDialog();
        //Eklenen Ürünleri Map Edelim
        updateRequest.getServAppDetailsList().clear();
        for (ServAppGetByIdServAppDetails current : adapter.getItems()
        ) {
            ServAppDetailsList item = new ServAppDetailsList();
            item.setInv_ServiceAppDetailId(StringUtil.emptyToString(current.getInv_ServiceAppDetailId()));
            item.setInv_ProductDescription(StringUtil.nullToString(current.getInv_ProductDescription()));
            item.setInv_Description(StringUtil.emptyToString(current.getInv_Description()));
            if (current.getInv_ProductId() != null)
                item.setInv_ProductId(new Inv_Id_Id(current.getInv_ProductId().getId()));
            if (current.getInv_Uomid() != null)
                item.setInv_Uomid(new Inv_Id_Id(current.getInv_Uomid().getId()));
            else item.setInv_Uomid(new Inv_Id_Id());
            item.setInv_Quantity(current.getInv_Quantity() != null ? current.getInv_Quantity() : 0);
            item.setInv_Price(current.getInv_Price() != null ? current.getInv_Price() : 0);
            item.setInv_WillBeBilled(current.getInv_WillBeBilled());
            updateRequest.getServAppDetailsList().add(item);

        }
        //
        updateRequest.setUserId(SingletonUser.getInstance().getUser().getUserId());
        updateRequest.getServiceAppointment().setActivityId(model.getServiceAppointment().getActivityId());
        checkIds();
        dialog.show();
        Call<DefaultResponse2> call = jsonApi.updateServapp(updateRequest);
        APIHelper.enqueueWithRetry(call, new Callback<DefaultResponse2>() {
            @Override
            public void onResponse(Call<DefaultResponse2> call, Response<DefaultResponse2> response) {
                dialog.dismissWithAnimation();
                if (response.isSuccessful()) {

                    new SweetAlertDialog(ServAppDetailActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText(succes)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    onBackPressed();
                                }
                            })
                            .show();


                } else {

                    new SweetAlertDialog(ServAppDetailActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(response.message())
                            .show();

                }
            }

            @Override
            public void onFailure(Call<DefaultResponse2> call, Throwable t) {
                dialog.dismissWithAnimation();
                new SweetAlertDialog(ServAppDetailActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText(t.getMessage())
                        .show();
            }
        });


    }

    private void checkIds() {
        if (updateRequest.getServiceAppointment().getInv_BreakdownCodeId() == null)
            updateRequest.getServiceAppointment().setInv_BreakdownCodeId(new Inv_Id_Id());
        if (updateRequest.getServiceAppointment().getInv_BreakdownDefCodeid() == null)
            updateRequest.getServiceAppointment().setInv_BreakdownDefCodeid(new Inv_Id_Id());
        if (updateRequest.getServiceAppointment().getInv_MainProductGroupid() == null)
            updateRequest.getServiceAppointment().setInv_MainProductGroupid(new Inv_Id_Id());
        if (updateRequest.getServiceAppointment().getInv_SubProductGroupid() == null)
            updateRequest.getServiceAppointment().setInv_SubProductGroupid(new Inv_Id_Id());
    }

    @Override
    public void addNote(List<Notes> notes) {
        updateRequest.setServAppNotesList(new ArrayList<>());
        if (notes.get(0).getSubject() != null)
            updateRequest.getServAppNotesList().add(notes.get(0));
        if (notes.get(1).getSubject() != null)
            updateRequest.getServAppNotesList().add(notes.get(1));

    }

    @Override
    public void addIds(ServiceAppHelperIds ids) {
        updateRequest.getServiceAppointment().setInv_BreakdownCodeId(ids.getInv_BreakdownCodeId());
        updateRequest.getServiceAppointment().setInv_BreakdownDefCodeid(ids.getInv_BreakdownDefCodeid());
        updateRequest.getServiceAppointment().setInv_MainProductGroupid(ids.getInv_MainProductGroupid());
        updateRequest.getServiceAppointment().setInv_SubProductGroupid(ids.getInv_SubProductGroupid());


    }
}
