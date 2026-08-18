package com.library.model;

public class Member {
    private int memberId;
    private String name;
    private String surname;
    private String email;
    private String phone;

   
    public Member() {
    }

    public Member(int memberId, String name, String surname, String email, String phone) {
        this.memberId = memberId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

  
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String toString() {
        return "Üzv: " + name + " " + surname + " | Email: " + (email != null ? email : "Yoxdur");
    }
}