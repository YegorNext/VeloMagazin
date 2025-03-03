package com.velomagaz.data_integration;

import java.util.List;
import java.util.Map;

public interface ILinkParser {
	public Map<String, String> parseData(List<List<String>> dataSet);
}
