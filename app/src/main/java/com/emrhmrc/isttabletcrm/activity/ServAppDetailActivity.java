package com.emrhmrc.isttabletcrm.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.NestedScrollView;
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
import com.emrhmrc.isttabletcrm.SweetDialog.DialogButtonListener;
import com.emrhmrc.isttabletcrm.SweetDialog.DialogCreater;
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
import com.emrhmrc.isttabletcrm.fragment.CreateNewWareRequestServappFragment;
import com.emrhmrc.isttabletcrm.fragment.DefaultMapFragment;
import com.emrhmrc.isttabletcrm.fragment.DetailServAppFormFragment;
import com.emrhmrc.isttabletcrm.fragment.NewUnstabilityServAppFragment;
import com.emrhmrc.isttabletcrm.fragment.ReasonAndAddenBreakdownFragment;
import com.emrhmrc.isttabletcrm.fragment.ReasonOfBreakdownFragment;
import com.emrhmrc.isttabletcrm.helper.AddBreakdownTypeCode;
import com.emrhmrc.isttabletcrm.helper.AddManuelProduct;
import com.emrhmrc.isttabletcrm.helper.AddNotes;
import com.emrhmrc.isttabletcrm.helper.AddOrDeleteBreakdown;
import com.emrhmrc.isttabletcrm.helper.CreateSubServAppSingleton;
import com.emrhmrc.isttabletcrm.helper.Methodes;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id_Id;
import com.emrhmrc.isttabletcrm.models.Elevator.ServAppDetails;
import com.emrhmrc.isttabletcrm.models.MapModel;
import com.emrhmrc.isttabletcrm.models.Product.Product;
import com.emrhmrc.isttabletcrm.models.ServApp.CompleteByIdRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse2;
import com.emrhmrc.isttabletcrm.models.ServApp.Notes;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppDetailsList;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetById;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdNotes;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppBreakdownTypes;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppDetails;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppModernizationChecklists;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppIdRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.ServappCheckinRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.ServappSendToSuperVisorRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppHelperIds;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppointmentSupervisor;
import com.emrhmrc.isttabletcrm.models.ServApp.UpsertByIdUpdateRequest;
import com.emrhmrc.isttabletcrm.util.StringUtil;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

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
        AddManuelProduct, AddNotes, AddBreakdownTypeCode, AddOrDeleteBreakdown, DialogButtonListener {

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
    @BindString(R.string.read_error)
    String read_error;
    @BindString(R.string.update)
    String update;
    @BindString(R.string.toast_error)
    String error;
    @BindString(R.string.succes)
    String succes;
    @BindString(R.string.fillblanks)
    String blanks;
    @BindString(R.string.iptal_islemi)
    String cancel;
    @BindString(R.string.amount_check)
    String amount;
    @BindString(R.string.try_again)
    String try_again;
    @BindView(R.id.btn_ariza_kodu)
    Button btnArizaKodu;
    @BindView(R.id.btn_ariza_nedeni)
    Button btnArizaNedeni;
    @BindView(R.id.nested)
    NestedScrollView nested;

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
            if (id != null) {

                for (int k = 0; k < adapter.getItems().size(); k++) {
                    if (adapter.getItems().get(k).getInv_ProductId() != null)
                        if (adapter.getItems().get(k).getInv_ProductId().getId().equals(id)) {
                            adapter.remove(k);
                        }

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
        nested.fullScroll(View.FOCUS_UP);
        nested.scrollTo(0, 0);

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
                dialog.dismissWithAnimation();
                if (response.isSuccessful()) {
                    if (response.body().getSuccess()) {
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
                    } else {
                        DialogCreater.errorDialog(ServAppDetailActivity.this, response.body().getErrorMsg());
                    }
                } else {
                    DialogCreater.errorDialog(ServAppDetailActivity.this, try_again);
                }

            }

            @Override
            public void onFailure(Call<ServAppGetById> call, Throwable t) {
                CreateSubServAppSingleton.getInstance().setServAppGetById(null);
                dialog.dismissWithAnimation();
                DialogCreater.errorDialog(ServAppDetailActivity.this, try_again);
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


        if (model.getInv_TypeCode() != null) {
            if (model.getInv_TypeCode().getValue() != 3) {

                btnArizaKodu.setVisibility(View.GONE);
                btnArizaNedeni.setVisibility(View.GONE);

            }
        }

    }

    private void cancelServApp() {
        DialogCreater.questionDialog(this, this, cancel, 2);

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

        ShareData.getInstance().setSub_servapp(true);
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
            R.id.img_add_2, R.id.add_job, R.id.img_yeni_uygunsuzluk, R.id.txt_yeni_uygunsuzluk,
            R.id.img_qr, R.id.img_send_supervisor, R.id.txt_send_supervisor,
            R.id.img_yeni_stoktalep, R.id.txt_stok_talep})
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
                openWorkman();
                break;
            case R.id.add_job:
                openWorkman();
                break;
            case R.id.img_yeni_uygunsuzluk:
                openNewUnsuit();
                break;
            case R.id.txt_yeni_uygunsuzluk:
                openNewUnsuit();
                break;
            case R.id.img_qr:
                startScan();
                break;
            case R.id.img_send_supervisor:
                sendToSupervisor();
                break;
            case R.id.txt_send_supervisor:
                sendToSupervisor();
                break;
            case R.id.txt_stok_talep:
                openNewWarehouse();
                break;
            case R.id.img_yeni_stoktalep:
                openNewWarehouse();
                break;


        }
    }

    private void sendToSupervisor() {
        //map ediliyor
        List<ServAppDetails> servAppDetails = new ArrayList<>();
        ServiceAppointment serviceAppointment = model.getServiceAppointment();
        ServiceAppointmentSupervisor supervisor = new ServiceAppointmentSupervisor();
        ServappSendToSuperVisorRequest request = new ServappSendToSuperVisorRequest();
        request.setUserId(ShareData.getInstance().getUserId());
        //map supervispor
        supervisor.setActivityId(serviceAppointment.getActivityId());
        supervisor.setInv_BreakdownCodeid(serviceAppointment.getInv_BreakdownCodeid());
        supervisor.setInv_BreakdownDefCodeid(serviceAppointment.getInv_BreakdownDefCodeid());
        supervisor.setInv_MainProductGroupid(serviceAppointment.getInv_MainProductGroupid());
        supervisor.setInv_SubProductGroupid(serviceAppointment.getInv_SubProductGroupid());
        supervisor.setStatusCode(serviceAppointment.getStatusCode());
        supervisor.setScheduledStart(serviceAppointment.getScheduledStart());
        supervisor.setScheduledEnd(serviceAppointment.getScheduledEnd());
        supervisor.setInv_TypeCode(serviceAppointment.getInv_TypeCode());
        supervisor.setSubject(serviceAppointment.getSubject());
        supervisor.setInv_ElevatorId(serviceAppointment.getInv_ElevatorId());
        supervisor.setInv_CustomerId(serviceAppointment.getInv_CustomerId());
        //map
        request.setServiceAppointment(supervisor);
        //map details
        for (ServAppGetByIdServAppDetails current :
                serviceAppointment.getServAppGetByIdServAppDetails()
        ) {
            ServAppDetails details = new ServAppDetails();
            details.setDeleted(false);
            details.setInv_Quantity(current.getInv_Quantity());
            details.setTransactionCurrencyId(new Inv_Id("transactioncurrency", "Türk Lirası", "ECF22335-4D62-E811-80FB-005056B66D80"));
            details.setInv_ApprovalStCode(current.getInv_ApprovalStCode());
            details.setInv_Description(current.getInv_Description());
            details.setInv_LineNo(current.getInv_LineNo());
            details.setInv_Price(current.getInv_Price());
            details.setInv_ProductDescription(current.getInv_ProductDescription());
            details.setInv_ProductId(current.getInv_ProductId());
            details.setInv_ServiceAppDetailId(current.getInv_ServiceAppDetailId());
            details.setInv_Uomid(current.getInv_Uomid());
            details.setInv_WarrantyStatusCode(current.getInv_WarrantyStatusCode());
            details.setInv_WillBeBilled(current.getInv_WillBeBilled());
            servAppDetails.add(details);

        }
        request.setServAppDetailsList(servAppDetails);
        SweetAlertDialog alertDialog = DialogCreater.loadingDialog(this);
        alertDialog.show();
        Call<DefaultResponse2> call = jsonApi.sendToSupervisor(request);
        APIHelper.enqueueWithRetry(call, new Callback<DefaultResponse2>() {
            @Override
            public void onResponse(Call<DefaultResponse2> call, Response<DefaultResponse2> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSucces()) {
                        DialogCreater.succesDialog(ServAppDetailActivity.this);
                    } else {
                        DialogCreater.errorDialog(ServAppDetailActivity.this, response.body().getErrorMsg());
                    }
                } else {
                    DialogCreater.errorDialog(ServAppDetailActivity.this, try_again);
                }
                alertDialog.dismissWithAnimation();
            }

            @Override
            public void onFailure(Call<DefaultResponse2> call, Throwable t) {
                alertDialog.dismissWithAnimation();
                DialogCreater.errorDialog(ServAppDetailActivity.this, try_again);
            }
        });

    }

    private void openWorkman() {
        AddWorkmanshipFragment fragment = AddWorkmanshipFragment.newInstance();
        fragment.show(getSupportFragmentManager(), "AddWorkmanshipFragment");

    }

    private void openNewUnsuit() {
        NewUnstabilityServAppFragment fragment = NewUnstabilityServAppFragment.newInstance(model);
        fragment.show(getSupportFragmentManager(), "NewUnstabilityServAppFragment");

    }

    private void openNewWarehouse() {
        CreateNewWareRequestServappFragment fragment = CreateNewWareRequestServappFragment.newInstance();
        fragment.show(getSupportFragmentManager(), "CreateNewWareRequestServappFragment");

    }

    @Override
    public void onBackPressed() {
        CreateSubServAppSingleton.getInstance().setServAppGetById(null);
        super.onBackPressed();
    }

    private void checkUpsertById() {
        isOk = false;
        if (checkProductAmount()) {
            DialogCreater.questionDialog(ServAppDetailActivity.this, this, update, 1);
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
        DialogCreater.errorDialog(this, amount);
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
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                Log.d("MainActivity", result.getContents());
                DialogCreater.succesDialog(ServAppDetailActivity.this, result.getContents());
                //Api Hazır çağır
                doCheckin(result.getContents());

            } else {
                DialogCreater.errorDialog(ServAppDetailActivity.this, read_error);
            }
        }

    }

    private void doCheckin(String contents) {

        ServappCheckinRequest request = new ServappCheckinRequest();
        request.setInv_QrCode(contents);
        request.setUserId(ShareData.getInstance().getUserId());
        request.setInv_Date(Methodes.getCurrenttime());

        Call<DefaultResponse> call = jsonApi.checkInCall(request);
        APIHelper.enqueueWithRetry(call, new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                if (response.isSuccessful()) {
                    DialogCreater.succesDialog(ServAppDetailActivity.this, response.message());
                } else {
                    DialogCreater.errorDialog(ServAppDetailActivity.this, response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                DialogCreater.errorDialog(ServAppDetailActivity.this, t.getMessage());

            }
        });


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
            item.setInv_Quantity(current.getInv_Quantity());
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
        updateRequest.getServiceAppointment().setInv_BreakdownCodeId(new Inv_Id_Id(ids.getInv_BreakdownCodeId().getId()));
        updateRequest.getServiceAppointment().setInv_BreakdownDefCodeid(ids.getInv_BreakdownDefCodeid());
        updateRequest.getServiceAppointment().setInv_MainProductGroupid(ids.getInv_MainProductGroupid());
        updateRequest.getServiceAppointment().setInv_SubProductGroupid(ids.getInv_SubProductGroupid());
        model.getServiceAppointment().setInv_BreakdownCodeid(ids.getInv_BreakdownCodeId());
        binding.setModel(model.getServiceAppointment());


    }

    @Override
    public void addBreakdown(ServAppGetByIdServAppBreakdownTypes item) {
        model.getServiceAppointment().getServAppGetByIdServAppBreakdownTypes().add(item);
    }

    @Override
    public void updateBreakdown(String id, boolean deleteStatus) {

        for (ServAppGetByIdServAppBreakdownTypes item : model.getServiceAppointment().getServAppGetByIdServAppBreakdownTypes()) {
            if (item.getInv_BreakdownTypeId() != null) {
                if (item.getInv_BreakdownTypeId().getId().equals(id)) item.setDeleted(deleteStatus);
            }

        }
    }

    @Override
    public void deleteBreakdown(String id) {
        if (id != null && !id.isEmpty()) {
            for (int i = 0; i < model.getServiceAppointment().getServAppGetByIdServAppBreakdownTypes().size(); i++) {
                if (model.getServiceAppointment().getServAppGetByIdServAppBreakdownTypes().get(i).getInv_BreakdownTypeId() != null) {
                    if (model.getServiceAppointment().getServAppGetByIdServAppBreakdownTypes().get(i).getInv_BreakdownTypeId().getId().equals(id))
                        model.getServiceAppointment().getServAppGetByIdServAppBreakdownTypes().remove(i);
                }

            }
        }
    }

    private void startScan() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Tara");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }

    private void cancelServAppDoit() {
        CompleteByIdRequest request = new CompleteByIdRequest();
        request.setServiceAppId(ShareData.getInstance().getServAppId());
        request.setUserId(SingletonUser.getInstance().getUser().getUserId());
        request.setCompleteType(false);
        initDialog();
        dialog.show();
        Call<DefaultResponse> call = jsonApi.servAppCompleteById(request);
        APIHelper.enqueueWithRetry(call, new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                dialog.dismissWithAnimation();
                if (response.isSuccessful()) {
                    DialogCreater.doneDialog(ServAppDetailActivity.this);

                } else {
                    DialogCreater.errorDialog(ServAppDetailActivity.this, response.message());
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                dialog.dismissWithAnimation();
                DialogCreater.errorDialog(ServAppDetailActivity.this, t.getMessage());
            }
        });
    }

    @Override
    public void onConfirmButton(int id) {
        switch (id) {
            case 1:
                updateServApp();
                break;
            case 2:
                cancelServAppDoit();
                break;
        }

    }

    @Override
    public void onCancelButton(int id) {
        DialogCreater.errorDialog(this, "İptal Edildi");
    }
}
