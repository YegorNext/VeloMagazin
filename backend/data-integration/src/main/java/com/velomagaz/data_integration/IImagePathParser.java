package com.velomagaz.data_integration;

import java.util.Map;

public interface IImagePathParser {
	public Map<String, String> parse(String filePath);
	public Map<String, String> parseAll(String directoryName);
}
