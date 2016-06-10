package com.legendmohe.rappid.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by legendmohe on 16/4/14.
 */
public class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    private T mItem;

    public BaseViewHolder(View view) {
        super(view);
    }

    public View getItemView() {
        return itemView;
    }

    public T getItem() {
        return mItem;
    }

    public void setItem(T item) {
        mItem = item;
    }
}
