package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.helper.StateHandler;
import com.emrhmrc.isttabletcrm.models.Product.Product;
import com.emrhmrc.isttabletcrm.util.StringUtil;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductViewHolder extends BaseViewHolder<Product,
        OnItemClickListener<Product>> implements View.OnClickListener {

    @BindView(R.id.txt_count)
    TextView txt_count;
    @BindView(R.id.txt_code)
    TextView txt_code;
    @BindView(R.id.txt_descp)
    TextView txt_descp;
    @BindView(R.id.btn_add)
    Button btn_add;
    @BindDrawable(R.drawable.btn_selected)
    Drawable btn_selected;
    @BindDrawable(R.drawable.btn_kontrol)
    Drawable btn_default;
    private Product current;
    private Context context;

    public ProductViewHolder(View itemView,Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context=context;

    }

    @Override
    public void onBind(final Product item, @Nullable final OnItemClickListener<Product> listener) {

        txt_count.setText(StringUtil.convertIntToString(getAdapterPosition() + 1) + "-");
        txt_code.setText(StringUtil.nullToString(item.getProductNumber()));
        txt_descp.setText(StringUtil.nullToString(item.getName()));
        btn_add.setOnClickListener(this);

        if (!StateHandler.getInstance().getStateList().get(getAdapterPosition()).isState()) {
            btn_add.setText("Ekle");
            btn_add.setBackground(btn_default);

        } else {
            btn_add.setText("Eklendi");
            btn_add.setBackground(btn_selected);
        }
        current = item;
    }


    @Override
    public void onClick(View view) {
        int adapterPosition = getAdapterPosition();
        if (!StateHandler.getInstance().getStateList().get(getAdapterPosition()).isState()) {
            StateHandler.getInstance().getStateList().get(getAdapterPosition()).setState(true);
            btn_add.setText("Eklendi");
            btn_add.setBackground(btn_selected);
            sendItemToAdd(current);

        } else {
            StateHandler.getInstance().getStateList().get(getAdapterPosition()).setState(false);
            btn_add.setText("Ekle");
            btn_add.setBackground(btn_default);
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

