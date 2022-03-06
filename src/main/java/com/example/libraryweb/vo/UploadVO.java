package com.example.libraryweb.vo;

public class UploadVO {
    private int id;
    private String uid;
    private String imageUrl;
    private int pid;

    public UploadVO() {
    }

    public UploadVO(int id, String uid, String imageUrl, int pid) {
        this.id = id;
        this.uid = uid;
        this.imageUrl = imageUrl;
        this.pid = pid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
