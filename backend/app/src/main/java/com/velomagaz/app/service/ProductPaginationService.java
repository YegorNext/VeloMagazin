package com.velomagaz.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.velomagaz.app.service.component.GridLayoutValidator;
import com.velomagaz.app.service.record.ProductPageable;

@Component
public class ProductPaginationService {
	@Autowired
	GridLayoutValidator layoutValidator;
	
    public void addPaginationAttributes(Model model, ProductPageable productRecord, int page) {
		model.addAttribute("productGrid", (layoutValidator.isProductGridNullOrEmpty(productRecord.productGrid())) ? null : productRecord.productGrid());
		model.addAttribute("endPage", productRecord.endPage());
		model.addAttribute("currentPage", page);
    }
    
    public int pageValidate(int page) {
    	return Math.max(page, 0);
    }
}
