package com.example.libraryweb.vo;

import org.springframework.stereotype.Component;

@Component
public class UserVO {

    private String grade;
    private int no;
    private String uid;
    private String pwd;
    private String name;
    private String birth;
    private String gender;
    private String email;
    private String phone;
    private String address;

    public UserVO() {
    }

    public UserVO(String grade, int no, String uid, String pwd, String name, String birth, String gender, String email, String phone, String address) {
        this.grade = grade;
        this.no = no;
        this.uid = uid;
        this.pwd = pwd;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}