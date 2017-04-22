package ru.pocketbyte.recyclerext.adapter.binder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Denis Shurygin
 */
public interface ListItemBinder<ItemType> {

    void bind(Context context, RecyclerView.ViewHolder holder, ItemType item);
    RecyclerView.ViewHolder buildHolder(View view);

}
