package com.library.model;

import java.time.LocalDateTime;

public class Reservation {
    private int reservationId;
    private int memberId;      // Sadə rəqəm ID
    private int bookId;        // Sadə rəqəm ID
    private LocalDateTime reservationDate; // Tarix və Saat
    private String status;

    // 1. Boş Constructor
    public Reservation() {
    }

    // 2. Tam Constructor
    public Reservation(int reservationId, int memberId, int bookId, LocalDateTime reservationDate, String status) {
        this.reservationId = reservationId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    // 3. Getter və Setter-lər
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // 4. toString() metodu
    @Override
    public String toString() {
        return "Rezervasiya [ID=" + reservationId + ", ÜzvID=" + memberId + ", KitabID=" + bookId + ", Status=" + status + "]";
    }
}