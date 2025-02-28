package com.velomagaz;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

public interface IDataBuilder {
	public List<List<String>> Build(Sheet sheet);
}
