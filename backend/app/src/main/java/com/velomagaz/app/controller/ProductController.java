package com.velomagaz.app.controller;

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
import com.velomagaz.app.service.component.GridLayoutValidator;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private final int productListSize = 48; 
	
	@Autowired 
	ProductImageService imageService;
	
	@Autowired
	ProductInfoBuilder productInfoService;
	
	@Autowired
	ProductPageRecordBuilderService productPageRecordService;
	
	@Autowired
	GridLayoutValidator layoutValidator;

	@Autowired
	ProductPaginationService paginationService;
	
	@GetMapping
	public String index(Model model, @RequestParam(defaultValue = "0") int page) {
		
		page = paginationService.pageValidate(page);
	
		paginationService.addPaginationAttributes(model, productPageRecordService.buildPageable(page, productListSize), page);

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
    	if(!layoutValidator.isProductExists(id)) {
    		return "errorPage";
    	}
    	 	
    	model.addAttribute("productInfo", productInfoService.BuildInfo(id));

    	return "product/info";
    }
    
    @GetMapping("/category/{categoryName}")
    public String category(@PathVariable String categoryName, Model model, @RequestParam(defaultValue = "0") int page) {
    	
    	if(layoutValidator.isCategoryNullOrEmpty(categoryName)) {
    		return "errorPage";
    	}
    	
    	page = paginationService.pageValidate(page);
		
    	paginationService.addPaginationAttributes(model, productPageRecordService.buildPageableByCategory(categoryName, page, productListSize), page);
		model.addAttribute("categoryName", categoryName);
    	
    	return "product/category";
    }
    
    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "") String query, Model model, @RequestParam(defaultValue = "0") int page) {
		
    	page = paginationService.pageValidate(page);
    	
    	paginationService.addPaginationAttributes(model, productPageRecordService.buildPageableByQuery(query, page, productListSize), page);
		model.addAttribute("query", query);
    	
    	return "product/search";
    }
    
    
}
