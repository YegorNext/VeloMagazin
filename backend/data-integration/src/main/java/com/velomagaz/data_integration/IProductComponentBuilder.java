package com.velomagaz.data_integration;

import java.util.List;
import java.util.Map;

import com.velomagaz.data_integration.entity.ProductComponent;

public interface IProductComponentBuilder {
	public List<ProductComponent> build(Map.Entry<String, Map<String, String>> productComponent);
}
