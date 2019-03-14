package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.ServApp.Notes;

public class RcvImageViewHolder extends BaseViewHolder<Notes,
        OnItemClickListener<Notes>> {
    private ImageView img;

    public RcvImageViewHolder(View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.img);

    }

    @Override
    public void onBind(Notes item,
                       @Nullable OnItemClickListener<Notes> listener) {
        GlideBindingAdapters.setImageResourceBitmap(img, item.getBitmap());
    }


}
