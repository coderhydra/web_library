package com.example.libraryweb.vo;

public class BookVO {

    private String isbn;
    private String title;
    private String author;
    private String translator;
    private String imageUrl;
    private String publisher;
    private String pubDate;
    private String description;
    private Long reviewCount;
    private Object reviewRank;
    private boolean rent;
    private int reserve;


    public BookVO() {
    }

    public BookVO(String isbn, String title, String author, String translator, String imageUrl, String publisher, String pubDate, String description, Long reviewCount, Object reviewRank, boolean rent, int reserve) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.translator = translator;
        this.imageUrl = imageUrl;
        this.publisher = publisher;
        this.pubDate = pubDate;
        this.description = description;
        this.reviewCount = reviewCount;
        this.reviewRank = reviewRank;
        this.rent = rent;
        this.reserve = reserve;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Long reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Object getReviewRank() {
        return reviewRank;
    }

    public void setReviewRank(Object reviewRank) {
        this.reviewRank = reviewRank;
    }

    public boolean isRent() {
        return rent;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }

    public int getReserve() {
        return reserve;
    }

    public void setReserve(int reserve) {
        this.reserve = reserve;
    }
}
