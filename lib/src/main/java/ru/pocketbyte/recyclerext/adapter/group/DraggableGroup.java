package ru.pocketbyte.recyclerext.adapter.group;

import android.support.v7.widget.RecyclerView;

/**
 * @author Denis Shurygin
 */

public interface DraggableGroup {

    boolean isDraggable();
    void onMoveChildItem(int fromPosition, int toPosition);

    boolean isCanStartDrag(RecyclerView.ViewHolder holder, int childPosition, int x, int y);
}
