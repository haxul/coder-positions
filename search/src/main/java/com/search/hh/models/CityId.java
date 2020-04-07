package com.search.hh.models;

public enum CityId {
    SAMARA(78);

    private int id;

    CityId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
