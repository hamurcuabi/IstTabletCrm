package com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter;

public interface OnItemClickListener<T> extends BaseRecyclerListener {

   // void onItemClicked(T item);

    void onItemClicked(T item, int positon);
}