package com.welson.gankio2.entity;

import java.util.ArrayList;

public class HistoryEntity {
    private boolean error;
    private ArrayList<String> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ArrayList<String> getResults() {
        return results;
    }

    public void setResults(ArrayList<String> results) {
        this.results = results;
    }
}
