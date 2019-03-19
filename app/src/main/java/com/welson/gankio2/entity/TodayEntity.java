package com.welson.gankio2.entity;

import com.google.gson.annotations.SerializedName;

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

        private ArrayList<GankEntity> Android;
        private ArrayList<GankEntity> App;
        private ArrayList<GankEntity> iOS;
        @SerializedName("休息视频")
        private ArrayList<GankEntity> ristMovie;
        @SerializedName("前端")
        private ArrayList<GankEntity> front;
        @SerializedName("拓展资源")
        private ArrayList<GankEntity> expand;
        @SerializedName("瞎推荐")
        private ArrayList<GankEntity> recommendBlind;
        @SerializedName("福利")
        private ArrayList<GankEntity> fuli;

        public ArrayList<GankEntity> getAndroid() {
            return Android;
        }

        public void setAndroid(ArrayList<GankEntity> android) {
            Android = android;
        }

        public ArrayList<GankEntity> getApp() {
            return App;
        }

        public void setApp(ArrayList<GankEntity> app) {
            App = app;
        }

        public ArrayList<GankEntity> getiOS() {
            return iOS;
        }

        public void setiOS(ArrayList<GankEntity> iOS) {
            this.iOS = iOS;
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
