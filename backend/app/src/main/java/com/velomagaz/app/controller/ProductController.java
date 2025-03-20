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
	ISubCategoryRepository subCategoryRepository;
	
	@Autowired
	IProductRepository productRepository;
	
	@GetMapping
	public String index(Model model, @RequestParam(defaultValue = "0") int page) {
		
		if(page < 0) page = 0;
		int totalPages = productService.getTotalPages(page, productList_size);
		int endPage = page + 10 > totalPages ? totalPages : page + 10;
		
		LinkedList<ProductRow> productGrid = builderService.buildPageableGrid(page, productList_size);
		
		model.addAttribute("productGrid", productGrid);
		model.addAttribute("endPage", endPage);
		model.addAttribute("currentPage", page);
		
		return "product/index";
	}
	
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable String id) {
        byte[] image = imageService.getImageById(id);
        
        if (image == null) {
            return null; 
        }

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("image/webp"))
                .body(image); 
    }
    
    @GetMapping("/{id}")
    public String info(@PathVariable String id, Model model) {
    	if(productRepository.findById(id).orElse(null) == null) {
    		return "errorPage";
    	}
    	 	
    	model.addAttribute("productInfo", productInfoService.BuildInfo(id));

    	return "product/info";
    }
    
    @GetMapping("/category/{categoryName}")
    public String category(@PathVariable String categoryName, Model model, @RequestParam(defaultValue = "0") int page) {
    	if(categoryName == null || categoryName.isEmpty() || subCategoryRepository.findBySubcategoryName(categoryName) == null) {
    		return "errorPage";
    	}
    	
    	if(page < 0) page = 0;
		int totalPages = (categoryName.isEmpty()) ? 0 : productService.getTotalPagesByCategoryName(page, productList_size, categoryName);
		int endPage = page + 10 > totalPages ? totalPages : page + 10;
		
		LinkedList<ProductRow> productGrid = builderService.buildPageableGridByCategoryName(page, productList_size, categoryName);
		
		model.addAttribute("productGrid", ( productGrid == null || productGrid.isEmpty()) ? null : productGrid);
		model.addAttribute("endPage", endPage);
		model.addAttribute("currentPage", page);
		model.addAttribute("categoryName", categoryName);
    	
    	return "product/category";
    }
    
    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "") String query, Model model, @RequestParam(defaultValue = "0") int page) {
		
    	if(page < 0) page = 0;
		int totalPages = (query.isEmpty() || query.length() < 3) ? 0 : productService.getTotalPagesByQuery(page, productList_size, query);
		int endPage = page + 10 > totalPages ? totalPages : page + 10;
		
		LinkedList<ProductRow> productGrid = (query.isEmpty() || query.length() < 3) ? null : builderService.buildPageableGridByQuery(page, productList_size, query);
		
		model.addAttribute("productGrid", ( productGrid == null || productGrid.isEmpty()) ? null : productGrid);
		model.addAttribute("endPage", endPage);
		model.addAttribute("currentPage", page);
		model.addAttribute("query", query);
    	
    	return "product/search";
    }
}
