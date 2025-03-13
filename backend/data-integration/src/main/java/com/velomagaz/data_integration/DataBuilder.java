package com.velomagaz.data_integration;
 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

@Component
public class DataBuilder implements IDataBuilder<Sheet>{
	private List<List<String>> dataSet;
	private final IDataFormatStrategy dataFormatStrategy;
	
	public DataBuilder(IDataFormatStrategy dataFormatStrategy) {
		dataSet = new ArrayList<List<String>>();
		this.dataFormatStrategy = dataFormatStrategy;
	}
	
	@Override
	public List<List<String>> build(Sheet sheet) {
		return (dataSet = buildDataList(sheet.iterator()));
	}
	
	private List<List<String>> buildDataList(Iterator<Row> rowIterator){
		List<List<String>> data = new ArrayList<List<String>>();
		
		while(rowIterator.hasNext()) {
			List<String> row = createList(rowIterator.next());
			if(row != null) {
				data.add(row); 
			}
		}
		
		return data.isEmpty() ? null : data;
	}
	
	private List<String> createList(Row row) {
		List<String> element = new LinkedList<String>();
		for (Cell cell : row) {
			String cellData = cell.toString();
			if(!cellData.isEmpty()) {
				element.add(cellData);
			}
		}
		
		if(dataFormatStrategy != null && !element.isEmpty()) 
			dataFormatStrategy.formatData(element);

		return element.isEmpty() ? null : element;
	}
	
	public List<List<String>> getDataSet(){
		return dataSet.isEmpty() ? null : dataSet; 
	}
}
