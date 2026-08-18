package com.library.model;

import java.time.LocalDate;

public class Fine {
    private int fineId;
    private int borrowsId; // Sadə rəqəm ID
    private int memberId;  // Sadə rəqəm ID
    private double amount; // decimal(10,2) üçün double
    private LocalDate fineDate;
    private boolean isPaid; // bit üçün boolean

    // 1. Boş Constructor
    public Fine() {
    }

    // 2. Tam Constructor
    public Fine(int fineId, int borrowsId, int memberId, double amount, LocalDate fineDate, boolean isPaid) {
        this.fineId = fineId;
        this.borrowsId = borrowsId;
        this.memberId = memberId;
        this.amount = amount;
        this.fineDate = fineDate;
        this.isPaid = isPaid;
    }

    // 3. Getter və Setter-lər
    public int getFineId() {
        return fineId;
    }

    public void setFineId(int fineId) {
        this.fineId = fineId;
    }

    public int getBorrowsId() {
        return borrowsId;
    }

    public void setBorrowsId(int borrowsId) {
        this.borrowsId = borrowsId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getFineDate() {
        return fineDate;
    }

    public void setFineDate(LocalDate fineDate) {
        this.fineDate = fineDate;
    }

    public boolean getisPaid() {
        return isPaid;
    }

    public void setisPaid(boolean paid) {
        isPaid = paid;
    }

    // 4. toString() metodu
    @Override
    public String toString() {
        return "Cərimə [ID=" + fineId + ", Məbləğ=" + amount + ", Ödənilib=" + (isPaid ? "Bəli" : "Xeyr") + "]";
    }
}