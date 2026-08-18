package com.library.daoImpl;

import com.library.daoInterface.IFine;
import com.library.model.Fine;
import com.library.util.DBconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FineDao implements IFine {

    @Override
    public void add(Fine fine) {
        String sql = "INSERT INTO fine (borrows_id, member_id, amount, fine_date, is_paid) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, fine.getBorrowsId());
            pstmt.setInt(2, fine.getMemberId());
            pstmt.setDouble(3, fine.getAmount());
            
            // fineDate null ola bilərsə yoxlanış
            if (fine.getFineDate() != null) {
                pstmt.setDate(4, java.sql.Date.valueOf(fine.getFineDate()));
            } else {
                pstmt.setNull(4, Types.DATE);
            }
            
            pstmt.setBoolean(5, fine.getisPaid());

            pstmt.executeUpdate();
            System.out.println("Cərimə uğurla əlavə edildi!");

        } catch (SQLException e) {
            System.out.println("Cərimə əlavə edilərkən xəta: " + e.getMessage());
        }
    }

    @Override
    public void update(Fine fine) {
        String sql = "UPDATE fine SET borrows_id = ?, member_id = ?, amount = ?, fine_date = ?, is_paid = ? WHERE fine_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, fine.getBorrowsId());
            pstmt.setInt(2, fine.getMemberId());
            pstmt.setDouble(3, fine.getAmount());
            pstmt.setDate(4, java.sql.Date.valueOf(fine.getFineDate()));
            pstmt.setBoolean(5, fine.getisPaid());
            pstmt.setInt(6, fine.getFineId());

            pstmt.executeUpdate();
            System.out.println("Cərimə məlumatı yeniləndi!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM fine WHERE fine_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Cərimə silindi!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Fine getById(int id) {
        Fine fine = null;
        String sql = "SELECT * FROM fine WHERE fine_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    fine = mapResultSetToFine(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fine;
    }

    @Override
    public List<Fine> getAll() {
        List<Fine> fines = new ArrayList<>();
        String sql = "SELECT * FROM fine";
        try (Connection conn = DBconnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                fines.add(mapResultSetToFine(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fines;
    }

    // Köməkçi metod: ResultSet -> Fine obyektinə çevirmək
    private Fine mapResultSetToFine(ResultSet rs) throws SQLException {
        Fine fine = new Fine();
        fine.setFineId(rs.getInt("fine_id"));
        fine.setBorrowsId(rs.getInt("borrows_id"));
        fine.setMemberId(rs.getInt("member_id"));
        fine.setAmount(rs.getDouble("amount"));
        
        // Tarixi oxuyuruq
        Date date = rs.getDate("fine_date");
        if (date != null) {
            fine.setFineDate(date.toLocalDate());
        }
        
        // is_paid (BIT) -> boolean
        fine.setisPaid(rs.getBoolean("is_paid"));
        
        return fine;
    }
} 