package com.velomagaz.data_integration;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

public interface IDataBuilder {
	public List<List<String>> build(Sheet sheet);
	public List<List<String>> getDataSet();
}
