package ru.pocketbyte.example.ui;

import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.h6ah4i.android.widget.advrecyclerview.animator.GeneralItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.animator.RefactoredDefaultItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;

import java.util.ArrayList;
import java.util.List;

import ru.pocketbyte.example.R;
import ru.pocketbyte.example.ui.listbinder.GroupTitleBinder;
import ru.pocketbyte.example.ui.listbinder.ListItem1Binder;
import ru.pocketbyte.example.ui.listbinder.ListItem2Binder;
import ru.pocketbyte.example.ui.listbinder.ListItem3Binder;
import ru.pocketbyte.example.model.ListItem1;
import ru.pocketbyte.example.model.ListItem2;
import ru.pocketbyte.example.model.ListItem3;
import ru.pocketbyte.recyclerext.adapter.ExpandableGroupsAdapter;
import ru.pocketbyte.recyclerext.adapter.binder.SingleItemBinder;
import ru.pocketbyte.recyclerext.adapter.group.OnChildClickListener;
import ru.pocketbyte.recyclerext.adapter.group.RecyclerGroup;
import ru.pocketbyte.recyclerext.adapter.group.AbsDraggableRecyclerGroup;
import ru.pocketbyte.recyclerext.adapter.group.SingleItemsRecyclerGroup;


/**
 * @author Denis Shurygin
 */
public class MainActivity extends AppCompatActivity implements OnChildClickListener {

    private static final String SAVED_STATE_EXPANDABLE_ITEM_MANAGER =
            "MainActivity.SAVED_STATE_EXPANDABLE_ITEM_MANAGER";

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mWrappedAdapter;
    private RecyclerViewExpandableItemManager mRecyclerViewExpandableItemManager;
    private RecyclerViewDragDropManager mRecyclerViewDragDropManager;

