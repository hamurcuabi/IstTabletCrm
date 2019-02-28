package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.Product.SubList;
import com.emrhmrc.isttabletcrm.util.StringUtil;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductSubViewHolder extends BaseViewHolder<SubList,
        OnItemClickListener<SubList>> implements View.OnClickListener {

    @BindView(R.id.img_anapic)
    ImageView imgAnapic;
    @BindView(R.id.txt_anatext)
    TextView txtAnatext;
    @BindView(R.id.txt_anacount)
    TextView txtAnacount;
    @BindView(R.id.cons_rcv)
    ConstraintLayout cons_bg;
    @BindColor(R.color.cons_bg)
    int bg;
    @BindColor(R.color.rcv_back)
    int bg_default;
    @BindColor(R.color.white)
    int white;
    @BindColor(R.color.border)
    int defaulttext;
    @BindColor(R.color.kontrol_listesi)
    int defaulttext2;

    public ProductSubViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    @Override
    public void onBind(final SubList item, @Nullable final OnItemClickListener<SubList> listener) {
        GlideBindingAdapters.setImageResourceBase64(imgAnapic, item.getImage());
        txtAnatext.setText(StringUtil.nullToString(item.getInv_SubProductGroupName()));
        txtAnacount.setText(StringUtil.convertIntToString(item.getProductCount()));
        cons_bg.setBackgroundColor(bg_default);
        txtAnacount.setTextColor(defaulttext2);
        txtAnatext.setTextColor(defaulttext);
        if (listener != null) {
            imgAnapic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(item, getAdapterPosition());
                    cons_bg.setBackgroundColor(bg);
                    txtAnacount.setTextColor(white);
                    txtAnatext.setTextColor(white);
                }
            });

        }
    }


    @Override
    public void onClick(View view) {

    }
}

