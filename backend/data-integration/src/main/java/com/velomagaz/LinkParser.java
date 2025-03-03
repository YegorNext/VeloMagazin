package com.velomagaz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinkParser implements ILinkParser{
	private final int LINK_INDEX = 5, ID_INDEX = 0;
	private final String baseURL; 
	private final IParserStrategy parserStrategy;
	
	public LinkParser(String baseURL, IParserStrategy parserStrategy) {
		this.baseURL = baseURL;
		this.parserStrategy = parserStrategy;
	}
	
	public Map<String, String> parseData(List<List<String>> dataSet) {
		return getLinks(dataSet);
	}
	
	private Map<String, String> getLinks(List<List<String>> dataSet)  {
		Map<String, String> imageLinks = new HashMap<>();
		
		
		for(List<String> row : dataSet) {
			imageLinks.put(row.get(ID_INDEX), getImageURL(row.get(LINK_INDEX)));
		}
		
		return imageLinks;
	}
	
	private String getImageURL(String link){
		return parserStrategy.parse(link, baseURL);
	}
}
