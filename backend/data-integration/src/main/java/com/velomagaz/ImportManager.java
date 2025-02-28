package com.velomagaz;

import org.apache.poi.ss.usermodel.Sheet;

public class ImportManager {
	private IDataBuilder dataBuilder;
	
	public ImportManager(IDataBuilder dataBuilder) {
		this.dataBuilder = dataBuilder;
	}
	
	public void Import(Sheet sheet) {
		dataBuilder.Build(null);
	}
}
