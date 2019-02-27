package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.Warehouse.WarehouseItem;
import com.emrhmrc.isttabletcrm.util.StringUtil;

public class RcvWarehouselViewHolder extends BaseViewHolder<WarehouseItem,
        OnItemClickListener<WarehouseItem>> {

    private TextView txt_depo, txt_urunadi, txt_miktar, txt_birim;


    public RcvWarehouselViewHolder(View itemView) {
        super(itemView);
        txt_depo = itemView.findViewById(R.id.txt_depo);
        txt_urunadi = itemView.findViewById(R.id.txt_urunadi);
        txt_miktar = itemView.findViewById(R.id.txt_miktar);
        txt_birim = itemView.findViewById(R.id.txt_birim);

    }

    @Override
    public void onBind(final WarehouseItem item,
                       @Nullable final OnItemClickListener<WarehouseItem> listener) {
        if (item.getInv_Uomid() != null)
            txt_birim.setText(StringUtil.nullToString(item.getInv_Uomid().getText()));
        txt_miktar.setText(StringUtil.convertIntToString(item.getInv_Quantity()));
        txt_depo.setText(StringUtil.nullToString(item.getInv_WarehouseTypeCode().getText()));
        txt_urunadi.setText(StringUtil.nullToString(item.getInv_Productid().getText()));
    }


}

