package com.emrhmrc.isttabletcrm.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.fragment.ImageSliderFragment;
import com.emrhmrc.isttabletcrm.helper.SlideInterface;
import com.emrhmrc.isttabletcrm.helper.StateHandler;
import com.emrhmrc.isttabletcrm.models.Product.Product;
import com.emrhmrc.isttabletcrm.util.StringUtil;

import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductSubProductViewHolder extends BaseViewHolder<Product,
        OnItemClickListener<Product>> implements View.OnClickListener {
    private static final String TAG = "ProductSubProductViewHo";
    @BindView(R.id.img_pic)
    ImageView imgPic;
    @BindView(R.id.txt_code)
    TextView txtCode;
    @BindView(R.id.txt_descp1)
    TextView txtDescp1;
    @BindView(R.id.txt_descp2)
    TextView txtDescp2;
    @BindView(R.id.txt_anabirim)
    TextView txtAnabirim;
    @BindView(R.id.txt_specialcode)
    TextView txtSpecialcode;
    @BindView(R.id.txt_specialcode4)
    TextView txtSpecialcode4;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindDrawable(R.drawable.btn_selected)
    Drawable btn_selected;
    @BindDrawable(R.drawable.btn_kontrol)
    Drawable btn_default;
    private FragmentManager fragmentManager;
    private List<Product> list;


    public ProductSubProductViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.fragmentManager = fragmentManager;
        this.list = list;
    }


    @Override
    public void onBind(final Product item, @Nullable final OnItemClickListener<Product> listener) {
        GlideBindingAdapters.setImageResourceBase64(imgPic, item.getImage());
        txtCode.setText(StringUtil.nullToString(item.getProductNumber()));
        if (listener != null)
            imgPic.setOnClickListener(view -> listener.onItemClicked(item, getAdapterPosition()));
        btnAdd.setOnClickListener(this);

        if (!StateHandler.getInstance().getStateList().get(getAdapterPosition()).isState()) {
            btnAdd.setText("Ekle");
            btnAdd.setBackground(btn_default);
        } else {
            btnAdd.setText("Eklendi");
            btnAdd.setBackground(btn_selected);
        }

    }

    @Override
    public void onClick(View view) {
        int adapterPosition = getAdapterPosition();
        if (!StateHandler.getInstance().getStateList().get(getAdapterPosition()).isState()) {
            StateHandler.getInstance().getStateList().get(getAdapterPosition()).setState(true);
            btnAdd.setText("Eklendi");
            btnAdd.setBackground(btn_selected);

        } else {
            StateHandler.getInstance().getStateList().get(getAdapterPosition()).setState(false);
            btnAdd.setText("Ekle");
            btnAdd.setBackground(btn_default);

        }


    }

}

