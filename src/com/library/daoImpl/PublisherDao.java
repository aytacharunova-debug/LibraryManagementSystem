package com.library.daoImpl;

import com.library.daoInterface.IPublisher;
import com.library.model.Publisher;
import com.library.util.DBconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublisherDao implements IPublisher {

    @Override
    public void add(Publisher publisher) {
        String sql = "INSERT INTO publisher (name, country, phone) VALUES (?, ?, ?)";
        
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, publisher.getName());
            pstmt.setString(2, publisher.getCountry());
            pstmt.setString(3, publisher.getPhone());
            
            pstmt.executeUpdate();
            System.out.println("Nəşriyyat uğurla əlavə edildi!");
            
        } catch (SQLException e) {
            System.out.println("Nəşriyyat əlavə edilərkən xəta: " + e.getMessage());
        }
    }

    @Override
    public void update(Publisher publisher) {
        String sql = "UPDATE publisher SET name = ?, country = ?, phone = ? WHERE publisher_id = ?";
        
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, publisher.getName());
            pstmt.setString(2, publisher.getCountry());
            pstmt.setString(3, publisher.getPhone());
            pstmt.setInt(4, publisher.getPublisherId());
            
            pstmt.executeUpdate();
            System.out.println("Nəşriyyat məlumatları yeniləndi!");
            
        } catch (SQLException e) {
            System.out.println("Yeniləmə zamanı xəta: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM publisher WHERE publisher_id = ?";
        
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Nəşriyyat silindi!");
            
        } catch (SQLException e) {
            System.out.println("Silinmə zamanı xəta: " + e.getMessage());
        }
    }

    @Override
    public Publisher getById(int id) {
        String sql = "SELECT * FROM publisher WHERE publisher_id = ?";
        Publisher publisher = null;
        
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    publisher = new Publisher();
                    publisher.setPublisherId(rs.getInt("publisher_id"));
                    publisher.setName(rs.getString("name"));
                    publisher.setCountry(rs.getString("country"));
                    publisher.setPhone(rs.getString("phone"));
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Axtarış zamanı xəta: " + e.getMessage());
        }
        return publisher;
    }

    @Override
    public List<Publisher> getAll() {
        List<Publisher> publisherList = new ArrayList<>();
        String sql = "SELECT * FROM publisher";
        
        try (Connection conn = DBconnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Publisher publisher = new Publisher();
                publisher.setPublisherId(rs.getInt("publisher_id"));
                publisher.setName(rs.getString("name"));
                publisher.setCountry(rs.getString("country"));
                publisher.setPhone(rs.getString("phone"));
                
                publisherList.add(publisher);
            }
            
        } catch (SQLException e) {
            System.out.println("Siyahı alınarkən xəta: " + e.getMessage());
        }
        return publisherList;
    }
}