package com.library.daoImpl;

import com.library.daoInterface.IMember;
import com.library.model.Member;
import com.library.util.DBconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao implements IMember {

    @Override
    public void add(Member member) {
        String sql = "INSERT INTO member (name, surname, email, phone) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getSurname());
            pstmt.setString(3, member.getEmail());
            pstmt.setString(4, member.getPhone());
            
            pstmt.executeUpdate();
            System.out.println("Yeni üzv uğurla əlavə edildi!");
            
        } catch (SQLException e) {
            System.out.println("Üzv əlavə edilərkən xəta: " + e.getMessage());
        }
    }

    @Override
    public void update(Member member) {
        String sql = "UPDATE member SET name = ?, surname = ?, email = ?, phone = ? WHERE member_id = ?";
        
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getSurname());
            pstmt.setString(3, member.getEmail());
            pstmt.setString(4, member.getPhone());
            pstmt.setInt(5, member.getMemberId());
            
            pstmt.executeUpdate();
            System.out.println("Üzv məlumatları yeniləndi!");
            
        } catch (SQLException e) {
            System.out.println("Üzv yenilənərkən xəta: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM member WHERE member_id = ?";
        
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Üzv silindi!");
            
        } catch (SQLException e) {
            System.out.println("Üzv silinərkən xəta: " + e.getMessage());
        }
    }

    @Override
    public Member getById(int id) {
        String sql = "SELECT * FROM member WHERE member_id = ?";
        Member member = null;
        
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    member = new Member();
                    member.setMemberId(rs.getInt("member_id"));
                    member.setName(rs.getString("name"));
                    member.setSurname(rs.getString("surname"));
                    member.setEmail(rs.getString("email"));
                    member.setPhone(rs.getString("phone"));
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Üzv axtarışında xəta: " + e.getMessage());
        }
        return member;
    }

    @Override
    public List<Member> getAll() {
        List<Member> memberList = new ArrayList<>();
        String sql = "SELECT * FROM member";
        
        try (Connection conn = DBconnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setName(rs.getString("name"));
                member.setSurname(rs.getString("surname"));
                member.setEmail(rs.getString("email"));
                member.setPhone(rs.getString("phone"));
                
                memberList.add(member);
            }
            
        } catch (SQLException e) {
            System.out.println("Üzvlər siyahısı alınarkən xəta: " + e.getMessage());
        }
        return memberList;
    }
}