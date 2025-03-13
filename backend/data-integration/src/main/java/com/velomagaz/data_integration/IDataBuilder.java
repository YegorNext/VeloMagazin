package com.velomagaz.data_integration;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

public interface IDataBuilder<T> {
	public List<List<String>> build(T data);
	public List<List<String>> getDataSet();
}
