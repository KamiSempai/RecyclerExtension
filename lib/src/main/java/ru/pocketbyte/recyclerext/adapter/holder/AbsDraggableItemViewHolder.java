package ru.pocketbyte.recyclerext.adapter.holder;

import android.view.View;

import com.h6ah4i.android.widget.advrecyclerview.draggable.annotation.DraggableItemStateFlags;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import ru.pocketbyte.recyclerext.adapter.group.DraggableItem;

/**
 * @author Denis Shurygin
 */

public abstract class AbsDraggableItemViewHolder extends AbstractExpandableItemViewHolder
        implements DraggableItem {

    @DraggableItemStateFlags
    private int mDragStateFlags;

    public AbsDraggableItemViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setDragStateFlags(@DraggableItemStateFlags int flags) {
        mDragStateFlags = flags;
    }

    @Override
    @DraggableItemStateFlags
    public int getDragStateFlags() {
        return mDragStateFlags;
    }
}
