package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.Elevator.PeriodicalProducts;
import com.emrhmrc.isttabletcrm.util.StringUtil;

public class RcvElevatorOmurluViewHolder extends BaseViewHolder<PeriodicalProducts,
        OnItemClickListener<PeriodicalProducts>> {

    private TextView txt_productnumber, txt_name, txt_main, txt_sub, txt_remain;


    public RcvElevatorOmurluViewHolder(View itemView) {
        super(itemView);
        txt_productnumber = itemView.findViewById(R.id.txt_productnumber);
        txt_name = itemView.findViewById(R.id.txt_name);
        txt_main = itemView.findViewById(R.id.txt_main);
        txt_sub = itemView.findViewById(R.id.txt_sub);
        txt_remain = itemView.findViewById(R.id.txt_remain);


    }

    @Override
    public void onBind(final PeriodicalProducts item,
                       @Nullable final OnItemClickListener<PeriodicalProducts> listener) {

        txt_productnumber.setText(StringUtil.nullToString(item.getProductNumber()));
        if (item.getInv_MainGroupId() != null)
            txt_main.setText(StringUtil.nullToString(item.getInv_MainGroupId().getText()));
        else txt_main.setText(StringUtil.notExist());
        if (item.getInv_SubGroupId() != null)
            txt_sub.setText(StringUtil.nullToString(item.getInv_SubGroupId().getText()));
        else txt_sub.setText(StringUtil.notExist());
        if (item.getRemainingDays() != null)
            txt_remain.setText(StringUtil.convertIntToString(item.getRemainingDays()));
        else txt_remain.setText(StringUtil.notExist());


    }


}

