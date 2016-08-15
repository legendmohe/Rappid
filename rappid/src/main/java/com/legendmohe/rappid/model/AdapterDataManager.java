package com.legendmohe.rappid.model;

import com.legendmohe.rappid.ui.BaseRecyclerViewAdapter;
import com.legendmohe.rappid.util.CommonUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by legendmohe on 16/4/27.
 */
public abstract class AdapterDataManager<K, V> extends DataManager<K, V> {

    private List<BaseRecyclerViewAdapter> mBindedAdapters = new ArrayList<>();

    public void addItem(V item) {
        super.addItem(item);
        notiBindedAdapterItemAdded(item);
    }

    public void addItem(K itemId, V item) {
        super.addItem(itemId, item);
        notiBindedAdapterItemAdded(item);
    }

    public void addItems(Collection<V> items) {
        if (CommonUtil.isEmpty(items))
            return;

        super.addItems(items);
        notiBindedAdapterItemsAdded(items);
    }

    public void removeItemById(K itemId) {
        V item = getItem(itemId);
        if (item != null) {
            super.removeItemById(itemId);
            notiBindedAdapterItemRemove(item);
        }
    }

    public void removeItems(Collection<K> itemIds) {
        if (CommonUtil.isEmpty(itemIds))
            return;

        List<V> items = new ArrayList<>();
        for (K itemId :
                itemIds) {
            V item = getItem(itemId);
            if (item != null) {
                items.add(item);
            }
        }
        super.removeItems(itemIds);
        notiBindedAdapterItemsRemove(items);
    }

    public void clear() {
        Collection<V> items = getAllItems();
        if (CommonUtil.isEmpty(items))
            return;

        super.clear();
        notiBindedAdapterItemsClear(items);
    }

    public void updateItem(K itemId, V item) {
        super.updateItem(itemId, item);
        notiBindedAdapterItemUpdate(item);
    }

    public void bindAdapter(BaseRecyclerViewAdapter adapter) {
        if (!mBindedAdapters.contains(adapter)) {
            mBindedAdapters.add(adapter);
        }
    }

    public void unbindAdapter(BaseRecyclerViewAdapter adapter) {
        mBindedAdapters.remove(adapter);
    }

    private void notiBindedAdapterItemUpdate(final V item) {
        for (BaseRecyclerViewAdapter adapter : mBindedAdapters) {
            adapter.replaceItem(item);
        }
    }

    private void notiBindedAdapterItemAdded(final V item) {
        for (BaseRecyclerViewAdapter adapter : mBindedAdapters) {
            adapter.addItem(item);
        }
    }

    private void notiBindedAdapterItemRemove(final V item) {
        for (BaseRecyclerViewAdapter adapter : mBindedAdapters) {
            adapter.removeItem(item);
        }
    }

    private void notiBindedAdapterItemsAdded(final Collection items) {
        for (BaseRecyclerViewAdapter adapter : mBindedAdapters) {
            adapter.addItems(items);
        }
    }

    private void notiBindedAdapterItemsRemove(final Collection items) {
        for (BaseRecyclerViewAdapter adapter : mBindedAdapters) {
            adapter.removeItems(items);
        }
    }

    private void notiBindedAdapterItemsClear(final Collection<V> items) {
        for (BaseRecyclerViewAdapter adapter : mBindedAdapters) {
            adapter.removeItems(items);
        }
    }
}
