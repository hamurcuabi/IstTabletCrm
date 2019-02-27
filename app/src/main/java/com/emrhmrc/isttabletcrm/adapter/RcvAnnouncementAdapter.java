package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.filter.NotifTypeFilterAdapter;
import com.emrhmrc.isttabletcrm.models.Notification.Notification;

public class RcvAnnouncementAdapter extends GenericAdapter<Notification,
        OnItemClickListener<Notification>,
        RcvAnnoucementViewHolder> implements Filterable {
    NotifTypeFilterAdapter filterAdapter;

    public RcvAnnouncementAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);
        filterAdapter = new NotifTypeFilterAdapter(this, getItemsFilter());

    }

    @Override
    public RcvAnnoucementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //viewtype göre ekelriz
        return new RcvAnnoucementViewHolder(inflate(R.layout.announcemenent_item, parent));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public Filter getFilter() {
        return filterAdapter;
    }
}