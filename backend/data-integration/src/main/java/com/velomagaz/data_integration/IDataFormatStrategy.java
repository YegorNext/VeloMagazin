package com.velomagaz.data_integration;

import java.util.List;

public interface IDataFormatStrategy {
	public void formatData(List<String> dataRow);
}
