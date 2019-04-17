package com.emrhmrc.isttabletcrm.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.ViewPagerAdapter;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.fragment.FragMalzemeGecmisi;
import com.emrhmrc.isttabletcrm.fragment.FragOmurluParcalar;
import com.emrhmrc.isttabletcrm.fragment.FragServisler;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.models.Elevator.Elevator;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorGetById;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorIdRequest;
import com.emrhmrc.isttabletcrm.util.StringUtil;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ElevatorDetailActivity extends AppCompatActivity implements OnItemClickListener {

    private static final String TAG = "ElevatorDetailActivity";
    @BindView(R.id.txt_elevator_name)
    TextView txtElevatorName;
    @BindView(R.id.txt_asansorname)
    TextView txtAsansorname;
    @BindView(R.id.txt_apartadi)
    TextView txtApartadi;
    @BindView(R.id.txt_blokadi)
    TextView txtBlokadi;
    @BindView(R.id.txt_panaromikmi)
    TextView txtPanaromikmi;
    @BindView(R.id.txt_tekniktakim)
    TextView txtTekniktakim;
    @BindView(R.id.txt_etiketrengi)
    TextView txtEtiketrengi;
    @BindView(R.id.txt_sozlemebitis)
    TextView txtSozlemebitis;
    @BindView(R.id.txt_asansormanike)
    TextView txtAsansormanike;
    @BindView(R.id.txt_anlikdurum)
    TextView txtAnlikdurum;
    @BindView(R.id.txt_hizmetyili)
    TextView txtHizmetyili;
    @BindView(R.id.txt_adsoyad)
    TextView txtAdsoyad;
    @BindView(R.id.txt_arizadakisorun)
    TextView txtArizadakisorun;
    @BindView(R.id.txt_yaptiranfirma)
    TextView txtYaptiranfirma;
    @BindView(R.id.txt_ozellikler)
    TextView txtOzellikler;
    @BindView(R.id.txt_kimlikno)
    TextView txtKimlikno;
    @BindView(R.id.txt_kapasite)
    TextView txtKapasite;
    @BindView(R.id.txt_katsayisi)
    TextView txtKatsayisi;
    @BindView(R.id.txt_sinif)
    TextView txtSinif;
    @BindView(R.id.txt_serino)
    TextView txtSerino;
    @BindView(R.id.txt_duraksayisi)
    TextView txtDuraksayisi;
    @BindView(R.id.txt_asansorhizi)
    TextView txtAsansorhizi;
    @BindDrawable(R.drawable.btn_malzeme_2)
    Drawable first;
    @BindDrawable(R.drawable.btn_taleplerim)
    Drawable second;
    @BindView(R.id.btn_wish)
    Button btnWish;
    @BindView(R.id.btn_omurlu)
    Button btnOmurlu;
    @BindView(R.id.btn_changeproductlist)
    Button btnChangeproductlist;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private JsonApi jsonApi;
    private ViewPagerAdapter viewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elevator_tab_detail);
        ButterKnife.bind(this);
        init();
        getElevatorById();
        setupViewPager();
    }


    private void init() {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
    }

    private void setTexts(Elevator model) {
        txtAsansorname.setText(StringUtil.nullToString(model.getInv_ElevatorNumber()));
        txtApartadi.setText(StringUtil.nullToString(model.getInv_AccountId().getText()));
        txtBlokadi.setText(StringUtil.nullToString(model.getInv_BlockName()));
        txtEtiketrengi.setText(StringUtil.nullToString(model.getInv_ColorCode().getText()));
        txtSozlemebitis.setText(StringUtil.nullToString(model.getInv_ContractEndDate()));
        txtAnlikdurum.setText(StringUtil.nullToString(model.getInv_WorkingStCode().getText()));
        txtHizmetyili.setText(StringUtil.nullToString(model.getInv_StartDate()));
        txtYaptiranfirma.setText(StringUtil.nullToString(model.getInv_BuilderFirmId().getText()));
        txtKapasite.setText(StringUtil.convertIntToString(model.getInv_Capacity()));
        txtSinif.setText(StringUtil.nullToString(model.getInv_ClassId().getText()));
        txtAsansorhizi.setText(StringUtil.convertIntToString(model.getInv_Speed()));
        txtAsansormanike.setText(StringUtil.nullToString(model.getInv_Location()));
        txtKatsayisi.setText(StringUtil.convertIntToString(model.getInv_Floor()));
        txtSerino.setText(StringUtil.nullToString(model.getInv_SerialNumber()));
        txtKimlikno.setText(StringUtil.nullToString(model.getInv_ElevatorNumber()));


    }

    public void getElevatorById() {
        Log.d(TAG, "getElevatorById: " + ShareData
                .getInstance().getElevatorId());
        Call<ElevatorGetById> call = jsonApi.elevatorGetById(new ElevatorIdRequest(ShareData
                .getInstance().getElevatorId()));
        call.enqueue(new Callback<ElevatorGetById>() {
            @Override
            public void onResponse(Call<ElevatorGetById> call, Response<ElevatorGetById> response) {
                if (response.isSuccessful()) {

                    ElevatorGetById model = response.body();
                    setTexts(model.getElevator());

                }
            }

            @Override
            public void onFailure(Call<ElevatorGetById> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    private void rcvFirst() {

        btnChangeproductlist.setBackground(first);
        btnWish.setBackground(second);
        btnOmurlu.setBackground(second);
        viewpager.setCurrentItem(0);

    }

    private void rcvSecond() {

        btnWish.setBackground(first);
        btnChangeproductlist.setBackground(second);
        btnOmurlu.setBackground(second);
        viewpager.setCurrentItem(1);


    }

    private void rcvThird() {
        btnOmurlu.setBackground(first);
        btnChangeproductlist.setBackground(second);
        btnWish.setBackground(second);
        viewpager.setCurrentItem(2);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onItemClicked(Object item, int positon) {

    }

    @OnClick({R.id.btn_changeproductlist, R.id.btn_wish, R.id.btn_omurlu, R.id.img_menu,
            R.id.txt_menu_header})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_changeproductlist:
                rcvFirst();
                break;
            case R.id.btn_wish:
                rcvSecond();
                break;
            case R.id.btn_omurlu:
                rcvThird();
                break;
            case R.id.img_menu:
                onBackPressed();
                break;
            case R.id.txt_menu_header:
                onBackPressed();
                break;
        }
    }


    private void setupViewPager() {

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(FragMalzemeGecmisi.newInstance(), "FragMalzemeGecmisi");
        viewPagerAdapter.addFragment(FragServisler.newInstance(), "FragServisler");
        viewPagerAdapter.addFragment(FragOmurluParcalar.newInstance(), "FragOmurluParcalar");
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
                    case 2:
                        rcvThird();
                        break;
                    default:
                        rcvFirst();
                        break;
                }
            }
        });
    }
}
