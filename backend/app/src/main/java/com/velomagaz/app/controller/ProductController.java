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
import org.springframework.web.bind.annotation.RequestParam;

import com.velomagaz.app.service.*;
import com.velomagaz.app.service.component.ProductRow;

import com.velomagaz.app.repository.*;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private final int productList_size = 48; 
	
	@Autowired
	ProductGridBuilderService builderService;
	
	@Autowired 
	ProductImageService imageService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductInfoBuilder productInfoService;
	
	@Autowired
	IProductRepository productRepository;
	
	@GetMapping
	public String Index(Model model, @RequestParam(defaultValue = "0") int page) {
		
		if(page < 0) page = 0;
		int totalPages = productService.getTotalPages(page, productList_size);
		int endPage = page + 10 > totalPages ? totalPages : page + 10;
		
		LinkedList<ProductRow> productGrid = builderService.buildPagebaleGrid(page, productList_size);
		
		model.addAttribute("productGrid", productGrid);
		model.addAttribute("endPage", endPage);
		model.addAttribute("currentPage", page);
		
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
