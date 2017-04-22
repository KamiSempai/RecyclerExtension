package ru.pocketbyte.recyclerext.adapter.group;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import ru.pocketbyte.recyclerext.adapter.binder.ListItemBinder;

/**
 * @author Denis Shurygin
 */
public abstract class BaseRecyclerGroup<GroupItemType, ChildItemType>
        implements RecyclerGroup<GroupItemType, ChildItemType> {

    public interface ChildItem {
        long getId();
    }

    private GroupItemType mGroupItem;
    private List<ChildItemType> mChildren;

    protected ListItemBinder<GroupItemType> mGroupItemBinder;
    protected ListItemBinder<ChildItemType> mChildItemBinder;

    public BaseRecyclerGroup(
            GroupItemType groupItem,
            ListItemBinder<GroupItemType> groupItemBinder,
            List<ChildItemType> children,
            ListItemBinder<ChildItemType> childItemBinder) {

        mGroupItem = groupItem;
        mChildren = children;

        mGroupItemBinder = groupItemBinder;
        mChildItemBinder = childItemBinder;
    }

    @Override
    public GroupItemType getGroupItem() {
        return mGroupItem;
    }

    @Override
    public int getChildCount() {
        return mChildren.size();
    }

    @Override
    public ChildItemType getChildItem(int position) {
        return mChildren.get(position);
    }

    @Override
    public int getChildItemType(int position) {
        return 0;
    }

    @Override
    public long getChildItemId(int position) {
        ChildItemType child = getChildItem(position);

        if (child instanceof ChildItem)
            return ((ChildItem) child).getId();

        return position;
    }

    @Override
    public void bindGroupHolder(Context context, RecyclerView.ViewHolder holder) {
        mGroupItemBinder.bind(context, holder, getGroupItem());
    }

    @Override
    public void bindChildHolder(Context context, RecyclerView.ViewHolder holder, int position) {
        mChildItemBinder.bind(context, holder, getChildItem(position));
    }

    protected RecyclerView.ViewHolder buildChildHolder(View view, int viewType) {
        return mChildItemBinder.buildHolder(view);
    }
}
