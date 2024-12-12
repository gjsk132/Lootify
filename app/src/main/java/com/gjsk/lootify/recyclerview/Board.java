package com.gjsk.lootify.recyclerview;

public class Board {
    private final String name;
    private final String detail;

    public Board(String name, String detail){
        this.name = name;
        this.detail = detail;
    }

    public String getName(){
        return name;
    }

    public String getDetail(){
        return detail;
    }
}
