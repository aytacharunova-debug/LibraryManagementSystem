package com.library.daoImpl;

import com.library.daoInterface.IBook;
import com.library.model.Book;
import com.library.util.DBconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements IBook {

    @Override
    public void add(Book book) {
        String sql = "INSERT INTO book (title, author_id, category_id, publisher_id, isbn, publish_year, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setInt(2, book.getAuthorId());
            pstmt.setInt(3, book.getCategoryId());
            pstmt.setInt(4, book.getPublisherId());
            pstmt.setString(5, book.getIsbn());
            pstmt.setInt(6, book.getPublishYear());
            pstmt.setString(7, book.getStatus());

            pstmt.executeUpdate();
            System.out.println("Kitab uğurla əlavə edildi!");

        } catch (SQLException e) {
            System.out.println("Kitab əlavə edilərkən xəta: " + e.getMessage());
        }
    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE book SET title = ?, author_id = ?, category_id = ?, publisher_id = ?, isbn = ?, publish_year = ?, status = ? WHERE book_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setInt(2, book.getAuthorId());
            pstmt.setInt(3, book.getCategoryId());
            pstmt.setInt(4, book.getPublisherId());
            pstmt.setString(5, book.getIsbn());
            pstmt.setInt(6, book.getPublishYear());
            pstmt.setString(7, book.getStatus());
            pstmt.setInt(8, book.getBookId());

            pstmt.executeUpdate();
            System.out.println("Kitab məlumatları yeniləndi!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM book WHERE book_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Kitab silindi!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book getById(int id) {
        Book book = null;
        String sql = "SELECT * FROM book WHERE book_id = ?";
        
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    book = mapResultSetToBook(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book";

        try (Connection conn = DBconnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                books.add(mapResultSetToBook(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Kod təkrarının qarşısını almaq üçün köməkçi metod
    private Book mapResultSetToBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getInt("book_id"));
        book.setTitle(rs.getString("title"));
        book.setAuthorId(rs.getInt("author_id"));
        book.setCategoryId(rs.getInt("category_id"));
        book.setPublisherId(rs.getInt("publisher_id"));
        book.setIsbn(rs.getString("isbn"));
        book.setPublishYear(rs.getInt("publish_year"));
        book.setStatus(rs.getString("status"));
        return book;
    }
}