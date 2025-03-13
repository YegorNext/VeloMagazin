package com.velomagaz.data_integration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataIntegrationApplication implements CommandLineRunner{

	@Autowired
	private ImportManager<Sheet> importManager;
	
	public static void main(String[] args) {
		SpringApplication.run(DataIntegrationApplication.class, args);
	}

    @Override
    public void run(String... args) throws EncryptedDocumentException, IOException {
        FileInputStream file = new FileInputStream(new File("TEST2.xlsx"));
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);
        
        importManager.importData(sheet);
              	
        workbook.close();
        file.close();
    }
}
