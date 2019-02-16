package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.Document.TechnicDocument;
import com.emrhmrc.isttabletcrm.util.StringUtil;

public class RcvTechnicalViewHolder extends BaseViewHolder<TechnicDocument,
        OnItemClickListener<TechnicDocument>> {

    private TextView txt_baslik, txt_descp;
    private Button btn_look;

    public RcvTechnicalViewHolder(View itemView) {
        super(itemView);
        txt_baslik = itemView.findViewById(R.id.txt_baslik);
        txt_descp = itemView.findViewById(R.id.txt_descp);
        btn_look = itemView.findViewById(R.id.btn_look);
    }

    @Override
    public void onBind(final TechnicDocument item,
                       @Nullable final OnItemClickListener<TechnicDocument> listener) {
        txt_baslik.setText(StringUtil.nullToString(item.getInv_TechnicalDocumentName()));
        txt_descp.setText(StringUtil.nullToString(item.getInv_Keywords()));

        if (listener != null) {
            btn_look.setOnClickListener(view -> listener.onItemClicked(item, getAdapterPosition()));

        }
    }


}

