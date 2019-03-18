package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdNotes;
import com.emrhmrc.isttabletcrm.util.StringUtil;

public class ServAppDescriptionViewHolder extends BaseViewHolder<ServAppGetByIdNotes,
        OnItemClickListener<ServAppGetByIdNotes>> {

    private TextView txt_descp, txt_owner, txt_statu;

    public ServAppDescriptionViewHolder(View itemView) {
        super(itemView);
        txt_descp = itemView.findViewById(R.id.txt_descp);
        txt_owner = itemView.findViewById(R.id.txt_owner);
        txt_statu = itemView.findViewById(R.id.txt_statu);
    }

    @Override
    public void onBind(final ServAppGetByIdNotes item, @Nullable final OnItemClickListener<ServAppGetByIdNotes> listener) {
        txt_descp.setText(StringUtil.nullToString(item.getSubject()));


    }


}

