package com.example.appdraweractivitytest;

public class RecyclerViewItems {
    private String mTaskTitleRV;
    private String mTaskDescRV;
    private int mImageResource;

    public RecyclerViewItems(int imageResource, String taskTitleRV, String taskDescRV) {
        this.mImageResource = imageResource;
        this.mTaskTitleRV = taskTitleRV;
        this.mTaskDescRV = taskDescRV;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getTaskDescRV() {
        return mTaskDescRV;
    }

    public String getTaskTitleRV() {
        return mTaskTitleRV;
    }
}
