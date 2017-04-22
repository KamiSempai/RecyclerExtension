package ru.pocketbyte.example.model;

import ru.pocketbyte.recyclerext.adapter.group.BaseRecyclerGroup;

/**
 * @author Denis Shurygin
 */

public class ListItem2 implements BaseRecyclerGroup.ChildItem {

    private static long sLastId = 1;
    private static synchronized long newId() {
        return sLastId++;
    }

    public final int color;
    public final String title;
    public final String message;

    private long mId = newId();

    public ListItem2(int color, String title, String message) {
        this.color = color;
        this.title = title;
        this.message = message;
    }

    @Override
    public long getId() {
        return mId;
    }
}
