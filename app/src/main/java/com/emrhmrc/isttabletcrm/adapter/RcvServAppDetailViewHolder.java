package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppDetails;

public class RcvServAppDetailViewHolder extends BaseViewHolder<ServAppGetByIdServAppDetails,
        OnItemClickListener<ServAppGetByIdServAppDetails>> {
    private TextView txt_urundahilimi, txt_fatura, txt_garantidurumu, txt_stokdurumu, txt_birim,
            txt_miktar, txt_urunadi, txt_kod;

    public RcvServAppDetailViewHolder(View itemView) {
        super(itemView);
        txt_urundahilimi = itemView.findViewById(R.id.txt_urundahilimi);
        txt_fatura = itemView.findViewById(R.id.txt_fatura);
        txt_garantidurumu = itemView.findViewById(R.id.txt_garantidurumu);
        txt_stokdurumu = itemView.findViewById(R.id.txt_stokdurumu);
        txt_birim = itemView.findViewById(R.id.txt_birim);
        txt_miktar = itemView.findViewById(R.id.txt_miktar);
        txt_urunadi = itemView.findViewById(R.id.txt_urunadi);
        txt_kod = itemView.findViewById(R.id.txt_kod);
    }

    @Override
    public void onBind(ServAppGetByIdServAppDetails item, @Nullable OnItemClickListener<ServAppGetByIdServAppDetails> listener) {

        //txt_urundahilimi.setText();
        txt_fatura.setText(""+item.getInv_WillBeBilled());
        txt_garantidurumu.setText(item.getInv_WarrantyStatusCode().getText());
       // txt_stokdurumu.setText();
        txt_birim.setText(item.getInv_Uomid().getText());
        txt_miktar.setText(item.getInv_Quantity());
        txt_urunadi.setText(item.getInv_ProductId().getText());
        txt_kod.setText(item.getInv_LineNo().toString());

    }



}
