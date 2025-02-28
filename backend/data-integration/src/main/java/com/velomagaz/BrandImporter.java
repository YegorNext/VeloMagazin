package com.velomagaz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BrandImporter {
	private static final String URL = "jdbc:mariadb://localhost:3306/velomagazin";
    private static final String USER = "root";
    private static final String PASSWORD = "sys";

    public void insertBrands(List<List<String>> data) {
        String sql = "INSERT IGNORE INTO brand (brand_name) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (List<String> row : data) {
                String brandName = row.get(1);
                stmt.setString(1, brandName);
                stmt.executeUpdate(); 
            }

            System.out.println("Success updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}