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
public abstract class BaseRecyclerViewAdapter<T, VH extends BaseViewHolder<T>> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = Integer.MIN_VALUE;
    private static final int TYPE_FOOTER = Integer.MIN_VALUE + 1;
    private static final int TYPE_BASE_ITEM = 100;
    private static final int TYPE_ADAPTEE_OFFSET = 2;

    protected DataProvider<T> mDataProvider;
    protected OnItemClickedListener<T> mItemClickedListener;

    public BaseRecyclerViewAdapter(OnItemClickedListener itemClickedListener, DataProvider<T> dataProvider) {
        mItemClickedListener = itemClickedListener;
        mDataProvider = dataProvider;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return onCreateHeaderViewHolder(parent, viewType);
        } else if (viewType == TYPE_FOOTER) {
            return onCreateFooterViewHolder(parent, viewType);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(getItemViewHolderLayout(viewType), parent, false);
            return onCreateBaseViewHolder(view, viewType - TYPE_ADAPTEE_OFFSET);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (position == 0 && holder.getItemViewType() == TYPE_HEADER) {
            onBindHeaderView(holder, position);
        } else if (position == getBaseItemCount() && holder.getItemViewType() == TYPE_FOOTER) {
            onBindFooterView(holder, position);
        } else {
            position = getBaseIndex(position);
            final VH baseViewHolder = (VH) holder;

            baseViewHolder.setItem(mDataProvider.get(position));
            baseViewHolder.getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickedListener != null && shouldHandleClick(baseViewHolder.getItem())) {
                        mItemClickedListener.onItemClicked(baseViewHolder.getItem());
                    }
                }
            });

            onBindBaseViewHolder(baseViewHolder, position);
        }
    }

    public void addItem(T item) {
        if (item != null) {
            mDataProvider.add(item);
            notifyDataSetChanged();
        }
    }

    public void addItems(Collection<T> items) {
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

    public void removeItems(Collection<T> items) {
        if (CommonUtil.isEmpty(items))
            return;
        mDataProvider.removeAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        int itemCount = getBaseItemCount();
        if (hasHeader()) {
            itemCount += 1;
        }
        if (hasFooter()) {
            itemCount += 1;
        }
        return itemCount;
    }

    public int replaceItem(T item) {
        if (item == null)
            return -1;
        int idx = mDataProvider.replace(item);
        if (idx != -1)
            notifyItemChanged(internalIndexOf(item));
        return idx;
    }

    public int indexOf(T item) {
        if (item == null)
            return -1;
        return mDataProvider.indexOf(item);
    }

    public T getItem(int index) {
        return mDataProvider.get(index);
    }

    public int getBaseIndex(int innerIndex) {
        return innerIndex - (hasHeader() ? 1 : 0);
    }

    public int getInnerIndex(int baseIndex) {
        return baseIndex + (hasHeader() ? 1 : 0);
    }

    public int internalIndexOf(T item) {
        return getInnerIndex(indexOf(item));
    }

    public int getBaseItemCount() {
        return mDataProvider.size();
    }

    public boolean shouldHandleClick(T item) {
        return true;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && hasHeader()) {
            return TYPE_HEADER;
        }
        if (position == getBaseItemCount() && hasFooter()) {
            return TYPE_FOOTER;
        }
        if (getBaseItemType(position) >= Integer.MAX_VALUE - TYPE_ADAPTEE_OFFSET) {
            new IllegalStateException("HeaderRecyclerViewAdapter offsets your BasicItemType by " + TYPE_ADAPTEE_OFFSET + ".");
        }
        return getBaseItemType(position) + TYPE_ADAPTEE_OFFSET;
    }

    private int getBaseItemType(int position) {
        return TYPE_BASE_ITEM;
    }

    protected abstract
    @LayoutRes
    int getItemViewHolderLayout(int viewType);

    public boolean hasHeader() {
        return false;
    }

    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public void onBindHeaderView(RecyclerView.ViewHolder holder, int position) {
    }

    public boolean hasFooter() {
        return false;
    }

    public RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public void onBindFooterView(RecyclerView.ViewHolder holder, int position) {
    }

    protected abstract VH onCreateBaseViewHolder(View view, int viewType);

    protected abstract void onBindBaseViewHolder(VH holder, int position);

    public interface OnItemClickedListener<T> {
        void onItemClicked(T item);
    }

    public interface DataProvider<I> extends Iterable<I> {
        void remove(I item);

        void removeAll(Collection<? extends I> items);

        void add(I item);

        void addAll(Collection<? extends I> items);

        boolean contains(I item);

        I get(int idx);

        int size();

        int indexOf(I item);

        int replace(I item);
    }

    public static class ListDataProvider<T> implements DataProvider<T> {

        List<T> mValues = new ArrayList<>();

        @Override
        public void remove(T item) {
            mValues.remove(item);
        }

        public void removeAll(Collection<? extends T> items) {
            mValues.removeAll(items);
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
        public int replace(T item) {
            int idx = mValues.indexOf(item);
            if (idx != -1) {
                mValues.set(idx, item);
            }
            return idx;
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

        public void removeAll(Collection<? extends T> items) {
            mValues.removeAll(items);
            mOrderValues.removeAll(items);
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

        @Override
        public int replace(T item) {
            int idx = mOrderValues.indexOf(item);
            if (idx != -1) {
                mOrderValues.set(idx, item);
                mValues.add(item);
            }
            return idx;
        }
    }
}
