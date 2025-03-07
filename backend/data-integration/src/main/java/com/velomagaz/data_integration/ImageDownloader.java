package com.velomagaz.data_integration;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import java.io.IOException;



public class ImageDownloader implements IImageDownloader {
	private final String path = "images/";
	private Map<String, String> imagePath;
	
	public Map<String, String> download(Map<String, String> productLinks) {
		this.imagePath = new HashMap<String, String>();
		
		for(Map.Entry<String, String> entry : productLinks.entrySet()) {
			saveImage(entry);
		}
	
		return imagePath.isEmpty() ? null : imagePath;
	}
	
	private void saveImage(Map.Entry<String, String> entry) {
		try {
            File destination = new File(path + entry.getKey() + ".jpg");
			FileUtils.copyURLToFile(new URL(entry.getValue()), destination);
			
			imagePath.put(entry.getKey(), destination.getAbsolutePath());
			
			System.out.println("Success: " + destination.getAbsolutePath());
		} catch (IOException e) {
			System.err.println("Failed: " + e.getMessage() + ". Number:  " + entry.getKey() + ". " + entry.getValue());
		}
	}
	
	public Map<String, String> getImagePath(){
		return imagePath.isEmpty() ? null : imagePath;
	}
}
