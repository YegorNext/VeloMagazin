package com.velomagaz.data_integration;

import java.util.List;
import java.util.Map;

public interface IProductComponentFactory {
	public Map<String, Map<String, String>> build(List<String> data);
}
