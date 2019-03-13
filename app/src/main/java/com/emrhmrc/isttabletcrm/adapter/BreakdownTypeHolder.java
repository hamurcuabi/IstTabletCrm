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

public class BreakdownTypeHolder extends BaseViewHolder<BreakdownType,
        OnItemClickListener<BreakdownType>> implements View.OnClickListener {

    @BindView(R.id.txt_reason)
    TextView txtReason;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindDrawable(R.drawable.btn_selected)
    Drawable btn_selected;
    @BindDrawable(R.drawable.btn_kontrol)
    Drawable btn_default;

    public BreakdownTypeHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBind(final BreakdownType item, @Nullable final OnItemClickListener<BreakdownType> listener) {

        txtReason.setText(String.format("%1$d- %2$s!", (1 + getAdapterPosition()), item.getInv_BreakdownTypeName()));
        if (listener != null) {
            btnAdd.setOnClickListener(view -> listener.onItemClicked(item, getAdapterPosition()));
        }
        // btnAdd.setOnClickListener(this);
        if (!StateHandler.getInstance().getStateList().get(getAdapterPosition()).isState()) {
            btnAdd.setEnabled(true);
            btnAdd.setText("Ekle");
            btnAdd.setBackground(btn_default);

        } else {
            btnAdd.setText("Eklendi");
            btnAdd.setBackground(btn_selected);
            btnAdd.setEnabled(false);
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

