package com.velomagaz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



public class ImportManager {
	
	private static final String URL = "jdbc:mariadb://localhost:3306/velomagazin";
    private static final String USER = "root";
    private static final String PASSWORD = "sys";

	
	public void Import(List<List<String>> data) {
		 String sql = "INSERT INTO product (id, age_group, description, gender_group, image, price, product_name, brand_id, subcategory_id) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	     String getBrandIdSQL = "SELECT id FROM brand WHERE brand_name = ?";

		 
	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	        		PreparedStatement getBrandStmt = conn.prepareStatement(getBrandIdSQL);
	                PreparedStatement stmt = conn.prepareStatement(sql)) {

	               for (List<String> row : data) {
	            	   String brandName = row.get(1); 
	                   int brandId = getBrandId(conn, getBrandStmt, brandName);
	            	   
	                   stmt.setString(1, row.get(0)); 
	                   stmt.setString(2, null);
	                   stmt.setString(3, row.get(4));
	                   stmt.setString(4, null);
	                   stmt.setNull(5, java.sql.Types.BLOB); 
	                   stmt.setDouble(6, row.get(6).isEmpty() ? 0 : Double.parseDouble(row.get(6))); 
	                   stmt.setString(7, row.get(2));
	                   stmt.setInt(8, brandId); 
	                   stmt.setInt(9, 1); 

	                   stmt.executeUpdate();
	               }

	               System.out.println("Success");
	           } catch (SQLException e) {
	               e.printStackTrace();
	           }

	}
	
    private int getBrandId(Connection conn, PreparedStatement stmt, String brandName) throws SQLException {
        stmt.setString(1, brandName);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1; 
    }
}
