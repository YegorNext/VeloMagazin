package com.velomagaz.app.controller;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.velomagaz.app.service.*;
import com.velomagaz.app.service.component.ProductRow;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductGridBuilderService builderService;
	
	
	@GetMapping
	public String Index(Model model) {
		LinkedList<ProductRow> productGrid = builderService.BuildGrid();
		model.addAttribute("productGrid", productGrid);
		
		return "product/index";
	}
	
	
}
