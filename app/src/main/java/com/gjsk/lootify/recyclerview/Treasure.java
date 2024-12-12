package com.gjsk.lootify.recyclerview;

public class Treasure {
    private final String name;
    private final int iconRes;

    public Treasure(String name, int iconRes){
        this.name = name;
        this.iconRes = iconRes;
    }

    public String getName(){
        return name;
    }

    public int getIconRes(){
        return iconRes;
    }
}
