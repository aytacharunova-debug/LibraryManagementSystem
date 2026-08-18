package com.library.daoImpl;

import com.library.daoInterface.IBorrows;
import com.library.model.Borrows;
import com.library.util.DBconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowsDao implements IBorrows {

    @Override
    public void add(Borrows borrows) {
        String sql = "INSERT INTO borrows (book_id, member_id, borrow_date, due_date, return_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, borrows.getBookId());
            pstmt.setInt(2, borrows.getMemberId());
            pstmt.setDate(3, java.sql.Date.valueOf(borrows.getBorrowDate()));
            pstmt.setDate(4, java.sql.Date.valueOf(borrows.getDueDate()));
            
            // return_date null ola bilər (kitab hələ qaytarılmayıbsa)
            if (borrows.getReturnDate() != null) {
                pstmt.setDate(5, java.sql.Date.valueOf(borrows.getReturnDate()));
            } else {
                pstmt.setNull(5, Types.DATE);
            }

            pstmt.executeUpdate();
            System.out.println("Götürmə qeydi uğurla yaradıldı!");

        } catch (SQLException e) {
            System.out.println("Əlavə zamanı xəta: " + e.getMessage());
        }
    }

    @Override
    public void update(Borrows borrows) {
        String sql = "UPDATE borrows SET book_id = ?, member_id = ?, borrow_date = ?, due_date = ?, return_date = ? WHERE borrows_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, borrows.getBookId());
            pstmt.setInt(2, borrows.getMemberId());
            pstmt.setDate(3, java.sql.Date.valueOf(borrows.getBorrowDate()));
            pstmt.setDate(4, java.sql.Date.valueOf(borrows.getDueDate()));

            if (borrows.getReturnDate() != null) {
                pstmt.setDate(5, java.sql.Date.valueOf(borrows.getReturnDate()));
            } else {
                pstmt.setNull(5, Types.DATE);
            }
            
            pstmt.setInt(6, borrows.getBorrowsId());

            pstmt.executeUpdate();
            System.out.println("Qeyd yeniləndi!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM borrows WHERE borrows_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Qeyd silindi!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Borrows getById(int id) {
        Borrows borrows = null;
        String sql = "SELECT * FROM borrows WHERE borrows_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    borrows = mapResultSetToBorrows(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }

    @Override
    public List<Borrows> getAll() {
        List<Borrows> borrowsList = new ArrayList<>();
        String sql = "SELECT * FROM borrows";
        try (Connection conn = DBconnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                borrowsList.add(mapResultSetToBorrows(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowsList;
    }

    // ResultSet-dən gələn datanı Borrows obyektinə çevirən köməkçi metod
    private Borrows mapResultSetToBorrows(ResultSet rs) throws SQLException {
        Borrows borrows = new Borrows();
        borrows.setBorrowsId(rs.getInt("borrows_id"));
        borrows.setBookId(rs.getInt("book_id"));
        borrows.setMemberId(rs.getInt("member_id"));
        
        // Tarixləri LocalDate-ə çeviririk
        borrows.setBorrowDate(rs.getDate("borrow_date").toLocalDate());
        borrows.setDueDate(rs.getDate("due_date").toLocalDate());
        
        // return_date null olub-olmadığını yoxlayırıq
        Date returnDate = rs.getDate("return_date");
        if (returnDate != null) {
            borrows.setReturnDate(returnDate.toLocalDate());
        }
        
        return borrows;
    }
}