package com.emrhmrc.isttabletcrm.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.Product.Product;
import com.emrhmrc.isttabletcrm.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductSubProductViewHolder extends BaseViewHolder<Product,
        OnItemClickListener<Product>> {
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


    List<Boolean> list_btn_enabled;

    public ProductSubProductViewHolder(View itemView, int count) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        list_btn_enabled = new ArrayList<>();
        list_btn_enabled.clear();
        for (int i = 0; i < count; i++) {
            Boolean b = new Boolean(false);
            list_btn_enabled.add(b);
        }

    }

    @Override
    public void onBind(final Product item, @Nullable final OnItemClickListener<Product> listener) {
        final int k = getAdapterPosition();
        GlideBindingAdapters.setImageResource(imgPic, item.getImage());
        txtCode.setText(StringUtil.nullToString(item.getProductNumber()));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                list_btn_enabled.set(k, !list_btn_enabled.get(k));
                if (list_btn_enabled.get(k)) {
                    btnAdd.setText("Eklendi");
                    btnAdd.setBackground(btn_selected);
                } else {
                    btnAdd.setText("Ekle");
                    btnAdd.setBackground(btn_default);
                }


            }
        });
        if (list_btn_enabled.get(k)) {
            btnAdd.setText("Eklendi");
            btnAdd.setBackground(btn_selected);
        } else {
            btnAdd.setText("Ekle");
            btnAdd.setBackground(btn_default);
        }

        if (listener != null) {
            imgPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(item);
                }
            });
        }
    }


}

