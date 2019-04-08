package com.welson.gankio2.entity;

import java.util.ArrayList;

public class CategoryEntity {
    private boolean error;
    private ArrayList<GankEntity> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ArrayList<GankEntity> getResults() {
        return results;
    }

    public void setResults(ArrayList<GankEntity> results) {
        this.results = results;
    }
}
