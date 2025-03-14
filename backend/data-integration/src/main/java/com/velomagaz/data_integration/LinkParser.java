package com.velomagaz.data_integration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.velomagaz.data_integration.constant.IndexConstants;

public class LinkParser implements ILinkParser{
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
			imageLinks.put(row.get(IndexConstants.ID_IND), getImageURL(row.get(IndexConstants.LINK_IND)));
		}
		
		return imageLinks;
	}
	
	private String getImageURL(String link){
		return parserStrategy.parse(link, baseURL);
	}
}
