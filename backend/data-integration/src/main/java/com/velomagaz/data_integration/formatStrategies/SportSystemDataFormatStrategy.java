package com.velomagaz.data_integration.formatStrategies;

import com.velomagaz.data_integration.*;
import com.velomagaz.data_integration.constant.IndexConstants;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SportSystemDataFormatStrategy implements IDataFormatStrategy {
	private final String excludeValue = "HYPERLINK"; // Element with this value to exclude
	
	public void formatData(List<String> dataRow) {
		excludeElement(dataRow);
		excludeValue(dataRow);
	}
	
	private void excludeElement(List<String> element) {
		element.removeIf(item -> item != null && item.contains(excludeValue)); // remove item with hyperlink 
	}
	
	private void excludeValue(List<String> element) {
		String str = element.get(IndexConstants.ID_IND);
		
		int dotIndex = str.indexOf(".");
		if(dotIndex != -1) {
			element.set(IndexConstants.ID_IND, str.substring(0, str.indexOf("."))); // Remove id suffix 
		}
	}
}
