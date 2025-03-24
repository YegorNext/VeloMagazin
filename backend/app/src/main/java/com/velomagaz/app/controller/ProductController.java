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
import com.velomagaz.app.service.record.ProductPageable;
import com.velomagaz.app.repository.*;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private final int productList_size = 48; 
	
	@Autowired 
	ProductImageService imageService;
	
	@Autowired
	ProductInfoBuilder productInfoService;
	
	@Autowired
	ProductPageRecordBuilderService productPageRecordService;
	
	@Autowired
	ISubCategoryRepository subCategoryRepository;
	
	@Autowired
	IProductRepository productRepository;
	
	@GetMapping
	public String index(Model model, @RequestParam(defaultValue = "0") int page) {
		
		if(page < 0) page = 0;
	
		addPaginationAttributes(model, productPageRecordService.buildPageable(page, productList_size), page);

		return "product/index";
	}
	
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable String id) {
        byte[] image = imageService.getImageById(id);
        
        if (image == null) {
            return ResponseEntity.notFound().build(); 
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
    	
    	if(isCategoryNullOrEmpty(categoryName)) {
    		return "errorPage";
    	}
    	
    	if(page < 0) page = 0;
		
		addPaginationAttributes(model, productPageRecordService.buildPageableByCategory(categoryName, page, productList_size), page);
		model.addAttribute("categoryName", categoryName);
    	
    	return "product/category";
    }
    
    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "") String query, Model model, @RequestParam(defaultValue = "0") int page) {
		
    	if(page < 0) page = 0;
    	
		addPaginationAttributes(model, productPageRecordService.buildPageableByQuery(query, page, productList_size), page);
		model.addAttribute("query", query);
    	
    	return "product/search";
    }
    
    private boolean isProductGridNullOrEmpty(LinkedList<ProductRow> productRow) {
    	return productRow == null || productRow.isEmpty();
    }
    
    private boolean isCategoryNullOrEmpty(String categoryName) {
    	return categoryName == null || categoryName.isEmpty() || subCategoryRepository.findBySubcategoryName(categoryName) == null;
    }
    
    private void addPaginationAttributes(Model model, ProductPageable productRecord, int page) {
		model.addAttribute("productGrid", (isProductGridNullOrEmpty(productRecord.productGrid())) ? null : productRecord.productGrid());
		model.addAttribute("endPage", productRecord.endPage());
		model.addAttribute("currentPage", page);
    }
    
}
