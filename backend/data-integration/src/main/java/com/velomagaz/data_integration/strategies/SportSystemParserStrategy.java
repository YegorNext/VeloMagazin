package com.velomagaz.data_integration.strategies;

import com.velomagaz.data_integration.*;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class SportSystemParserStrategy implements IParserStrategy{
	public String parse(String link, String baseURL) {
		if (link.isEmpty()) return null;
		if (baseURL.isEmpty()) throw new IllegalArgumentException("BaseURL value cannot be null");
		
		return selectElements(link, baseURL);
	}
	
	private String selectElements(String link, String baseURL) {
		try {
			Document doc = Jsoup.connect(link).get();
			Elements imgContainer = doc.select(".p-images__right a"); 
		
			String imageURI = parseImageContainer(imgContainer, link);
			
			return imageURI.isEmpty() ? null : baseURL + imageURI;
			
		} catch (IOException e) {
			System.out.println("Parsing failed: " + e.getMessage() + ". " + link);
		}
		return null;
	}
	
	private String parseImageContainer(Elements imgContainer, String link) {
		if(!imgContainer.isEmpty())	
			return checkAttributeValue(imgContainer.first().attr("href"), link);
		else 
			System.out.println("Link is not found: image container is empty. " + link);
		
		return null;
	}
	
	private String checkAttributeValue(String linkHref, String link) {
		if(linkHref.isEmpty()) {
			System.out.println("Link is not found: link value is empty. " + link);
			return null;
		}
		
		System.out.println("URI parsed: " + linkHref);
		return linkHref;
	}
}