    private List<RecyclerGroup<?, ?>> mDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);

        final Parcelable eimSavedState = (savedInstanceState != null) ?
                savedInstanceState.getParcelable(SAVED_STATE_EXPANDABLE_ITEM_MANAGER) : null;
        mRecyclerViewExpandableItemManager = new RecyclerViewExpandableItemManager(eimSavedState);
        mRecyclerViewExpandableItemManager.setDefaultGroupsExpandedState(true);

        // drag & drop manager
        mRecyclerViewDragDropManager = new RecyclerViewDragDropManager();

        //adapter
        mDataSource = buildDataSource();
        final ExpandableGroupsAdapter myItemAdapter = new ExpandableGroupsAdapter(this, mDataSource);
        myItemAdapter.setChildClickListener(this);

        mWrappedAdapter = mRecyclerViewExpandableItemManager.createWrappedAdapter(myItemAdapter);
        mWrappedAdapter = mRecyclerViewDragDropManager.createWrappedAdapter(mWrappedAdapter);

        mRecyclerViewDragDropManager.setCheckCanDropEnabled(false);

        final GeneralItemAnimator animator = new RefactoredDefaultItemAnimator();
        animator.setSupportsChangeAnimations(false);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mWrappedAdapter);  // requires *wrapped* adapter
        mRecyclerView.setItemAnimator(animator);
        mRecyclerView.setHasFixedSize(false);

        mRecyclerViewDragDropManager.attachRecyclerView(mRecyclerView);
        mRecyclerViewExpandableItemManager.attachRecyclerView(mRecyclerView);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save current state to support screen rotation, etc...
        if (mRecyclerViewExpandableItemManager != null) {
            outState.putParcelable(
                    SAVED_STATE_EXPANDABLE_ITEM_MANAGER,
                    mRecyclerViewExpandableItemManager.getSavedState());
        }
    }

    @Override
    public void onDestroy() {
        mLayoutManager = null;

        if (mRecyclerViewExpandableItemManager != null) {
            mRecyclerViewExpandableItemManager.release();
            mRecyclerViewExpandableItemManager = null;
        }

        if (mWrappedAdapter != null) {
            WrapperAdapterUtils.releaseAll(mWrappedAdapter);
            mWrappedAdapter = null;
        }

        if (mRecyclerView != null) {
            mRecyclerView.setItemAnimator(null);
            mRecyclerView.setAdapter(null);
            mRecyclerView = null;
        }

        super.onDestroy();
    }

    @Override
    public void onChildClick(int position) {
        long packedPosition = mRecyclerViewExpandableItemManager.getExpandablePosition(position);

        int groupPosition = RecyclerViewExpandableItemManager
                .getPackedPositionGroup(packedPosition);
        int childPosition = RecyclerViewExpandableItemManager
                .getPackedPositionChild(packedPosition);

        onChildClick(groupPosition, childPosition);
    }

    private void onChildClick(int groupPosition, int childPosition) {
        Toast.makeText(this, "Group: " + groupPosition + " , Child: "  + childPosition,
                Toast.LENGTH_SHORT).show();
    }

    private List<RecyclerGroup<?, ?>> buildDataSource() {
        GroupTitleBinder groupTitleBinder = new GroupTitleBinder();
        ListItem2Binder item2Binder = new ListItem2Binder();
        ListItem3Binder item3Binder = new ListItem3Binder();

        List<RecyclerGroup<?, ?>> list = new ArrayList<>();

        // =============================
        // Group 1
        List<SingleItemBinder<?>> group1Items = new ArrayList<>();

        group1Items.add(new SingleItemBinder<>(
                new ListItem1Binder("Item 1"),
                new ListItem1(R.drawable.ic_group, "This is the item with fixed title"),
                R.layout.list_item_1, 0
        ));

        group1Items.add(new SingleItemBinder<>(
                item2Binder,
                new ListItem2(Color.RED, "Item 2", "This is the item with type 2"),
                R.layout.list_item_2, 0
        ));

        group1Items.add(new SingleItemBinder<>(
                item3Binder,
                new ListItem3(R.drawable.ic_image, "Item 3", "This is the item with type 3"),
                R.layout.list_item_1, 0
        ));

        list.add(new SingleItemsRecyclerGroup<>(
                "Custom items",
                groupTitleBinder,
                R.layout.list_item_group_title,
                group1Items
        ));

        // =============================
        // Group 2

        final List<ListItem2> group2Items = new ArrayList<>();

        group2Items.add(new ListItem2(Color.BLACK, "Black", "Black Color"));
        group2Items.add(new ListItem2(Color.BLUE, "Blue", "Blue Color"));
        group2Items.add(new ListItem2(Color.CYAN, "Cyan", "Cyan Color"));
        group2Items.add(new ListItem2(Color.GREEN, "Green", "Green Color"));

        list.add(new AbsDraggableRecyclerGroup<String, ListItem2>(
                "List of Draggable Colors", groupTitleBinder, R.layout.list_item_group_title,
                group2Items, item2Binder, R.layout.list_item_2
        ) {
            @Override
            public void onMoveChildItem(int fromPosition, int toPosition) {
                group2Items.add(toPosition, group2Items.remove(fromPosition));
            }
        });

        // =============================
        // Group 3

        final List<ListItem3> group3Items = new ArrayList<>();
        group3Items.add(new ListItem3(R.drawable.ic_matrix, "Matrix", "Matrix icon"));
        group3Items.add(new ListItem3(R.drawable.ic_image, "Image", "Image icon"));
        group3Items.add(new ListItem3(R.drawable.ic_layers, "Layers", "Layers icon"));

        list.add(new AbsDraggableRecyclerGroup<String, ListItem3>(
                "List of Draggable Icons", groupTitleBinder, R.layout.list_item_group_title,
                group3Items, item3Binder, R.layout.list_item_1
        ) {
            @Override
            public void onMoveChildItem(int fromPosition, int toPosition) {
                group3Items.add(toPosition, group3Items.remove(fromPosition));
            }
        });

        return list;
    }
}
