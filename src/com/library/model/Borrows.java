package com.library.model;

import java.time.LocalDate;

public class Borrows {
    private int borrowsId;
    private int bookId;      // Sadəcə rəqəm ID
    private int memberId;    // Sadəcə rəqəm ID
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public Borrows() {}

    public Borrows(int borrowsId, int bookId, int memberId, LocalDate borrowDate, LocalDate dueDate, LocalDate returnDate) {
        this.borrowsId = borrowsId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    // Getter və Setter-lər
    
    
	public int getBorrowsId() {
		return borrowsId;
	}

	public void setBorrowsId(int borrowsId) {
		this.borrowsId = borrowsId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public LocalDate getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	@Override
    public String toString() {
        return "Borrow [ID=" + borrowsId + ", BookID=" + bookId + ", MemberID=" + memberId + "]";
    }

}