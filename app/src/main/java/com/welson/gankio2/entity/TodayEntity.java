package com.welson.gankio2.entity;

import java.util.ArrayList;

public class TodayEntity {

    private ArrayList<String> category;
    private boolean error;
    private Result results;

    public ArrayList<String> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Result getResults() {
        return results;
    }

    public void setResults(Result results) {
        this.results = results;
    }

    public class Result {

        private ArrayList<GankEntity> android;
        private ArrayList<GankEntity> app;
        private ArrayList<GankEntity> ios;
        private ArrayList<GankEntity> ristMovie;
        private ArrayList<GankEntity> front;
        private ArrayList<GankEntity> expand;
        private ArrayList<GankEntity> recommendBlind;
        private ArrayList<GankEntity> fuli;

        public ArrayList<GankEntity> getAndroid() {
            return android;
        }

        public void setAndroid(ArrayList<GankEntity> android) {
            this.android = android;
        }

        public ArrayList<GankEntity> getApp() {
            return app;
        }

        public void setApp(ArrayList<GankEntity> app) {
            this.app = app;
        }

        public ArrayList<GankEntity> getIos() {
            return ios;
        }

        public void setIos(ArrayList<GankEntity> ios) {
            this.ios = ios;
        }

        public ArrayList<GankEntity> getRistMovie() {
            return ristMovie;
        }

        public void setRistMovie(ArrayList<GankEntity> ristMovie) {
            this.ristMovie = ristMovie;
        }

        public ArrayList<GankEntity> getFront() {
            return front;
        }

        public void setFront(ArrayList<GankEntity> front) {
            this.front = front;
        }

        public ArrayList<GankEntity> getExpand() {
            return expand;
        }

        public void setExpand(ArrayList<GankEntity> expand) {
            this.expand = expand;
        }

        public ArrayList<GankEntity> getRecommendBlind() {
            return recommendBlind;
        }

        public void setRecommendBlind(ArrayList<GankEntity> recommendBlind) {
            this.recommendBlind = recommendBlind;
        }

        public ArrayList<GankEntity> getFuli() {
            return fuli;
        }

        public void setFuli(ArrayList<GankEntity> fuli) {
            this.fuli = fuli;
        }
    }
}
