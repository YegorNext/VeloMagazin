package com.velomagaz;
 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class DataBuilder implements IDataBuilder{
	private List<List<String>> dataSet;
	private final IDataFormatStrategy dataFormatStrategy;
	
	public DataBuilder(IDataFormatStrategy dataFormatStrategy) {
		dataSet = new ArrayList<List<String>>();
		this.dataFormatStrategy = dataFormatStrategy;
	}
	
	public List<List<String>> build(Sheet sheet) {
		return (dataSet = buildDataList(sheet.iterator()));
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
		
		if(dataFormatStrategy != null) 
			dataFormatStrategy.formatData(element);

		return element;
	}
	
	public List<List<String>> getDataSet(){
		return dataSet.isEmpty() ? null : dataSet; 
	}
}
