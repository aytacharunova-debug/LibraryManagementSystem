package com.library.util;

import java.sql.Connection;
import java.sql.DriverManager; // Bu import-u yoxla, yoxdursa əlavə et

public class DBconnection {

    // KÖHNƏ SƏTİRLƏRİ SİLDİK, YERİNƏ BUNLARI YAZDIQ:
    private static final String URL = Config.get("DB_URL");
    private static final String USER = Config.get("DB_USER");
    private static final String PASS = Config.get("DB_PASS");

    public static Connection getConnection() {
        try {
            // Driver-i yükləyirik
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Bağlantını qururuq
            return DriverManager.getConnection(URL, USER, PASS);

        } catch (Exception e) {
            System.out.println("XƏTA: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Connection testConn = getConnection();
        if (testConn != null) {
            System.out.println("UĞUR! Kitabxana bazasına qoşulduq.");
        }
    }
}