package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppDetails;
import com.emrhmrc.isttabletcrm.util.StringUtil;

public class RcvServAppDetailViewHolder extends BaseViewHolder<ServAppGetByIdServAppDetails,
        OnItemClickListener<ServAppGetByIdServAppDetails>> {
    private TextView txt_descp, txt_garantidurumu, txt_stokdurumu, txt_birim,
            edt_miktar, txt_urunadi, txt_kod;
    private Spinner spn_ucretli;
    private Context context;

    public RcvServAppDetailViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        spn_ucretli = itemView.findViewById(R.id.spn_ucretli);
        txt_descp = itemView.findViewById(R.id.txt_descp);
        txt_garantidurumu = itemView.findViewById(R.id.txt_garantidurumu);
        txt_stokdurumu = itemView.findViewById(R.id.txt_stokdurumu);
        txt_birim = itemView.findViewById(R.id.txt_birim);
        edt_miktar = itemView.findViewById(R.id.edt_miktar);
        txt_urunadi = itemView.findViewById(R.id.txt_urunadi);
        txt_kod = itemView.findViewById(R.id.txt_kod);
    }

    @Override
    public void onBind(ServAppGetByIdServAppDetails item,
                       @Nullable OnItemClickListener<ServAppGetByIdServAppDetails> listener) {
        if (item.getInv_WarrantyStatusCode() != null)
            txt_garantidurumu.setText(item.getInv_WarrantyStatusCode().getText());
        if (item.getInv_Uomid() != null)
            txt_birim.setText(item.getInv_Uomid().getText());
        edt_miktar.setText(String.valueOf(item.getInv_Quantity()));
        edt_miktar.setEnabled(false);
        if (item.getInv_ProductId() != null)
            txt_urunadi.setText(item.getInv_ProductId().getText());
        if (item.getInv_LineNo() != null)
            txt_kod.setText(item.getInv_LineNo().toString());
        if (item.isManuel()) {
            edt_miktar.setEnabled(true);
            edt_miktar.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (!editable.toString().isEmpty())
                        item.setInv_Quantity(Integer.parseInt(editable.toString()));
                }
            });
        }
        txt_descp.setText(StringUtil.nullToString(item.getInv_Description()));


    }

}
