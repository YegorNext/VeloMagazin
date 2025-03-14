package com.velomagaz.data_integration;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.w3c.dom.*;


public class DataXMLBuilder implements IDataBuilder<Document>{
	private List<List<String>> dataSet;
	private final IDataFormatStrategy dataFormatStrategy;
	
	public DataXMLBuilder(IDataFormatStrategy dataFormatStrategy) {
		dataSet = new ArrayList<List<String>>();
		this.dataFormatStrategy = dataFormatStrategy;
	}
	
	@Override
	public List<List<String>> build(Document document) {
		return (dataSet = buildDataList(document));
	}
	
	public List<List<String>> getDataSet() {
		return dataSet == null ? null : dataSet;
	}
	
	private List<List<String>> buildDataList(Document document){
		List<List<String>> data = new ArrayList<List<String>>();
		
		NodeList items = document.getElementsByTagName("item");
		int itemsLength = items.getLength();
	
		for(int i = 0; i < itemsLength; i++) {
			List<String> row = createRow((Element) items.item(i));
			if(row != null) data.add(row);
		}
		
		return data.isEmpty() ? null : data;
	}
	
	private List<String> createRow(Element item){
		List<String> row = new ArrayList<String>();
		
		row.add(getItemByName(item, "Code"));
		row.add(getItemBrand(item));
		row.add(getItemByName(item, "name"));
		row.add(buildJsonFromParam(item));
		row.add("");
		row.add(getItemByName(item, "image"));
		row.add(getItemByName(item, "priceuah").replace(",", "."));
		
		
		return row.isEmpty() ? null : row;
	}
	
	private String getItemByName(Element item, String name) {
		Node node = item.getElementsByTagName(name).item(0);
		
		return node == null ? null : node.getTextContent(); 	
	}
	
	
	private String buildJsonFromParam(Element item) {
		NodeList params = item.getElementsByTagName("param");
		int paramsLength = params.getLength();
		
		JSONObject json = new JSONObject();
		
		for(int i = 0; i < paramsLength; i++) {
			Element param = (Element)params.item(i); 
			String key = param.getAttribute("name");
			String value = param.getTextContent();
			
			json.put(key, value);
		}
		
		return json.toString();
	}
	
	private String getItemBrand(Element item) {
		NodeList params = item.getElementsByTagName("param");
		int paramsLength = params.getLength();
		String brandName = "";
		
		
		for(int i = 0; i < paramsLength; i++) {
			Element param = (Element)params.item(i);
			String key = param.getAttribute("name");
			String value = param.getTextContent();
			
			if(key == "Бренд" || key == "Бренд велосипеда") {
				brandName = value;
				break;
			}
		}
		
		return brandName;
	}
}
