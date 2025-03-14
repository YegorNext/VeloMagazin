package com.velomagaz.data_integration;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class ImagePathSaver implements IImagePathSaver{
	private final String fileName = "imagePath";
	
	public void saveImagePathToJson(Map<String, String> imagePath) {
		try(FileWriter file = new FileWriter(fileName + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".json")) {
			file.write(convertImagePathToJson(imagePath).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private JSONObject convertImagePathToJson(Map<String, String> imagePath) {
		if(imagePath == null) {
			System.err.println("WARN: cannot create json (image path), variable <ImageDownloader>'imagePath' is null");;
			return null;
		}
		
		if (imagePath.isEmpty()) {
			System.err.println("WARN: cannot create json (image path), variable <ImageDownloader>'imagePath' is empty");;
			return null;
		}
		
		try {
			return new JSONObject(imagePath);
		}catch(Exception e){
			System.err.println("ERROR: cannot create json object from map variable <ImageDownloader>'imagePath'");
		}
		
		return null;
	}
}
