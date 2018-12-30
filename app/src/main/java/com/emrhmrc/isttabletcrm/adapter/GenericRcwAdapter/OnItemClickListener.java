package com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter;

import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseRecyclerListener;

public interface OnItemClickListener<T> extends BaseRecyclerListener {

    void onItemClicked(T item);
}