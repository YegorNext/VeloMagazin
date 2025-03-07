package com.velomagaz.data_integration;

import java.util.Map;

public interface IImageDownloader {
	public Map<String, String> download(Map<String, String> productLinks);
	public Map<String, String> getImagePath();
}
