package ru.pocketbyte.recyclerext.adapter.group;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import ru.pocketbyte.recyclerext.adapter.binder.ListItemBinder;

/**
 * @author Denis Shurygin
 */

public abstract class AbsDraggableRecyclerGroup<GroupItemType, ChildItemType>
        extends SimpleRecyclerGroup<GroupItemType, ChildItemType> implements DraggableGroup {

    public AbsDraggableRecyclerGroup(
            GroupItemType groupItem,
            ListItemBinder<GroupItemType> groupItemBinder,
            int groupItemLayout,
            List<ChildItemType> children,
            ListItemBinder<ChildItemType> childItemBinder,
            int childItemLayout) {
        super(groupItem, groupItemBinder, groupItemLayout, children, childItemBinder, childItemLayout);
    }

    @Override
    public boolean isDraggable() {
        return true;
    }

    @Override
    public boolean isCanStartDrag(RecyclerView.ViewHolder holder, int childPosition, int x, int y) {
        if (holder instanceof DraggableItem)
            return ((DraggableItem) holder).isCanStartDrag(x, y);
        return false;
    }

}
