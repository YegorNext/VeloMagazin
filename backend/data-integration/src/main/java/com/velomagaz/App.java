package com.velomagaz;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
       // String imageUrl = "https://sportsystems.com.ua/wa-data/public/shop/products/00/webp/98/15/11598/images/9388/9388.0x600.webp";
      //  File destination = new File("test.webp");

       // FileUtils.copyURLToFile(new URL(imageUrl), destination);

       // System.out.println("Файл скачан: " + destination.getAbsolutePath());
    	
        FileInputStream file = new FileInputStream(new File("TEST.xlsx"));
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);
        

        //DataBuilder builder = new DataBuilder();
        
       // BrandImporter importer = new BrandImporter();
       // importer.insertBrands(builder.Build(sheet));
        
      // ImportManager importer = new ImportManager();
      // importer.Import(builder.Build(sheet));
        
      //  int count = 0;
       // for(List<String> str : builder.Build(sheet)) {
       // 	System.out.println(str.get(0) + ". Count: " + count++);
       // }
        
        
        //LinkParser parser = new LinkParser(); IMAGES 
        //ImageDownloader downloader = new ImageDownloader();
        
       // downloader.Download(parser.ParseData(builder.Build(sheet))); 

        //RenameImages renamer = new RenameImages();
       // renamer.Rename();
        
        //ImageUploader uploader = new ImageUploader();
       // uploader.upload();
              	
        workbook.close();
        file.close();
    }
}
