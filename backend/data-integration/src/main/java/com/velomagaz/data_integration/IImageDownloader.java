package com.velomagaz.data_integration;

import java.util.Map;

public interface IImageDownloader {
	public void download(Map<String, String> productLinks);
}
