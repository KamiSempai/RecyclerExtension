package ru.pocketbyte.example.model;

import ru.pocketbyte.recyclerext.adapter.group.BaseRecyclerGroup;

/**
 * @author Denis Shurygin
 */

public class ListItem3 implements BaseRecyclerGroup.ChildItem {

    private static long sLastId = 1;
    private static synchronized long newId() {
        return sLastId++;
    }

    public final int icon;
    public final String title;
    public final String message;

    private long mId = newId();

    public ListItem3(int icon, String title, String message) {
        this.icon = icon;
        this.title = title;
        this.message = message;
    }

    @Override
    public long getId() {
        return mId;
    }
}