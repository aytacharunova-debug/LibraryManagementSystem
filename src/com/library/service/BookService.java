package com.library.service;

import com.library.daoImpl.BookDao;
import com.library.model.Book;
import java.util.List;

public class BookService {
    public class FineService {

	}

	private BookDao bookDao = new BookDao();

    public void addBook(Book book) {
        // Yeni əlavə edilən kitabın statusu həmişə 'Available' olmalıdır
        book.setStatus("Available");
        bookDao.add(book);
    }

    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    public Book getBookById(int id) {
        return bookDao.getById(id);
    }
}