package com.library.daoImpl;

import com.library.daoInterface.IReservation;
import com.library.model.Reservation;
import com.library.util.DBconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao implements IReservation {

    @Override
    public void add(Reservation reservation) {
        String sql = "INSERT INTO reservation (member_id, book_id, reservation_date, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, reservation.getMemberId());
            pstmt.setInt(2, reservation.getBookId());
            
            // LocalDateTime -> Timestamp çevrilməsi
            if (reservation.getReservationDate() != null) {
                pstmt.setTimestamp(3, Timestamp.valueOf(reservation.getReservationDate()));
            } else {
                pstmt.setNull(3, Types.TIMESTAMP);
            }
            
            pstmt.setString(4, reservation.getStatus());

            pstmt.executeUpdate();
            System.out.println("Rezervasiya uğurla əlavə edildi!");

        } catch (SQLException e) {
            System.out.println("Rezervasiya zamanı xəta: " + e.getMessage());
        }
    }

    @Override
    public void update(Reservation reservation) {
        String sql = "UPDATE reservation SET member_id = ?, book_id = ?, reservation_date = ?, status = ? WHERE reservation_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, reservation.getMemberId());
            pstmt.setInt(2, reservation.getBookId());
            
            if (reservation.getReservationDate() != null) {
                pstmt.setTimestamp(3, Timestamp.valueOf(reservation.getReservationDate()));
            } else {
                pstmt.setNull(3, Types.TIMESTAMP);
            }
            
            pstmt.setString(4, reservation.getStatus());
            pstmt.setInt(5, reservation.getReservationId());

            pstmt.executeUpdate();
            System.out.println("Rezervasiya yeniləndi!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM reservation WHERE reservation_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Rezervasiya silindi!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reservation getById(int id) {
        Reservation reservation = null;
        String sql = "SELECT * FROM reservation WHERE reservation_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    reservation = mapResultSetToReservation(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservation;
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation";
        try (Connection conn = DBconnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                reservations.add(mapResultSetToReservation(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    // Helper metod: ResultSet -> Reservation obyektinə
    private Reservation mapResultSetToReservation(ResultSet rs) throws SQLException {
        Reservation res = new Reservation();
        res.setReservationId(rs.getInt("reservation_id"));
        res.setMemberId(rs.getInt("member_id"));
        res.setBookId(rs.getInt("book_id"));
        
        // Timestamp -> LocalDateTime çevrilməsi
        Timestamp ts = rs.getTimestamp("reservation_date");
        if (ts != null) {
            res.setReservationDate(ts.toLocalDateTime());
        }
        
        res.setStatus(rs.getString("status"));
        return res;
    }
}