package com.velomagaz.data_integration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.velomagaz.data_integration.constant.IndexConstants;

@Component
public class ExcelBrandFactory implements IBrandFactory {
	public List<String> build(List<List<String>> dataSet) {
    	List<String> brands = new ArrayList<String>();
    	
    	for(List<String> row : dataSet) {
    		String brandName = isBrandNullOrElse(row);
    		brands.add(brandName);
    	}
    	
        return brands.isEmpty() ? null : brands;
    }
	
	private String isBrandNullOrElse(List<String> row) {
		if(row.get(IndexConstants.BRAND_IND) == null) {
			System.err.println("Cannot add brand to list with item id " + row.get(IndexConstants.ID_IND));
		}else return row.get(IndexConstants.BRAND_IND);
		
		return null;
	}
}