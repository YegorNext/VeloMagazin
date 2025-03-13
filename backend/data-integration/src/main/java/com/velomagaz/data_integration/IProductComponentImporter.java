package com.velomagaz.data_integration;

import java.util.List;

public interface IProductComponentImporter {
	public void importData(List<List<String>> dataSet);
}
