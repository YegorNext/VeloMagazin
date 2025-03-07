package com.velomagaz.data_integration;

import java.util.List;

import com.velomagaz.data_integration.entity.Product;

public interface IProductFactory {
	public Product build (List<String> productInfo);
}
