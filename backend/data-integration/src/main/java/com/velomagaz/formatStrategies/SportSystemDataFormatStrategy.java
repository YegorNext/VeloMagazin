package com.velomagaz.formatStrategies;

import java.util.List;

import com.velomagaz.IDataFormatStrategy;

public class SportSystemDataFormatStrategy implements IDataFormatStrategy {
	private final short ID_IND = 0;
	private final String excludeValue = "HYPERLINK"; // Element with this value to exclude
	
	public void formatData(List<String> dataRow) {
		excludeElement(dataRow);
		excludeValue(dataRow);
	}
	
	private void excludeElement(List<String> element) {
		element.removeIf(item -> item != null && item.contains(excludeValue)); // remove item with hyperlink 
	}
	
	private void excludeValue(List<String> element) {
		String str = element.get(ID_IND);
		element.set(ID_IND, str.substring(0, str.indexOf("."))); // Remove id suffix 
	}
}
