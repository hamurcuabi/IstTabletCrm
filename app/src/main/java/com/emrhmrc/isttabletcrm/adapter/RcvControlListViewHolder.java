package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppModernizationChecklists;
import com.emrhmrc.isttabletcrm.util.StringUtil;

public class RcvControlListViewHolder extends BaseViewHolder<ServAppGetByIdServAppModernizationChecklists,
        OnItemClickListener<ServAppGetByIdServAppModernizationChecklists>> {
    private CheckBox cb_header;
    private RadioGroup radGroup;
    private RadioButton rad_yes, rad_no;
    private TextInputEditText edt_descp, edt_decimal;
    private TextInputLayout til_descp, til_decimal;
    private TextView txt_tarih;

    public RcvControlListViewHolder(View itemView) {
        super(itemView);
        cb_header = itemView.findViewById(R.id.cb_header);
        radGroup = itemView.findViewById(R.id.radGroup);
        rad_yes = itemView.findViewById(R.id.rad_yes);
        rad_no = itemView.findViewById(R.id.rad_no);
        edt_descp = itemView.findViewById(R.id.edt_descp);
        edt_decimal = itemView.findViewById(R.id.edt_decimal);
        til_descp = itemView.findViewById(R.id.til_descp);
        til_decimal = itemView.findViewById(R.id.til_decimal);
        txt_tarih = itemView.findViewById(R.id.txt_tarih);
    }

    @Override
    public void onBind(final ServAppGetByIdServAppModernizationChecklists item, @Nullable final OnItemClickListener<ServAppGetByIdServAppModernizationChecklists> listener) {

        visibilty(item);
        if (listener != null) {
            txt_tarih.setOnClickListener(view -> listener.onItemClicked(item, getAdapterPosition()));
        }
        txt_tarih.setText(StringUtil.nullToString(item.getInv_Date()));
        if (item.getInv_ChecklistDetailid() != null)
            cb_header.setText(StringUtil.nullToString(item.getInv_ChecklistDetailid().getText()));


    }

    private void visibilty(ServAppGetByIdServAppModernizationChecklists item) {
        if (item.getIs_modernization()) {
            txt_tarih.setVisibility(View.VISIBLE);
            til_decimal.setVisibility(View.GONE);
            rad_no.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b) til_descp.setVisibility(View.VISIBLE);
                else til_descp.setVisibility(View.GONE);

            });
            rad_no.setSelected(true);

        } else {
            txt_tarih.setVisibility(View.GONE);
            til_descp.setVisibility(View.GONE);
            rad_yes.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b) til_decimal.setVisibility(View.VISIBLE);
                else til_decimal.setVisibility(View.GONE);
            });
            rad_yes.setSelected(true);

        }
    }


}

