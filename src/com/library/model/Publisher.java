package com.library.model;

public class Publisher {
    private int publisherId;
    private String name;
    private String country;
    private String phone;

    // 1. Boş Constructor
    public Publisher() {
    }

    // 2. Tam Constructor
    public Publisher(int publisherId, String name, String country, String phone) {
        this.publisherId = publisherId;
        this.name = name;
        this.country = country;
        this.phone = phone;
    }

    // 3. Getter və Setter-lər
    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // 4. toString() metodu
    @Override
    public String toString() {
        return "Nəşriyyat: " + name + " (Ölkə: " + (country != null ? country : "Qeyd edilməyib") + ")";
    }
}