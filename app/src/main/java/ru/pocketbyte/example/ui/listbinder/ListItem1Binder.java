package ru.pocketbyte.example.ui.listbinder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import ru.pocketbyte.example.R;
import ru.pocketbyte.example.model.ListItem1;
import ru.pocketbyte.recyclerext.adapter.binder.ListItemBinder;

/**
 * @author Denis Shurygin
 */

public class ListItem1Binder implements ListItemBinder<ListItem1> {

    private String mTitleS;

    public ListItem1Binder(String title) {
        mTitleS = title;
    }

    @Override
    public void bind(Context context, RecyclerView.ViewHolder holder, ListItem1 item) {
        Holder itemHolder = (Holder) holder;
        itemHolder.txtTitle.setText(mTitleS);
        itemHolder.txtValue.setText(item.message);
        itemHolder.imgIcon.setImageResource(item.icon);
    }

    @Override
    public Holder buildHolder(View view) {
        return new Holder(view);
    }

    public static class Holder extends AbstractExpandableItemViewHolder {

        public final TextView txtTitle;
        public final TextView txtValue;
        public final ImageView imgIcon;

        public Holder(View itemView) {
            super(itemView);

            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtValue = (TextView) itemView.findViewById(R.id.txtValue);
            imgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
        }
    }
}