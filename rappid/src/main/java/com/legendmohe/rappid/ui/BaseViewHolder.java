package com.legendmohe.rappid.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by legendmohe on 16/4/14.
 */
public class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public final View mView;
    public T mItem;

    public BaseViewHolder(View view) {
        super(view);
        mView = view;
        ButterKnife.bind(this, view);
    }
}
