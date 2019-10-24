package com.lying.recyclerviewdemo.fenzu;

public class Fenzu {

    private int id;

    private String name;

    private int position;

    public Fenzu(int id, String name, int position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "{\"Fenzu\":{"
                + "\"id\":"
                + id
                + ",\"name\":\""
                + name + '\"'
                + ",\"position\":"
                + position
                + "}}";

    }
}
