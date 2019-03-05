package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.Product.MainList;
import com.emrhmrc.isttabletcrm.util.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductMainViewHolder extends BaseViewHolder<MainList,
        OnItemClickListener<MainList>> {

    @BindView(R.id.img_anapic)
    ImageView imgAnapic;
    @BindView(R.id.txt_anatext)
    TextView txtAnatext;
    @BindView(R.id.txt_anacount)
    TextView txtAnacount;
    @BindView(R.id.cons_rcv)
    ConstraintLayout cons;

    public ProductMainViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBind(final MainList item,
                       @Nullable final OnItemClickListener<MainList> listener) {
        GlideBindingAdapters.setImageResourceBase64(imgAnapic, item.getImage());
        txtAnatext.setText(StringUtil.nullToString(item.getInv_MainProductGroupName()));
        txtAnacount.setText(StringUtil.convertIntToString(item.getSubGroupCount()));
        if (listener != null) {
            imgAnapic.setOnClickListener(view -> listener.onItemClicked(item, getAdapterPosition()));
            cons.setOnClickListener(view -> listener.onItemClicked(item, getAdapterPosition()));
        }
    }


}

