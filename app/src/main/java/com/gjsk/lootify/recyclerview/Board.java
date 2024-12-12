package com.gjsk.lootify.recyclerview;

public class Board {
    private String type;
    private String name;
    private String detail;

    public Board(String type, String name, String detail, boolean isDelete){
        this.type = type;
        this.name = name;
        this.detail = detail;
    }

    public String getType(){
        return type;
    }

    public String getName(){
        return name;
    }

    public String getDetail(){
        return detail;
    }
}
