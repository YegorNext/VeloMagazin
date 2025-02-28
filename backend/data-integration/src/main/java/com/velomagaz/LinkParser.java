package com.velomagaz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class LinkParser {
	private final int LINK_INDEX = 5, ID_INDEX = 0;
	private final String baseURL = "https://sportsystems.com.ua"; 
	
	public Map<String, String> ParseData(List<List<String>> dataSet) {
		return getLinks(dataSet);
	}
	
	private Map<String, String> getLinks(List<List<String>> dataSet)  {
		Map<String, String> imageLinks = new HashMap<>();
		
		
		for(List<String> row : dataSet) {
			imageLinks.put(row.get(ID_INDEX), parseImageLink(row.get(LINK_INDEX)));
		}
		
		return imageLinks;
	}
	
	private String parseImageLink(String link){
		if (link.isEmpty()) return null;
		
		try {
			Document doc = Jsoup.connect(link).get();
			
			Elements imgContainer = doc.select(".p-images__right a"); 
		
			if(!imgContainer.isEmpty()) {
				String linkHref = imgContainer.first().attr("href");
				
				if(linkHref == null) {
					System.out.println("Link is not found: link value is empty. " + link);
					return null;
				}
				
				System.out.println("Link parsed: " + baseURL + linkHref);
				return baseURL+linkHref;
				
			}else {
				System.out.println("Link is not found: image container is empty. " + link);
			}
		} catch (IOException e) {
			System.out.println("Parsing failed: " + e.getMessage() + ". " + link);
		}
		
		return null;
	}
}
