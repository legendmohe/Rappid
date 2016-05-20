package com.legendmohe.rappid.model;

import android.content.Context;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by legendmohe on 16/4/27.
 */
public abstract class DataManager<K, V> {

    private Context mContext;

    private ConcurrentHashMap<K, V> mDataMap = new ConcurrentHashMap<>();

    private DataObservable<V> mDataObservable = new DataObservableImpl<>();

    public void init(Context context) {
        mContext = context;
    }

    public Context getContext() {
        if (mContext == null)
            throw new NullPointerException("context is null");
        return mContext;
    }

    public boolean contains(V item) {
        return mDataMap.containsValue(item);
    }

    public boolean containsItemId(K itemId) {
        return mDataMap.containsKey(itemId);
    }

    public void addItem(V item) {
        mDataMap.put(getIdFromItem(item), item);
        mDataObservable.notifyDataAdded(item);
    }

    public void addItem(K itemId, V item) {
        mDataMap.put(itemId, item);
        mDataObservable.notifyDataAdded(item);
    }

    public void addItems(Collection<V> items) {
        for (V item :
                items) {
            addItem(getIdFromItem(item), item);
        }
    }

    public void removeItem(V item) {
        if (item != null) {
            mDataMap.remove(getIdFromItem(item));
            mDataObservable.notifyDataRemove(item);
        }
    }

    public void removeItemById(K itemId) {
        V item = mDataMap.get(itemId);
        if (item != null) {
            mDataMap.remove(itemId);
            mDataObservable.notifyDataRemove(item);
        }
    }

    public void removeItems(Collection<K> itemIds) {
        for (K itemId :
                itemIds) {
            V item = mDataMap.get(itemId);
            if (item != null) {
                mDataMap.remove(itemId);
                mDataObservable.notifyDataRemove(item);
            }
        }
    }

    public void clear() {
        Collection<V> items = mDataMap.values();
        if (items.size() != 0) {
            mDataMap.clear();
            for (V item :
                    items) {
                mDataObservable.notifyDataRemove(item);
            }
        }
    }

    public void updateItem(V item) {
        updateItem(getIdFromItem(item), item);
    }

    public void updateItem(K itemId, V item) {
        mDataMap.put(itemId, item);
        mDataObservable.notifyDataUpdated(item);
    }

    public Collection<V> getAllItems() {
        return mDataMap.values();
    }

    public V getItem(K itemId) {
        return mDataMap.get(itemId);
    }

    public void registerDataObserver(DataObserver<V> observer) {
        mDataObservable.register(observer);
    }

    public void unregisterDataObserver(DataObserver<V> observer) {
        mDataObservable.unregister(observer);
    }

    abstract public K getIdFromItem(V item);

}
