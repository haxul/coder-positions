package com.search.hh.models;

public enum City {
    SAMARA(78);

    private int id;

    City(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
