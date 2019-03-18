package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
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
    private ImageView img_statu;


    public RcvWarehouseTransferViewHolder(View itemView) {
        super(itemView);
        txt_tranfertipi = itemView.findViewById(R.id.txt_tranfertipi);
        img_statu = itemView.findViewById(R.id.img_statu);
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
        if (item.getInv_WarehouseTransferName() != null)
            txt_tranfertipi.setText(StringUtil.nullToString(item.getInv_WarehouseTransferName()));
        if (item.getInv_Productid() != null)
            txt_urunadi.setText(StringUtil.nullToString(item.getInv_Productid().getText()));
        txt_miktar.setText(StringUtil.convertIntToString(item.getInv_Quantity()));
        if (item.getInv_Uomid() != null)
            txt_birim.setText(StringUtil.nullToString(item.getInv_Uomid().getText()));
        if (item.getInv_RequestDate() != null)
            txt_tarih.setText(Methodes.changeDateFormatToText(StringUtil.nullToString(item.getInv_RequestDate())));
        if (item.getStatusCode() != null) {
            txt_onay_durumu.setText(StringUtil.nullToString(item.getStatusCode().getText()));
            GlideBindingAdapters.setStatusTransfer(img_statu, item.getStatusCode().getValue());
        }
        if (item.getInv_FromWarehouseid() != null)
            txt_depo_cikis.setText(StringUtil.nullToString(item.getInv_FromWarehouseid().getText()));
        if (item.getInv_ToWarehouseid() != null)
            txt_depo_gonderilecek.setText(StringUtil.nullToString(item.getInv_ToWarehouseid().getText()));

    }


}

