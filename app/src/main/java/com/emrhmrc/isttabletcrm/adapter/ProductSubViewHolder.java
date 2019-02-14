package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.Product.MainList;
import com.emrhmrc.isttabletcrm.models.Product.SubList;
import com.emrhmrc.isttabletcrm.util.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductSubViewHolder extends BaseViewHolder<SubList,
        OnItemClickListener<SubList>> {

    @BindView(R.id.img_anapic)
    ImageView imgAnapic;
    @BindView(R.id.txt_anatext)
    TextView txtAnatext;
    @BindView(R.id.txt_anacount)
    TextView txtAnacount;

    public ProductSubViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    @Override
    public void onBind(final SubList item, @Nullable final OnItemClickListener<SubList> listener) {
        GlideBindingAdapters.setImageResource(imgAnapic, item.getImage());
        txtAnatext.setText(StringUtil.nullToString(item.getInv_SubProductGroupName()));
        txtAnacount.setText(StringUtil.convertIntToString(item.getProductCount()));
        if (listener != null) {
            imgAnapic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(item);
                }
            });
        }
    }



}

