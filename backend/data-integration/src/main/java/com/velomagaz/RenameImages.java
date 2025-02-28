package com.velomagaz;

import java.io.File;

public class RenameImages {
    public void Rename() {
        File folder = new File("images"); 

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Folder is not found");
            return;
        }

        File[] files = folder.listFiles((dir, name) -> name.matches("image\\d+\\.\\d+\\.jpg"));

        if (files == null || files.length == 0) {
            System.out.println("No such files!");
            return;
        }

        for (File file : files) {
            String oldName = file.getName();
            String newName = oldName.replaceFirst("image(\\d+)\\.\\d+\\.jpg", "$1.jpg");

            File newFile = new File(folder, newName);
            if (file.renameTo(newFile)) {
                System.out.println("Renamed: " + oldName + " → " + newName);
            } else {
                System.out.println("Renamed error: " + oldName);
            }
        }
    }
}