package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorChangingParts;
import com.emrhmrc.isttabletcrm.models.Warehouse.WarehouseItem;
import com.emrhmrc.isttabletcrm.util.StringUtil;

public class RcvChangingPartViewHolder extends BaseViewHolder<ElevatorChangingParts,
        OnItemClickListener<ElevatorChangingParts>> {

    private TextView txt_malzemekodu,txt_malzeme,txt_degisimtarih,txt_ustaadi,txt_baslangictarih,
    txt_bitistarih,txt_sure,txt_serino;


    public RcvChangingPartViewHolder(View itemView) {
        super(itemView);
        txt_malzemekodu = itemView.findViewById(R.id.txt_malzemekodu);
        txt_malzeme = itemView.findViewById(R.id.txt_malzeme);
        txt_degisimtarih = itemView.findViewById(R.id.txt_degisimtarih);
        txt_ustaadi = itemView.findViewById(R.id.txt_ustaadi);
        txt_baslangictarih = itemView.findViewById(R.id.txt_baslangictarih);
        txt_bitistarih = itemView.findViewById(R.id.txt_bitistarih);
        txt_sure = itemView.findViewById(R.id.txt_sure);
        txt_serino = itemView.findViewById(R.id.txt_serino);

    }

    @Override
    public void onBind(final ElevatorChangingParts item,
                       @Nullable final OnItemClickListener<ElevatorChangingParts> listener) {
        txt_malzemekodu.setText(StringUtil.nullToString(item.getPrdocutCode()));
        txt_baslangictarih.setText(StringUtil.nullToString(item.getWarrantyStartDate()));
        txt_bitistarih.setText(StringUtil.nullToString(item.getWarrantyEndDate()));
        txt_sure.setText(StringUtil.convertIntToString(item.getWarrantyMonthCount()));

    }


}

