package com.emrhmrc.isttabletcrm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.SweetDialog.AnyDialog;
import com.emrhmrc.isttabletcrm.SweetDialog.SweetAlertDialog;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvProductSubAdapter;
import com.emrhmrc.isttabletcrm.adapter.RcvProductSubProductAdapter;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.fragment.ImageSliderFragment;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.helper.SlideInterface;
import com.emrhmrc.isttabletcrm.models.Product.Product;
import com.emrhmrc.isttabletcrm.models.Product.ProductListAll;
import com.emrhmrc.isttabletcrm.models.Product.SubGroupProductsRequest;
import com.emrhmrc.isttabletcrm.models.Product.SubGroupRequest;
import com.emrhmrc.isttabletcrm.models.Product.SubList;
import com.emrhmrc.isttabletcrm.models.Product.SubProductList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSubPieceActivity extends AppCompatActivity implements View.OnClickListener,
        OnItemClickListener, SlideInterface {

    private static final String TAG = "AddSubPieceActivity";
    @BindView(R.id.textView6)
    TextView txtHeader;
    @BindView(R.id.txt_count_item)
    TextView txt_count;
    private LinearLayout lnr_main, lnr_sub;
    private Animation anim_down, anim_down_2, anim_up, anim_up_2;
    private ConstraintLayout cons_main, cons_sub;
    private boolean ismain_shown, issub_shown;
    private SearchView search_main, search_sub;
    private RecyclerView rcv_main, rcv_sub;
    private JsonApi jsonApi;
    private RcvProductSubAdapter adapter_main;
    private RcvProductSubProductAdapter adapter_sub;
    private List<Product> productList;
    private int last_img;
    private ImageSliderFragment fragment;
    private TextView txt_maingroup_name, txt_maingroup_id;
    private int main_background = -1;
    private SweetAlertDialog dialog, dialog2;
    private  int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_piece);
        ButterKnife.bind(this);
        init();
        getSubProduct();
        AnimateDownMain();
        if (!ShareData.getInstance().isAdd_sub_piece()) {
            txtHeader.setText(getResources().getString(R.string.alt_urun));

        }
    }

    private void getSubProduct() {
        AnyDialog anyDialog = new AnyDialog(this);
        dialog = anyDialog.loading(getResources().getString(R.string.loading));
        dialog.show();
        SubGroupRequest request = new SubGroupRequest(ShareData.getInstance().getProductMainId());
        Call<SubProductList> call = jsonApi.getSubProductListCall(request);
        call.enqueue(new Callback<SubProductList>() {
            @Override
            public void onResponse(Call<SubProductList> call, Response<SubProductList> response) {
                if (response.isSuccessful()) {
                    final SubProductList list = response.body();
                    adapter_main.setItems(list.getSubProductGroups());
                    adapter_main.setItemsFilter(list.getSubProductGroups());
                    search_main();
                } else {
                    Log.d(TAG, "onResponse: ");
                }
                dialog.dismissWithAnimation();
            }

            @Override
            public void onFailure(Call<SubProductList> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                dialog.dismissWithAnimation();
            }
        });
    }

    private void getSubProductProduct(String id) {
        Log.d(TAG, "getSubProductProduct: " + id);
        AnyDialog anyDialog2 = new AnyDialog(this);
        dialog2 = anyDialog2.loading(getResources().getString(R.string.loading));
        dialog2.show();
        SubGroupProductsRequest request = new SubGroupProductsRequest(id);
        Call<ProductListAll> call = jsonApi.productListAll(request);
        call.enqueue(new Callback<ProductListAll>() {
            @Override
            public void onResponse(Call<ProductListAll> call, Response<ProductListAll> response) {
                if (response.isSuccessful()) {

                    ProductListAll temp = response.body();
                    productList = temp.getProducts();
                    adapter_sub.setItems(temp.getProducts());
                    adapter_sub.setItemsFilter(temp.getProducts());
                    txt_count.setText(productList.size() + " Adet Ürün");
                    search_sub();

                }
                dialog2.dismissWithAnimation();
            }

            @Override
            public void onFailure(Call<ProductListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                dialog2.dismissWithAnimation();
            }
        });

    }

    private void init() {
        search_main = findViewById(R.id.search_main);
        txt_maingroup_name = findViewById(R.id.txt_maingroup_name);
        txt_maingroup_id = findViewById(R.id.txt_maingroup_id);
        search_sub = findViewById(R.id.search_sub);
        rcv_main = findViewById(R.id.rcw_servapp);
        rcv_sub = findViewById(R.id.rcw_servapp2);
        ismain_shown = false;
        issub_shown = false;
        lnr_main = findViewById(R.id.lnr_main);
        lnr_sub = findViewById(R.id.lnr_sub);
        cons_main = findViewById(R.id.cons_main);
        cons_sub = findViewById(R.id.cons_sub);
        anim_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        anim_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        anim_up_2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        anim_down_2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        cons_main.setOnClickListener(this);
        cons_sub.setOnClickListener(this);
        anim_up.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (animation == anim_up)
                    lnr_main.setVisibility(View.GONE);
                else if (animation == anim_up_2) lnr_sub.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
//Rcv
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        rcv_main.setHasFixedSize(true);
        rcv_sub.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        rcv_main.setLayoutManager(mLayoutManager);
        rcv_sub.setLayoutManager(new LinearLayoutManager(this));
        adapter_main = new RcvProductSubAdapter(getApplicationContext(), this);
        adapter_main.setListener(this);
        adapter_sub = new RcvProductSubProductAdapter(this, this);
        adapter_sub.setListener(this);
        rcv_main.setAdapter(adapter_main);
        rcv_sub.setAdapter(adapter_sub);
        txt_maingroup_name.setText(ShareData.getInstance().getProductMainName());


    }

    private void search_main() {
        search_main.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter_main.getFilter().filter(s);
                return false;
            }
        });
    }

    private void search_sub() {
        search_sub.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter_sub.getFilter().filter(s);
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.cons_main:
                AnimateDownMain();
                break;
            case R.id.cons_sub:
                AnimateDownSub();
                break;
        }
    }

    private void AnimateDownMain() {
        if (!ismain_shown) {
            lnr_main.setVisibility(View.VISIBLE);
            lnr_main.startAnimation(anim_down);
            ismain_shown = true;

            lnr_sub.startAnimation(anim_up_2);
            issub_shown = false;

        } else {
            lnr_main.startAnimation(anim_up);
            ismain_shown = false;
        }


    }

    private void AnimateDownSub() {
        if (!issub_shown) {
            lnr_sub.setVisibility(View.VISIBLE);
            lnr_sub.startAnimation(anim_down_2);
            issub_shown = true;
            lnr_main.startAnimation(anim_up);
            ismain_shown = false;
        } else {
            lnr_sub.startAnimation(anim_up_2);
            issub_shown = false;
        }

    }

    @Override
    public void onItemClicked(Object item, int positon) {
        if (item instanceof SubList) {
            if (main_background == -1) main_background = positon;
            else {
                adapter_main.notifyItemChanged(main_background,
                        (SubList) item);
                main_background = positon;
            }
            final SubList subList = (SubList) item;
            getSubProductProduct(subList.getInv_SubProductGroupid());
            AnimateDownSub();
        } else if (item instanceof Product) {
            final Product product = (Product) item;
            last_img = positon;
            openSilder(product, last_img);

            //  AnimateDownMain();
        } else {
            Log.d(TAG, "onItemClicked: Faill");
        }

    }

    private void openSilder(Product product, int position) {
        fragment = ImageSliderFragment.newInstance(product, position);
        fragment.show(getSupportFragmentManager(), "slider");
    }

    @OnClick({R.id.img_back, R.id.img_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                super.onBackPressed();
                break;
            case R.id.img_close:
                super.onBackPressed();
                break;
        }
    }

    @Override
    public void slideTo(int position) {
        if (position < 0) openSilder(productList.get(0), 0);
        else if (position > productList.size() - 1)
            openSilder(productList.get(productList.size() - 1),
                    productList.size() - 1);
        else openSilder(productList.get(position), position);
    }

    public void sendMessage(Product product) {
        Log.d("sender", "Broadcasting message");
        Intent intent = new Intent("custom-event-name");
        // You can also include some extra data.
        intent.putExtra("message", "This is my message!");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public void changeTextCountUp() {
        i++;
      //  txt_count.setText(i + " Adet Ürün");
    }

    public void changeTextCountDown() {
        i--;
       // txt_count.setText(i + " Adet Ürün");
    }
}
