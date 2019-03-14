package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.ServApp.Notes;
import com.emrhmrc.isttabletcrm.models.Unsuitablity.MultiPartItem;

public class RcvImageAdapter extends GenericAdapter<Notes,
        OnItemClickListener<Notes>,
        RcvImageViewHolder> {
    private Context context;

    public RcvImageAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public RcvImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RcvImageViewHolder(inflate(R.layout.image_item, parent));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


}
