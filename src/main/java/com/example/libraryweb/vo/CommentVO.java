package com.example.libraryweb.vo;

public class CommentVO {
    private int parent_id;
    private int id;
    private String comment;
    private String writer;
    private String section;
    private String delete_yn;
    private String time;

    public CommentVO() {
    }

    public CommentVO(int parent_id, int id, String comment, String writer, String section, String delete_yn, String time) {
        this.parent_id = parent_id;
        this.id = id;
        this.comment = comment;
        this.writer = writer;
        this.section = section;
        this.delete_yn = delete_yn;
        this.time = time;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDelete_yn() {
        return delete_yn;
    }

    public void setDelete_yn(String delete_yn) {
        this.delete_yn = delete_yn;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
