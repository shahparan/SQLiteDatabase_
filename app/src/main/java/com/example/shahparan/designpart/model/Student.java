package com.example.shahparan.designpart.model;

/**
 * Created by Shah Paran on 03-Feb-18.
 */

public class Student {

    private String name;
    private String phone;
    private String email;
    private String cgpa;


    public Student(String name, String phone,String email,String cgpa) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.cgpa = cgpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
