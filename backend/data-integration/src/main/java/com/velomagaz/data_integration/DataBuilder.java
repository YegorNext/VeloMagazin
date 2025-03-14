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
	private final int row_size = 8;
	
	
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
		
		for(int i = 0; i < row_size; i++) {
			String cellData = row.getCell(i).toString();
			if(cellData.isEmpty()) element.add(" ");
			else element.add(cellData);
		}
		
		
		if(dataFormatStrategy != null && !element.isEmpty()) 
			dataFormatStrategy.formatData(element);

		return element.isEmpty() ? null : element;
	}
	
	public List<List<String>> getDataSet(){
		return dataSet.isEmpty() ? null : dataSet; 
	}
}
