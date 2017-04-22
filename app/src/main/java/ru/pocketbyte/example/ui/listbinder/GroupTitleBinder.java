package ru.pocketbyte.example.ui.listbinder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import ru.pocketbyte.example.R;
import ru.pocketbyte.recyclerext.adapter.binder.ListItemBinder;

/**
 * @author Denis Shurygin
 */

public class GroupTitleBinder implements ListItemBinder<String> {

    @Override
    public void bind(Context context, RecyclerView.ViewHolder holder, String item) {
        Holder itemHolder = (Holder) holder;
        itemHolder.txtTitle.setText(item);

        final int expandState = itemHolder.getExpandStateFlags();

        if ((expandState & ExpandableItemConstants.STATE_FLAG_IS_UPDATED) != 0) {

            boolean isExpanded = (expandState & ExpandableItemConstants.STATE_FLAG_IS_EXPANDED) != 0;

            itemHolder.imgIndicator.setImageResource(isExpanded ?
                    R.drawable.ic_chevron_down :
                    R.drawable.ic_chevron_left);
        }
    }

    @Override
    public Holder buildHolder(View view) {
        return new Holder(view);
    }

    public static class Holder extends AbstractExpandableItemViewHolder {

        public final TextView txtTitle;
        private ImageView imgIndicator;

        public Holder(View itemView) {
            super(itemView);

            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            imgIndicator = (ImageView) itemView.findViewById(R.id.imgIndicator);
        }
    }
}