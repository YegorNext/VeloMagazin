package com.velomagaz.data_integration;

import java.math.BigDecimal;
import java.util.List;
import javax.sql.rowset.serial.SerialClob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.velomagaz.data_integration.constant.IndexConstants;
import com.velomagaz.data_integration.entity.Product;
import com.velomagaz.data_integration.entity.repository.IBrandRepository;

@Component
public class ProductFactory implements IProductFactory{	
	@Autowired
	private IBrandRepository brandRepository;
	
	public Product build (List<String> dataSetRow){
        Product product = new Product();

		try {
	        product.setId(dataSetRow.get(0));
	        product.setProductName(dataSetRow.get(2));
	        product.setDescription(new SerialClob(dataSetRow.get(4).toCharArray()));
	        product.setPrice(convertPriceToDecimal(dataSetRow.get(6)));
	        product.setBrand(brandRepository.findByBrandName(dataSetRow.get(IndexConstants.BRAND_IND)));
	        
	    } catch (Exception e) {
	        System.err.println("Error building product: " + e.getMessage());
	        return null;
	    }
		
		return product;
	}
	
	private BigDecimal convertPriceToDecimal(String price) {
		try {
			return new BigDecimal(price);
		}catch(NumberFormatException e) {
			System.out.print(e.getMessage());
		}
		return null;
	}
}
