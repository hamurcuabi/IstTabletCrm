package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppDetails;
import com.emrhmrc.isttabletcrm.util.StringUtil;

public class RcvServAppDetailViewHolder extends BaseViewHolder<ServAppGetByIdServAppDetails,
        OnItemClickListener<ServAppGetByIdServAppDetails>> {
    private TextView txt_garantidurumu, txt_stokdurumu,
            edt_miktar, txt_kod;
    private Spinner spn_ucretli;
    private EditText edt_descp, edt_fiyat, edt_urunadi, edt_birim;
    private Context context;

    public RcvServAppDetailViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        spn_ucretli = itemView.findViewById(R.id.spn_ucretli);
        edt_descp = itemView.findViewById(R.id.txt_descp);
        edt_fiyat = itemView.findViewById(R.id.edt_fiyat);
        txt_garantidurumu = itemView.findViewById(R.id.txt_garantidurumu);
        txt_stokdurumu = itemView.findViewById(R.id.txt_stokdurumu);
        edt_birim = itemView.findViewById(R.id.txt_birim);
        edt_miktar = itemView.findViewById(R.id.edt_miktar);
        edt_urunadi = itemView.findViewById(R.id.edt_urunadi);
        txt_kod = itemView.findViewById(R.id.txt_kod);
    }

    @Override
    public void onBind(ServAppGetByIdServAppDetails item,
                       @Nullable OnItemClickListener<ServAppGetByIdServAppDetails> listener) {
        if (item.getInv_WarrantyStatusCode() != null)
            txt_garantidurumu.setText(item.getInv_WarrantyStatusCode().getText());
        if (item.getInv_Uomid() != null)
            edt_birim.setText(item.getInv_Uomid().getText());
        edt_miktar.setText(String.valueOf(item.getInv_Quantity()));
        if (item.getInv_ProductId() != null)
            edt_urunadi.setText(item.getInv_ProductId().getText());
        if (item.getInv_LineNo() != null)
            txt_kod.setText(item.getInv_LineNo().toString());
        ruleManuelAdd(item);
        edt_descp.setText(StringUtil.nullToString(item.getInv_Description()));
        saveChanges(item);

    }

    private void saveChanges(ServAppGetByIdServAppDetails item) {
        edt_descp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                item.setInv_Description(editable.toString());
            }
        });
        edt_miktar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable)) {
                    item.setInv_Quantity(StringUtil.convertStringToınt(editable.toString()));
                }

            }
        });

    }

    private void ruleManuelAdd(ServAppGetByIdServAppDetails item) {

        if (item.isManuelProduct()) {
            edt_urunadi.setEnabled(true);

        } else edt_urunadi.setEnabled(false);
        if (item.isManuel() || item.isManuelProduct()) {
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
                        item.setInv_Quantity(Float.parseFloat(editable.toString()));
                }
            });
        } else edt_miktar.setEnabled(false);
        if (item.isManuelProduct()) {
            edt_birim.setEnabled(false);
        } else edt_birim.setEnabled(false);
        if (item.isManuelProduct()) {
            spn_ucretli.setEnabled(false);
        } else spn_ucretli.setEnabled(true);
        if (item.isManuel()) {
            spn_ucretli.setEnabled(true);
        } else spn_ucretli.setEnabled(false);
        if (item.isManuelProduct()) {
            edt_fiyat.setEnabled(true);
        } else edt_fiyat.setEnabled(false);

    }

}
