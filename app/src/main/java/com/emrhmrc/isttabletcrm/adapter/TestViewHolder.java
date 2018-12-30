package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.Product.Product;

public class TestViewHolder extends BaseViewHolder<Product,
        OnItemClickListener<Product>> {

    private TextView txt_test;

    public TestViewHolder(View itemView) {
        super(itemView);
        txt_test = itemView.findViewById(R.id.txt_test);
    }

    @Override
    public void onBind(final Product item, @Nullable final OnItemClickListener<Product> listener) {
        txt_test.setText(item.getName());

        if (listener != null) {
            //if min sdk >24
            // txt_test.setOnClickListener(v->listener.onItemClicked(item));
            txt_test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(item);
                }
            });

        }
    }


}

