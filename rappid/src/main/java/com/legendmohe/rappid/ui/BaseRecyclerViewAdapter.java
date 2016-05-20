package com.legendmohe.rappid.ui;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.legendmohe.rappid.util.CommonUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by legendmohe on 16/4/14.
 */
public abstract class BaseRecyclerViewAdapter<T, VH extends BaseViewHolder<T>> extends RecyclerView.Adapter<VH> {

    protected DataProvider<T> mDataProvider;
    protected OnItemClickedListener<T> mItemClickedListener;

    public BaseRecyclerViewAdapter(OnItemClickedListener itemClickedListener, DataProvider<T> dataProvider) {
        mItemClickedListener = itemClickedListener;
        mDataProvider = dataProvider;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(getViewHolderLayout(), parent, false);
        return onCreateBaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VH holder, int position) {
        holder.mItem = mDataProvider.get(position);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickedListener != null && shouldHandleClick(holder.mItem)) {
                    mItemClickedListener.onItemClicked(holder.mItem);
                }
            }
        });

        onBindBaseViewHolder(holder, position);
    }

    public void addItem(T item) {
        if (item != null) {
            mDataProvider.add(item);
            notifyDataSetChanged();
        }
    }

    public void addItems(List<T> items) {
        if (CommonUtil.isEmpty(items))
            return;
        mDataProvider.addAll(items);
        notifyDataSetChanged();
    }

    public void removeItem(T item) {
        if (item == null)
            return;
        mDataProvider.remove(item);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDataProvider.size();
    }

    public boolean shouldHandleClick(T item) {
        return true;
    }

    protected abstract
    @LayoutRes
    int getViewHolderLayout();

    protected abstract VH onCreateBaseViewHolder(View view);

    protected abstract void onBindBaseViewHolder(VH holder, int position);

    public interface OnItemClickedListener<T> {
        void onItemClicked(T item);
    }

    public interface DataProvider<I> extends Iterable<I> {
        void remove(I item);

        void add(I item);

        void addAll(Collection<? extends I> items);

        boolean contains(I item);

        I get(int idx);

        int size();

        int indexOf(I item);
    }

    public static class ListDataProvider<T> implements DataProvider<T> {

        List<T> mValues = new ArrayList<>();

        @Override
        public void remove(T item) {
            mValues.remove(item);
        }

        @Override
        public void add(T item) {
            mValues.add(item);
        }

        @Override
        public void addAll(Collection<? extends T> items) {
            mValues.addAll(items);
        }

        @Override
        public boolean contains(T item) {
            return mValues.contains(item);
        }

        @Override
        public T get(int idx) {
            return mValues.get(idx);
        }

        @Override
        public int size() {
            return mValues.size();
        }

        @Override
        public int indexOf(T item) {
            return mValues.indexOf(item);
        }

        @Override
        public Iterator<T> iterator() {
            return mValues.iterator();
        }
    }

    public static class SetDataProvider<T> implements DataProvider<T> {

        Set<T> mValues = new HashSet<>();
        List<T> mOrderValues = new ArrayList<>();

        @Override
        public void remove(T item) {
            mOrderValues.remove(item);
            mValues.remove(item);
        }

        @Override
        public void add(T item) {
            if (mValues.contains(item))
                mOrderValues.remove(item);
            mOrderValues.add(item);
            mValues.add(item);
        }

        @Override
        public void addAll(Collection<? extends T> items) {
            Set<T> intersection = new HashSet<T>(mValues); // use the copy constructor
            intersection.retainAll(items);
            for (T item : intersection) {
                mOrderValues.remove(item);
            }
            mOrderValues.addAll(items);
            mValues.addAll(items);
        }

        @Override
        public boolean contains(T item) {
            return mValues.contains(item);
        }

        @Override
        public T get(int idx) {
            return mOrderValues.get(idx);
        }

        @Override
        public int size() {
            return mValues.size();
        }

        @Override
        public int indexOf(T item) {
            return mOrderValues.indexOf(item);
        }

        @Override
        public Iterator<T> iterator() {
            return mOrderValues.iterator();
        }
    }
}
