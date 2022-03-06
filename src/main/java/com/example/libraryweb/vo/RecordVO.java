package com.example.libraryweb.vo;

import org.springframework.stereotype.Component;

@Component
public class RecordVO {
    private int id;
    private String isbn;
    private String uid;
    private String title;
    private String author;
    private String publisher;
    private String imageUrl;
    private String rent_date;
    private String return_time;
    private String rent_time;
    private String delivery;
    private int reserve;
    private String reserve_time;
    private String done;

    public RecordVO() {
    }

    public RecordVO(int id, String isbn, String uid, String title, String author,
                    String publisher, String imageUrl, String rent_date, String return_time,
                    String rent_time, String delivery, int reserve, String reserve_time, String done) {
        this.id = id;
        this.isbn = isbn;
        this.uid = uid;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.imageUrl = imageUrl;
        this.rent_date = rent_date;
        this.return_time = return_time;
        this.rent_time = rent_time;
        this.delivery = delivery;
        this.reserve = reserve;
        this.reserve_time = reserve_time;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRent_date() {
        return rent_date;
    }

    public void setRent_date(String rent_date) {
        this.rent_date = rent_date;
    }

    public String getReturn_time() {
        return return_time;
    }

    public void setReturn_time(String return_time) {
        this.return_time = return_time;
    }

    public String getRent_time() {
        return rent_time;
    }

    public void setRent_time(String rent_time) {
        this.rent_time = rent_time;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public int getReserve() {
        return reserve;
    }

    public void setReserve(int reserve) {
        this.reserve = reserve;
    }

    public String getReserve_time() {
        return reserve_time;
    }

    public void setReserve_time(String reserve_time) {
        this.reserve_time = reserve_time;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }
}
