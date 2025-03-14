package com.velomagaz.data_integration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

public class ImagePathParser implements IImagePathParser{
	public Map<String, String> parse(String filePath) {
		if(filePath.isEmpty()) return null;
				
		return convertJsonToMap(readFile(filePath));
	}
	
	public Map<String, String> parseAll(String directoryName) {
		return scanDirectory(directoryName);
	}
	
	private JSONObject readFile(String filePath) {
		try {
			String content = new String(Files.readAllBytes(Paths.get(filePath)));
			return new JSONObject(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private Map<String, String> convertJsonToMap(JSONObject json){
		Map<String, String> imagePath = new HashMap<String, String>();
		Iterator<String> keys = json.keys();
		
		while(keys.hasNext()) {
			String key = keys.next();
			imagePath.put(key, json.get(key).toString()); 		
		}
		
		return imagePath.isEmpty() ? null : imagePath;
	}
	
	private Map<String, String> scanDirectory(String directoryName) {
		Map<String, String> imagePath = new HashMap<String, String>();
		File directory = new File(directoryName);
	
		if (directory.exists() && directory.isDirectory()) {
			  File[] files = directory.listFiles();
			  
			  if(files != null) {
				  for(File file : files) {
					  imagePath.put(file.getName(), file.getAbsolutePath());
				  }
			  }	  
		} 
		
		return imagePath.isEmpty() ? null : imagePath;
	}
}
