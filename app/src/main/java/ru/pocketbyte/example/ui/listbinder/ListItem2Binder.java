package ru.pocketbyte.example.ui.listbinder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.pocketbyte.example.R;
import ru.pocketbyte.example.model.ListItem2;
import ru.pocketbyte.recyclerext.adapter.holder.AbsDraggableItemViewHolder;
import ru.pocketbyte.recyclerext.adapter.binder.ListItemBinder;
import ru.pocketbyte.recyclerext.utils.ViewUtils;

/**
 * @author Denis Shurygin
 */

public class ListItem2Binder implements ListItemBinder<ListItem2> {

    @Override
    public void bind(Context context, RecyclerView.ViewHolder holder, ListItem2 item) {
        Holder itemHolder = (Holder) holder;
        itemHolder.txtTitle.setText(item.title);
        itemHolder.txtValue.setText(item.message);
        itemHolder.viewColorPreview.setBackgroundColor(item.color);
    }

    @Override
    public Holder buildHolder(View view) {
        return new Holder(view);
    }

    public static class Holder extends AbsDraggableItemViewHolder {

        public final TextView txtTitle;
        public final TextView txtValue;
        public final View viewColorPreview;

        public Holder(View itemView) {
            super(itemView);

            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtValue = (TextView) itemView.findViewById(R.id.txtValue);
            viewColorPreview = itemView.findViewById(R.id.viewColorPreview);
        }

        @Override
        public boolean isCanStartDrag(int x, int y) {
            return ViewUtils.hitTest(viewColorPreview, x, y);
        }
    }
}