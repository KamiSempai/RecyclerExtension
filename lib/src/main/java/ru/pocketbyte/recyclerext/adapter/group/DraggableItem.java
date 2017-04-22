package ru.pocketbyte.recyclerext.adapter.group;

import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemViewHolder;

/**
 * @author Denis Shurygin
 */

public interface DraggableItem extends DraggableItemViewHolder {

    boolean isCanStartDrag(int x, int y);

}
