package com.velomagaz.app.controller;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.velomagaz.app.service.*;
import com.velomagaz.app.service.component.ProductRow;

import com.velomagaz.app.repository.*;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductGridBuilderService builderService;
	
	@Autowired 
	ProductImageService imageService;
	
	@Autowired
	ProductInfoBuilder productInfoService;
	
	@Autowired
	IProductRepository productRepository;
	
	@GetMapping
	public String Index(Model model) {
		LinkedList<ProductRow> productGrid = builderService.BuildGrid();
		model.addAttribute("productGrid", productGrid);
		
		return "product/index";
	}
	
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> GetProductImage(@PathVariable String id) {
        byte[] image = imageService.getImageById(id);
        
        if (image == null) {
            return null; 
        }

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("image/webp"))
                .body(image); 
    }
    
    @GetMapping("/{id}")
    public String Info(@PathVariable String id, Model model) {
    	
    	model.addAttribute("productInfo", productInfoService.BuildInfo(id));

    	return "product/info";
    }
}
