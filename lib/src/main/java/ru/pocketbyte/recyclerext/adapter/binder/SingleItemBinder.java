package ru.pocketbyte.recyclerext.adapter.binder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Denis Shurygin
 */

public class SingleItemBinder<ItemType> implements ListItemBinder<Void> {

    private ListItemBinder<ItemType> mBinder;
    private ItemType mItem;
    private int mLayoutId;
    private int mItemId;

    public SingleItemBinder(ListItemBinder<ItemType> binder, ItemType item, int layoutId, int itemId) {
        mBinder = binder;
        mItem = item;
        mLayoutId = layoutId;
        mItemId = itemId;
    }

    public void bind(Context context, RecyclerView.ViewHolder holder) {
        bind(context, holder, null);
    }

    @Override
    public void bind(Context context, RecyclerView.ViewHolder holder, Void item) {
        mBinder.bind(context, holder, mItem);
    }

    @Override
    public RecyclerView.ViewHolder buildHolder(View view) {
        return mBinder.buildHolder(view);
    }

    public RecyclerView.ViewHolder buildHolder(LayoutInflater inflater, ViewGroup parent) {
        return buildHolder(inflater.inflate(mLayoutId, parent, false));
    }

    public ItemType getItem() {
        return mItem;
    }

    public int getItemId() {
        return mItemId;
    }
}
