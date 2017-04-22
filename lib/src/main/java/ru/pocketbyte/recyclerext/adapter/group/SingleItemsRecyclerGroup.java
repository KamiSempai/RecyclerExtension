package ru.pocketbyte.recyclerext.adapter.group;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ru.pocketbyte.recyclerext.adapter.binder.ListItemBinder;
import ru.pocketbyte.recyclerext.adapter.binder.SingleItemBinder;

/**
 * @author Denis Shurygin
 */
public class SingleItemsRecyclerGroup<GroupItem> implements RecyclerGroup<GroupItem, SingleItemBinder<?>> {

    private ListItemBinder<GroupItem> mGroupItemBinder;
    private GroupItem mGroupItem;
    private int mGroupItemLayoutId;

    private List<SingleItemBinder<?>> mChildren;

    public SingleItemsRecyclerGroup(GroupItem groupItem,
                                    ListItemBinder<GroupItem> groupItemBinder,
                                    int groupLayoutId,
                                    List<SingleItemBinder<?>> children) {
        mGroupItemBinder = groupItemBinder;
        mGroupItem = groupItem;
        mGroupItemLayoutId = groupLayoutId;
        mChildren = children;
    }

    @Override
    public GroupItem getGroupItem() {
        return mGroupItem;
    }

    @Override
    public int getChildCount() {
        return mChildren.size();
    }

    @Override
    public SingleItemBinder<?> getChildItem(int position) {
        return mChildren.get(position);
    }

    @Override
    public int getChildItemType(int position) {
        return position;
    }

    @Override
    public long getChildItemId(int position) {
        return mChildren.get(position).getItemId();
    }

    @Override
    public RecyclerView.ViewHolder buildGroupHolder(LayoutInflater inflater, ViewGroup parent) {
        return mGroupItemBinder.buildHolder(inflater.inflate(mGroupItemLayoutId, parent, false));
    }

    @Override
    public RecyclerView.ViewHolder buildChildHolder(LayoutInflater inflater, ViewGroup parent,
                                                    int viewType) {
        return mChildren.get(viewType).buildHolder(inflater, parent);
    }

    @Override
    public void bindGroupHolder(Context context, RecyclerView.ViewHolder holder) {
        mGroupItemBinder.bind(context, holder, getGroupItem());
    }

    @Override
    public void bindChildHolder(Context context, RecyclerView.ViewHolder holder, int position) {
        mChildren.get(position).bind(context, holder, null);
    }

}
