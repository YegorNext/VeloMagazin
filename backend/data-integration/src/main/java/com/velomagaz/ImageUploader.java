package com.velomagaz;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImageUploader {
    private static final String URL = "jdbc:mariadb://localhost:3306/velomagazin";
    private static final String USER = "root";
    private static final String PASSWORD = "sys";
    private static final String IMAGE_FOLDER = "images"; 

    public void upload() {
        File folder = new File(IMAGE_FOLDER);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Folder is not found!");
            return;
        }

        File[] imageFiles = folder.listFiles((dir, name) -> name.matches("\\d+\\.jpg"));
        if (imageFiles == null || imageFiles.length == 0) {
            System.out.println("No such images!");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE product SET image = ? WHERE id = ?")) {

            for (File file : imageFiles) {
                String productId = file.getName().replace(".jpg", "");

                byte[] imageData = Files.readAllBytes(file.toPath());

                stmt.setBytes(1, imageData);
                stmt.setString(2, productId);

                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Image loaded for ID: " + productId);
                } else {
                    System.out.println("Image not found for ID: " + productId);
                }
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
