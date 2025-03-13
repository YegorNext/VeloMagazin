package com.velomagaz.data_integration;

import java.util.List;
import java.util.Map;

public interface IComponentFormatter {
	public List<String> format(Map<String, Map<String, String>> productComponents);
}
