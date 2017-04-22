package ru.pocketbyte.recyclerext.adapter.group;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.pocketbyte.recyclerext.adapter.binder.ListItemBinder;
import ru.pocketbyte.recyclerextension.R;

/**
 * @author Denis Shurygin
 */
public class SimpleRecyclerGroup<GroupItemType, ChildItemType>
        extends BaseRecyclerGroup<GroupItemType, ChildItemType> {

    private int mGroupItemLayoutId;
    private int mChildItemLayoutId;

    private int mEmptyListItemLayout = R.layout.list_item_empty;

    public SimpleRecyclerGroup(
            GroupItemType groupItem,
            ListItemBinder<GroupItemType> groupItemBinder,
            int groupItemLayout,
            List<ChildItemType> children,
            ListItemBinder<ChildItemType> childItemBinder,
            int childItemLayout) {
        super(groupItem, groupItemBinder, children, childItemBinder);
        mGroupItemLayoutId = groupItemLayout;
        mChildItemLayoutId = childItemLayout;
    }

    @Override
    public int getChildCount() {
        int count = super.getChildCount();
        if (count == 0)
            return 1;
        return count;
    }

    @Override
    public ChildItemType getChildItem(int position) {
        if (super.getChildCount() == 0)
            return null;
        return super.getChildItem(position);
    }

    @Override
    public int getChildItemType(int position) {
        return super.getChildCount() == 0 ? 1 : 0;
    }

    @Override
    public long getChildItemId(int position) {
        return super.getChildCount() == 0 ? -1 : super.getChildItemId(position);
    }

    @Override
    public void bindChildHolder(Context context, RecyclerView.ViewHolder holder, int position) {
        if (super.getChildCount() > 0)
            super.bindChildHolder(context, holder, position);
    }

    @Override
    public RecyclerView.ViewHolder buildGroupHolder(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(mGroupItemLayoutId, parent, false);
        RecyclerView.ViewHolder holder = mGroupItemBinder.buildHolder(view);
        return holder;
    }

    @Override
    public RecyclerView.ViewHolder buildChildHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        if (viewType == 0)
            return buildChildHolder(inflater.inflate(mChildItemLayoutId, parent, false), viewType);

        View view = inflater.inflate(mEmptyListItemLayout, parent, false);
        return new RecyclerView.ViewHolder(view) {  };
    }

    public void setEmptyListItemLayout(int layoutId) {
        mEmptyListItemLayout = layoutId;
    }
}
