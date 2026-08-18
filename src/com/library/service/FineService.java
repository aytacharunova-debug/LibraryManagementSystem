package com.library.service;

import com.library.daoImpl.FineDao;
import com.library.model.Fine;
import com.library.model.Borrows;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineService {
    private FineDao fineDao = new FineDao();

    public void calculateAndAddFine(Borrows borrow) {
        // Əgər kitab qaytarılıbsa və gecikmə varsa
        if (borrow.getReturnDate() != null && borrow.getReturnDate().isAfter(borrow.getDueDate())) {
            
            long daysLate = ChronoUnit.DAYS.between(borrow.getDueDate(), borrow.getReturnDate());
            double fineAmount = daysLate * 0.20; // Hər gün üçün 20 qəpik cərimə

            Fine fine = new Fine();
            fine.setBorrowsId(borrow.getBorrowsId());
            fine.setMemberId(borrow.getMemberId());
            fine.setAmount(fineAmount);
            fine.setFineDate(LocalDate.now());
            fine.setisPaid(false);

            fineDao.add(fine);
            System.out.println("Gecikmə üçün cərimə yaradıldı: " + fineAmount + " AZN");
        }
    }
}