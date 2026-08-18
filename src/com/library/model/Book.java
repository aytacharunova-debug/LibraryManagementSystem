package com.library.model;

public class Book {
    private int bookId;
    private String title;
    private int authorId;      // Obyekt yox, sadəcə ID
    private int categoryId;    // Obyekt yox, sadəcə ID
    private int publisherId;   // Obyekt yox, sadəcə ID
    private String isbn;
    private int publishYear;
    private String status;

    public Book() {}

    public Book(int bookId, String title, int authorId, int categoryId, int publisherId, String isbn, int publishYear, String status) {
        this.bookId = bookId;
        this.title = title;
        this.authorId = authorId;
        this.categoryId = categoryId;
        this.publisherId = publisherId;
        this.isbn = isbn;
        this.publishYear = publishYear;
        this.status = status;
    }

    // Getter və Setter-lər
    public int getBookId() { 
    	return bookId; }
    
    public void setBookId(int bookId) { 
    	this.bookId = bookId; }

    public String getTitle() {
    	return title; }
    
    public void setTitle(String title) { 
    	this.title = title; }

    public int getAuthorId() {
    	return authorId; }
    
    public void setAuthorId(int authorId) { 
    	this.authorId = authorId; }

    public int getCategoryId() { 
    	return categoryId; }
    
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public int getPublisherId() {
    	return publisherId; }
    
    public void setPublisherId(int publisherId) { 
    	this.publisherId = publisherId; }

    public String getIsbn() {
    	return isbn; }
    
    public void setIsbn(String isbn) {
    	this.isbn = isbn; }

    public int getPublishYear() {
    	return publishYear; }
    
    public void setPublishYear(int publishYear) { 
    	this.publishYear = publishYear; }

    public String getStatus() {
    	return status; }
    
    public void setStatus(String status) {
    	this.status = status; }

    @Override
    public String toString() {
        return "Book [ID=" + bookId + ", Title=" + title + ", AuthorID=" + authorId + ", Status=" + status + "]";
    }
}