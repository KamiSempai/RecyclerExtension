package ru.pocketbyte.recyclerext.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableDraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.expandable.GroupPositionItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;

import java.util.List;

import ru.pocketbyte.recyclerext.adapter.group.DraggableGroup;
import ru.pocketbyte.recyclerext.adapter.group.OnChildClickListener;
import ru.pocketbyte.recyclerext.adapter.group.RecyclerGroup;

/**
 * @author Denis Shurygin
 */
public class ExpandableGroupsAdapter extends AbstractExpandableItemAdapter<RecyclerView.ViewHolder, RecyclerView.ViewHolder>
        implements ExpandableDraggableItemAdapter<RecyclerView.ViewHolder, RecyclerView.ViewHolder>{

    private Context mContext;
    private LayoutInflater mInflater;
    private List<RecyclerGroup<?, ?>> mGroups;

    private OnChildClickListener mChildClickListener;

    public ExpandableGroupsAdapter(Context context, List<RecyclerGroup<?, ?>> groups) {
        super();
        setHasStableIds(true);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mGroups = groups;
    }

    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public int getChildCount(int groupPosition) {
        return mGroups.get(groupPosition).getChildCount();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return mGroups.get(groupPosition).getChildItemId(childPosition);
    }

    @Override
    public int getGroupItemViewType(int groupPosition) {
        return groupPosition;
    }

    @Override
    public int getChildItemViewType(int groupPosition, int childPosition) {
        return mGroups.get(groupPosition).getChildItemType(childPosition) + 100 * (groupPosition + 1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        return mGroups.get(viewType).buildGroupHolder(mInflater, parent);
    }

    @Override
    public RecyclerView.ViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        int group = (viewType / 100) - 1;
        final RecyclerView.ViewHolder viewHolder = mGroups.get(group).buildChildHolder(mInflater, parent, viewType % 100);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mChildClickListener != null)
                    mChildClickListener.onChildClick(viewHolder.getAdapterPosition());
                }
            });
        return viewHolder;
    }

    @Override
    public void onBindGroupViewHolder(RecyclerView.ViewHolder holder, int groupPosition, int viewType) {
        mGroups.get(groupPosition).bindGroupHolder(mContext, holder);
    }

    @Override
    public void onBindChildViewHolder(RecyclerView.ViewHolder holder, int groupPosition, int childPosition, int viewType) {
        mGroups.get(groupPosition).bindChildHolder(mContext, holder, childPosition);
    }

    @Override
    public boolean onCheckCanExpandOrCollapseGroup(RecyclerView.ViewHolder holder, int groupPosition, int x, int y, boolean expand) {
        return true;
    }

    public void setChildClickListener(OnChildClickListener listener) {
        mChildClickListener = listener;
    }

    // ============================================================================================
    // Dragable

    @Override
    public boolean onCheckGroupCanStartDrag(RecyclerView.ViewHolder holder, int groupPosition, int x, int y) {
        return false;
    }

    @Override
    public boolean onCheckChildCanStartDrag(RecyclerView.ViewHolder holder, int groupPosition, int childPosition, int x, int y) {
        RecyclerGroup<?, ?> group = mGroups.get(groupPosition);
        if (group instanceof DraggableGroup) {
            DraggableGroup dragGroup = (DraggableGroup) group;
            if (dragGroup.isDraggable()) {
                return dragGroup.isCanStartDrag(holder, childPosition, x, y);
            }
        }

        return false;
    }

    @Override
    public ItemDraggableRange onGetGroupItemDraggableRange(RecyclerView.ViewHolder holder, int groupPosition) {
        return null;
    }

    @Override
    public ItemDraggableRange onGetChildItemDraggableRange(RecyclerView.ViewHolder holder, int groupPosition, int childPosition) {
        return new GroupPositionItemDraggableRange(groupPosition, groupPosition);
    }

    @Override
    public void onMoveGroupItem(int fromGroupPosition, int toGroupPosition) { }

    @Override
    public void onMoveChildItem(int fromGroupPosition, int fromChildPosition, int toGroupPosition, int toChildPosition) {
        RecyclerGroup<?, ?> group = mGroups.get(fromGroupPosition);
        if (group instanceof DraggableGroup) {
            ((DraggableGroup) group).onMoveChildItem(fromChildPosition, toChildPosition);
        }
    }

    @Override
    public boolean onCheckGroupCanDrop(int draggingGroupPosition, int dropGroupPosition) {
        return draggingGroupPosition == dropGroupPosition;
    }

    @Override
    public boolean onCheckChildCanDrop(int draggingGroupPosition, int draggingChildPosition, int dropGroupPosition, int dropChildPosition) {
        return draggingGroupPosition == dropGroupPosition;
    }
}
