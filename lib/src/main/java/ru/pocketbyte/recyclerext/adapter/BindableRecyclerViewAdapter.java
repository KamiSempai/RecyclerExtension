package ru.pocketbyte.recyclerext.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.pocketbyte.recyclerext.adapter.binder.ListItemBinder;


/**
 * @author Denis Shurygin
 */
public class BindableRecyclerViewAdapter<ItemType> extends RecyclerView.Adapter {

    public interface OnItemClickListener<ItemType> {
        void onItemClick(RecyclerView.ViewHolder holder, ItemType item);
    }

    protected final Context mContext;
    private LayoutInflater mInflater;
    private List<ItemType> mList;
    private ListItemBinder<ItemType> mBinder;
    private int mLayoutId;

    private OnItemClickListener<ItemType> mListener;

    public BindableRecyclerViewAdapter(Context context, List<ItemType> list, ListItemBinder<ItemType> binder, int layoutId) {
        super();
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mList = list;
        mBinder = binder;
        mLayoutId = layoutId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder holder = mBinder.buildHolder(mInflater.inflate(mLayoutId, parent, false));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleItemClick(holder);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mBinder.bind(mContext, holder, getItem(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public ItemType getItem(int position) {
        return mList.get(position);
    }

    public void setItemClickListener(OnItemClickListener<ItemType> listener) {
        mListener = listener;
    }

    private void handleItemClick(RecyclerView.ViewHolder holder) {
        if (mListener != null) {
            ItemType item = getItem(holder.getAdapterPosition());
            mListener.onItemClick(holder, item);
        }
    }
}
