package com.velomagaz.data_integration;

import java.util.List;

public interface IBrandFactory {
	public List<String> build(List<List<String>> dataSet);
}
