package org.dontpanic.spanners.dao;

public class Spanner {

    public static final String PROP_ID = "id";
    public static final String PROP_NAME = "name";
    public static final String PROP_SIZE = "size";

    private int id;
    private String name;
    private int size;
    private String owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
