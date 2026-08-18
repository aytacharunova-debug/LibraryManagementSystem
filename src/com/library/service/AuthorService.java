package com.library.service;

import com.library.daoImpl.AuthorDao;
import com.library.model.Author;
import java.util.List;

public class AuthorService {
    private AuthorDao authorDao = new AuthorDao();

    public void addAuthor(Author author) {
        authorDao.add(author);
    }

    public List<Author> getAllAuthors() {
        return authorDao.getAll();
    }

    public void deleteAuthor(int id) {
        authorDao.delete(id);
    }
}