package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.helper.StateHandler;
import com.emrhmrc.isttabletcrm.models.Product.Product;
import com.emrhmrc.isttabletcrm.util.StringUtil;

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
    private Product current;
    private Context context;

    public ProductSubProductViewHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = context;

    }

    @Override
    public void onBind(final Product item, @Nullable final OnItemClickListener<Product> listener) {
        GlideBindingAdapters.setImageResourceBase64(imgPic, item.getImage());
        txtCode.setText(StringUtil.nullToString(item.getProductNumber()));
        txtDescp1.setText(StringUtil.nullToString(item.getName()));
        txtDescp2.setText(StringUtil.nullToString(item.getInv_BillDefinition()));
        if (item.getInv_BrandId() != null)
            txtAnabirim.setText(StringUtil.nullToString(item.getInv_BrandId().getText()));
        if (item.getInv_TypeCode() != null)
            txtSpecialcode4.setText(StringUtil.nullToString(item.getInv_TypeCode().getText()));

        if (!ShareData.getInstance().isAdd_sub_piece()) btnAdd.setVisibility(View.GONE);
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
        current = item;

    }

    @Override
    public void onClick(View view) {
        int adapterPosition = getAdapterPosition();
        if (!StateHandler.getInstance().getStateList().get(getAdapterPosition()).isState()) {
            StateHandler.getInstance().getStateList().get(getAdapterPosition()).setState(true);
            btnAdd.setText("Eklendi");
            btnAdd.setBackground(btn_selected);
            sendItemToAdd(current);

        } else {
            StateHandler.getInstance().getStateList().get(getAdapterPosition()).setState(false);
            btnAdd.setText("Ekle");
            btnAdd.setBackground(btn_default);
            sendItemToDelete(current.getProductId());

        }


    }

    public void sendItemToAdd(Product current) {
        Log.d("sender", "Broadcasting message");
        Intent intent = new Intent("custom-event-name");
        // You can also include some extra data.
        intent.putExtra("product", current);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public void sendItemToDelete(String id) {
        Log.d("sender", "Broadcasting message");
        Intent intent = new Intent("custom-event-name");
        // You can also include some extra data.
        intent.putExtra("id", id);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

}

