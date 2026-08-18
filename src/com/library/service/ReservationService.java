package com.library.service;

import com.library.daoImpl.ReservationDao;
import com.library.model.Reservation;
import java.time.LocalDateTime;

public class ReservationService {
    private ReservationDao reservationDao = new ReservationDao();

    public void createReservation(int memberId, int bookId) {
        Reservation res = new Reservation();
        res.setMemberId(memberId);
        res.setBookId(bookId);
        res.setReservationDate(LocalDateTime.now());
        res.setStatus("Pending");
        
        reservationDao.add(res);
        System.out.println("Rezervasiya qeydə alındı.");
    }
}