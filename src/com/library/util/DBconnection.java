package com.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    // 1. Bağlantı məlumatlarını sabit (final) olaraq təyin edirik
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=LibraryDB;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASS = "12345";

    /**
     * Bu metod çağırıldıqda bizə SQL Server-ə qoşulmuş canlı Connection obyekti qaytarır.
     */
    public static Connection getConnection() {
        try {
            // Driver-i yükləyirik
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            // Bağlantını qururuq və obyekti (conn) geri qaytarırıq
            return DriverManager.getConnection(URL, USER, PASS);
            
        } catch (ClassNotFoundException e) {
            System.out.println("XƏTA: SQL Driver tapılmadı! " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("XƏTA: Verilənlər bazasına qoşulma alınmadı! " + e.getMessage());
        }
        return null; // Əgər xəta olarsa, heç nə (null) qaytarır
    }

    // Yoxlamaq üçün test metodu
    public static void main(String[] args) {
        Connection testConn = getConnection();
        if (testConn != null) {
            System.out.println("---------------------------------");
            System.out.println("UĞUR! Kitabxana bazasına qoşulduq.");
            System.out.println("---------------------------------");
        }
    }
}