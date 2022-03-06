package com.example.libraryweb.vo;

public class BoardVO {
    private int id;
    private String title;
    private String content;
    private String writer;
    private int view_cnt;
    private String section;
    private String secret_yn;
    private String delete_yn;
    private String insert_time;
    private String update_time;
    private String delete_time;

    public BoardVO() {
    }

    public BoardVO(int id, String title, String content, String writer, int view_cnt,
                   String section, String secret_yn, String delete_yn, String insert_time,
                   String update_time, String delete_time) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.view_cnt = view_cnt;
        this.section = section;
        this.secret_yn = secret_yn;
        this.delete_yn = delete_yn;
        this.insert_time = insert_time;
        this.update_time = update_time;
        this.delete_time = delete_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getView_cnt() {
        return view_cnt;
    }

    public void setView_cnt(int view_cnt) {
        this.view_cnt = view_cnt;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSecret_yn() {
        return secret_yn;
    }

    public void setSecret_yn(String secret_yn) {
        this.secret_yn = secret_yn;
    }

    public String getDelete_yn() {
        return delete_yn;
    }

    public void setDelete_yn(String delete_yn) {
        this.delete_yn = delete_yn;
    }

    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getDelete_time() {
        return delete_time;
    }

    public void setDelete_time(String delete_time) {
        this.delete_time = delete_time;
    }
}
