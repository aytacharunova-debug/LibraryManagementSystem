package com.library.model;

import java.time.LocalDate;

public class Employee {
    private int employeeId;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String username;
    private String password;
    private String position;
    private LocalDate hireDate;
    private double salary;
    private int age;

    // 1. Boş Constructor
    public Employee() {
    }

    // 2. Tam Constructor
    public Employee(int employeeId, String name, String surname, String phone, String email, 
                    String username, String password, String position, 
                    LocalDate hireDate, double salary, int age) {
        this.employeeId = employeeId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.position = position;
        this.hireDate = hireDate;
        this.salary = salary;
        this.age = age;
    }

    // 3. Getter və Setter-lər
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // 4. toString() metodu
    @Override
    public String toString() {
        return "İşçi [ID=" + employeeId + ", Ad=" + name + ", Vəzifə=" + position + ", Maaş=" + salary + "]";
    }
}