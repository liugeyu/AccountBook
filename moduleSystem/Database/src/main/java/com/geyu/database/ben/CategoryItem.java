package com.geyu.database.ben;

public class CategoryItem {
    public String uniqueName;
    public String name = "";
    public String icon = "";
    public int type;

    public CategoryItem(String uniqueName, String name, String icon) {
        this.uniqueName = uniqueName;
        this.name = name;
        this.icon = icon;
    }
}
