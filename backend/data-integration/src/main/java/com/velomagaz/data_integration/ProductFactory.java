package com.velomagaz.data_integration;

import java.math.BigDecimal;
import java.util.List;
import javax.sql.rowset.serial.SerialClob;
import org.springframework.stereotype.Component;
import com.velomagaz.data_integration.entity.Product;

@Component
public class ProductFactory implements IProductFactory{
	
	public Product build (List<String> productInfo){
        Product product = new Product();

		try {
	        product.setId(productInfo.get(0));
	        product.setProductName(productInfo.get(2));
	        product.setDescription(new SerialClob(productInfo.get(4).toCharArray()));
	        product.setPrice(convertPriceToDecimal(productInfo.get(6)));
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
