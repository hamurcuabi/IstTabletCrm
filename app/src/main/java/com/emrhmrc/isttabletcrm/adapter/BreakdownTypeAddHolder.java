package com.emrhmrc.isttabletcrm.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.helper.StateHandler;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakdownType;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BreakdownTypeAddHolder extends BaseViewHolder<BreakdownType,
        OnItemClickListener<BreakdownType>>  {

    @BindView(R.id.txt_reason)
    TextView txtReason;
    @BindView(R.id.btn_add)
    Button btnAdd;

    public BreakdownTypeAddHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBind(final BreakdownType item, @Nullable final OnItemClickListener<BreakdownType> listener) {

        txtReason.setText(String.format("%1$d- %2$s!", (1 + getAdapterPosition()), item.getInv_BreakdownTypeName()));
        if (listener != null) {
            btnAdd.setOnClickListener(view -> listener.onItemClicked(item, getAdapterPosition()));
        }


    }


}

