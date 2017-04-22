package ru.pocketbyte.example.ui.listbinder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import ru.pocketbyte.example.R;
import ru.pocketbyte.example.model.ListItem3;
import ru.pocketbyte.recyclerext.adapter.holder.AbsDraggableItemViewHolder;
import ru.pocketbyte.recyclerext.adapter.binder.ListItemBinder;
import ru.pocketbyte.recyclerext.utils.ViewUtils;

/**
 * @author Denis Shurygin
 */

public class ListItem3Binder implements ListItemBinder<ListItem3> {

    @Override
    public void bind(Context context, RecyclerView.ViewHolder holder, ListItem3 item) {
        Holder itemHolder = (Holder) holder;
        itemHolder.txtTitle.setText(item.title);
        itemHolder.txtValue.setText(item.message);
        itemHolder.imgIcon.setImageResource(item.icon);
    }

    @Override
    public Holder buildHolder(View view) {
        return new Holder(view);
    }

    public static class Holder extends AbsDraggableItemViewHolder {

        public final TextView txtTitle;
        public final TextView txtValue;
        public final ImageView imgIcon;

        public Holder(View itemView) {
            super(itemView);

            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtValue = (TextView) itemView.findViewById(R.id.txtValue);
            imgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
        }

        @Override
        public boolean isCanStartDrag(int x, int y) {
            return ViewUtils.hitTest(imgIcon, x, y);
        }
    }
}