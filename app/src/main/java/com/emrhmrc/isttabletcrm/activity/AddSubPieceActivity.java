package com.emrhmrc.isttabletcrm.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
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

import com.emrhmrc.isttabletcrm.R;
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

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSubPieceActivity extends AppCompatActivity implements View.OnClickListener,
        OnItemClickListener, SlideInterface {

    private static final String TAG = "AddSubPieceActivity";
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_piece);
        ButterKnife.bind(this);
        init();
        getSubProduct();
    }

    private void getSubProduct() {
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
            }

            @Override
            public void onFailure(Call<SubProductList> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void getSubProductProduct(String id) {

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
                    search_sub();

                }
            }

            @Override
            public void onFailure(Call<ProductListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    private void init() {
        search_main = findViewById(R.id.search_main);
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
        adapter_sub = new RcvProductSubProductAdapter(getApplicationContext(), this);
        adapter_sub.setListener(this);
        rcv_main.setAdapter(adapter_main);
        rcv_sub.setAdapter(adapter_sub);


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
        else if (position > productList.size()-1) openSilder(productList.get(productList.size() - 1),
                productList.size() - 1);
        else openSilder(productList.get(position), position);
    }
}
