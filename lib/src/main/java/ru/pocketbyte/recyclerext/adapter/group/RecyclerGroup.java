package ru.pocketbyte.recyclerext.adapter.group;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @author Denis Shurygin
 */
public interface RecyclerGroup<GroupItemType, ChildItemType> {

    GroupItemType getGroupItem();
    int getChildCount();
    ChildItemType getChildItem(int position);
    int getChildItemType(int position);
    long getChildItemId(int position);

    RecyclerView.ViewHolder buildGroupHolder(LayoutInflater inflater, ViewGroup parent);
    RecyclerView.ViewHolder buildChildHolder(LayoutInflater inflater, ViewGroup parent, int viewType);

    void bindGroupHolder(Context context, RecyclerView.ViewHolder holder);
    void bindChildHolder(Context context, RecyclerView.ViewHolder holder, int position);

}
