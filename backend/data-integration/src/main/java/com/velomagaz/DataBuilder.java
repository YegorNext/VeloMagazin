package com.velomagaz;
 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class DataBuilder implements IDataBuilder{
	public List<List<String>> Build(Sheet sheet) {
		return buildDataList(sheet.iterator());
	}
	
	private List<List<String>> buildDataList(Iterator<Row> rowIterator){
		List<List<String>> data = new ArrayList<List<String>>();
		
		while(rowIterator.hasNext()) {
			data.add(createList(rowIterator.next())); 
		}
		
		return data;
	}
	
	private List<String> createList(Row row) {
		List<String> element = new LinkedList<String>();
		for (Cell cell : row) {
			element.add(cell.toString());
		}
		
		excludeValue(element, "HYPERLINK");
		
		return element;
	} 
	
	private void excludeValue(List<String> element, String value) {
		element.removeIf(item -> item != null && item.contains(value));
	}
	
	
}
