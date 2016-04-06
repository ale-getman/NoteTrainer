package com.android.ag.notetrainer;

/**
 * Created by User on 06.04.2016.
 */
public class DrawerItem {

    String ItemName;
    int imgResID;
    String title;

    public DrawerItem(String itemName) {
        super();
        ItemName = itemName;
    }

    public DrawerItem(String title, int imgResID) {
        this(null);
        this.title = title;
        this.imgResID = imgResID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public int getImgResID() {
        return imgResID;
    }

    public void setImgResID(int imgResID) {
        this.imgResID = imgResID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
