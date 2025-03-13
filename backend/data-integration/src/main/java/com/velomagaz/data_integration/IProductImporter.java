package com.velomagaz.data_integration;

import java.util.List;
import java.util.Map;

public interface IProductImporter {
	public void importData(List<List<String>> dataSet, Map<String, String> images);
}
