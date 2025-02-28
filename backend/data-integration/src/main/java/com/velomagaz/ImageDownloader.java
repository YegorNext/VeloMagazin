package com.velomagaz;

import java.io.File;
import java.net.URL;

import java.util.Map;

import org.apache.commons.io.FileUtils;

import java.io.IOException;



public class ImageDownloader {
	private final String path = "images";
	
	public void Download(Map<String, String> productLinks) {
		for(Map.Entry<String, String> entry : productLinks.entrySet()) {
			saveImage(entry);
		}
	}
	
	private void saveImage(Map.Entry<String, String> entry) {
		try {
            File destination = new File(path + "/image" + entry.getKey() + ".jpg");
			FileUtils.copyURLToFile(new URL(entry.getValue()), destination);
			
			System.out.println("Success: " + destination.getAbsolutePath());
		} catch (IOException e) {
			System.err.println("Failed: " + e.getMessage() + ". Number:  " + entry.getKey() + ". " + entry.getValue());
		}
	}
}
