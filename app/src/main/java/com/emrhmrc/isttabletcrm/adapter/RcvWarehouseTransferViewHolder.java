package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.helper.Methodes;
import com.emrhmrc.isttabletcrm.models.Warehouse.WarehouseTransferItem;
import com.emrhmrc.isttabletcrm.util.StringUtil;

public class RcvWarehouseTransferViewHolder extends BaseViewHolder<WarehouseTransferItem,
        OnItemClickListener<WarehouseTransferItem>> {

    private TextView txt_tranfertipi, txt_urunadi, txt_miktar, txt_birim, txt_tarih,
            txt_onay_durumu, txt_depo_cikis, txt_depo_gonderilecek;


    public RcvWarehouseTransferViewHolder(View itemView) {
        super(itemView);
        txt_tranfertipi = itemView.findViewById(R.id.txt_tranfertipi);
        txt_urunadi = itemView.findViewById(R.id.txt_urunadi);
        txt_miktar = itemView.findViewById(R.id.txt_miktar);
        txt_birim = itemView.findViewById(R.id.txt_birim);
        txt_tarih = itemView.findViewById(R.id.txt_tarih);
        txt_onay_durumu = itemView.findViewById(R.id.txt_onay_durumu);
        txt_depo_cikis = itemView.findViewById(R.id.txt_depo_cikis);
        txt_depo_gonderilecek = itemView.findViewById(R.id.txt_depo_gonderilecek);

    }

    @Override
    public void onBind(final WarehouseTransferItem item,
                       @Nullable final OnItemClickListener<WarehouseTransferItem> listener) {
        txt_tranfertipi.setText(StringUtil.nullToString(item.getInv_WarehouseTransferName()));
        txt_urunadi.setText(StringUtil.nullToString(item.getInv_Productid().getText()));
        txt_miktar.setText(StringUtil.convertIntToString(item.getInv_Quantity()));
        txt_birim.setText(StringUtil.nullToString(item.getInv_Uomid().getText()));
        txt_tarih.setText(Methodes.changeDateFormatToText(StringUtil.nullToString(item.getInv_RequestDate())));
        txt_onay_durumu.setText(StringUtil.nullToString(item.getStatusCode().getText()));
        txt_depo_cikis.setText(StringUtil.nullToString(item.getInv_FromWarehouseid().getText()));
        txt_depo_gonderilecek.setText(StringUtil.nullToString(item.getInv_ToWarehouseid().getText()));

    }


}

