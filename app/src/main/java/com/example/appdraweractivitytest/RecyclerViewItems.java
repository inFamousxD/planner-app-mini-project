package com.example.appdraweractivitytest;

public class RecyclerViewItems {
    private String mTaskTitleRV;
    private String mTaskDescRV;
    private int mImageResource;
    private String mStatus;

    public RecyclerViewItems(int imageResource, String taskTitleRV, String taskDescRV, String status) {
        this.mImageResource = imageResource;
        this.mTaskTitleRV = taskTitleRV;
        this.mTaskDescRV = taskDescRV;
        this.mStatus = status;
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

    public String getStatus() {
        return mStatus;
    }
}
