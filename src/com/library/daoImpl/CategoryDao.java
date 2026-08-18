package com.library.daoImpl;

import com.library.daoInterface.ICategory;
import com.library.model.Category;
import com.library.util.DBconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements ICategory {

    @Override
    public void add(Category category) {
        String sql = "INSERT INTO category (name, description) VALUES (?, ?)";
        
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, category.getName());
            pstmt.setString(2, category.getDescription());
            
            pstmt.executeUpdate();
            System.out.println("Kateqoriya uğurla əlavə edildi!");
            
        } catch (SQLException e) {
            System.out.println("Kateqoriya əlavə edilərkən xəta: " + e.getMessage());
        }
    }

    @Override
    public void update(Category category) {
        String sql = "UPDATE category SET name = ?, description = ? WHERE category_id = ?";
        
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, category.getName());
            pstmt.setString(2, category.getDescription());
            pstmt.setInt(3, category.getCategoryId());
            
            pstmt.executeUpdate();
            System.out.println("Kateqoriya uğurla yeniləndi!");
            
        } catch (SQLException e) {
            System.out.println("Kateqoriya yenilənərkən xəta: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM category WHERE category_id = ?";
        
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Kateqoriya silindi!");
            
        } catch (SQLException e) {
            System.out.println("Kateqoriya silinərkən xəta: " + e.getMessage());
        }
    }

    @Override
    public Category getById(int id) {
        String sql = "SELECT * FROM category WHERE category_id = ?";
        Category category = null;
        
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    category = new Category();
                    category.setCategoryId(rs.getInt("category_id"));
                    category.setName(rs.getString("name"));
                    category.setDescription(rs.getString("description"));
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Axtarış zamanı xəta: " + e.getMessage());
        }
        return category;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categoryList = new ArrayList<>();
        String sql = "SELECT * FROM category";
        
        try (Connection conn = DBconnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                
                categoryList.add(category);
            }
            
        } catch (SQLException e) {
            System.out.println("Siyahı oxunarkən xəta: " + e.getMessage());
        }
        return categoryList;
    }
}