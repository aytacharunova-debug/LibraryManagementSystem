package com.library.service;

import com.library.daoImpl.BookDao;
import com.library.daoImpl.BorrowsDao;
import com.library.model.Book;
import com.library.model.Borrows;
import java.time.LocalDate;

public class BorrowsService {
    private BorrowsDao borrowsDao = new BorrowsDao();
    private BookDao bookDao = new BookDao();

    // KİTABI ÖDÜNC VERMƏK
    public void lendBook(int bookId, int memberId) {
        Book book = bookDao.getById(bookId);

        if (book != null && book.getStatus().equalsIgnoreCase("Available")) {
            // 1. Kitabın statusunu dəyişirik
            book.setStatus("Borrowed");
            bookDao.update(book);

            // 2. Borrows qeydi yaradırıq
            Borrows borrow = new Borrows();
            borrow.setBookId(bookId);
            borrow.setMemberId(memberId);
            borrow.setBorrowDate(LocalDate.now());
            borrow.setDueDate(LocalDate.now().plusDays(14)); // 2 həftə vaxt verilir
            
            borrowsDao.add(borrow);
            System.out.println("Kitab uğurla üzvə verildi. Qaytarma tarixi: " + borrow.getDueDate());
        } else {
            System.out.println("Xəta: Kitab tapılmadı və ya artıq götürülüb!");
        }
    }

    // KİTABI QAYTARMAQ
    public void returnBook(int borrowId) {
        Borrows borrow = borrowsDao.getById(borrowId);

        if (borrow != null && borrow.getReturnDate() == null) {
            // 1. Qaytarma tarixini bu gün edirik
            borrow.setReturnDate(LocalDate.now());
            borrowsDao.update(borrow);

            // 2. Kitabın statusunu 'Available' edirik
            Book book = bookDao.getById(borrow.getBookId());
            book.setStatus("Available");
            bookDao.update(book);

            System.out.println("Kitab uğurla geri alındı.");
        } else {
            System.out.println("Xəta: Belə bir aktiv götürmə qeydi tapılmadı!");
        }
    }
}