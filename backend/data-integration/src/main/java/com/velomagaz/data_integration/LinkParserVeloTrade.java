package com.velomagaz.data_integration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.velomagaz.data_integration.constant.IndexConstants;

public class LinkParserVeloTrade implements ILinkParser{
	
	public Map<String, String> parseData(List<List<String>> dataSet) {
		return buildLinkMap(dataSet);
	}
	
	private Map<String, String> buildLinkMap(List<List<String>> dataSet) {
		Map<String, String> linkMap = new HashMap<String, String>();
		for(List<String> row : dataSet) {
			linkMap.put(row.get(IndexConstants.ID_IND), row.get(IndexConstants.LINK_IND));
		}
		
		return linkMap;
	}
	
}
