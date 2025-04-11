package com.velomagaz.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalModelAttrubites {
	
	@ModelAttribute("hasCartItems")
	public boolean hasCartItems(HttpSession session) {
		List<String> items = (List<String>) session.getAttribute("items");
		
		System.out.print(items);
		
		return items != null && !items.isEmpty();
	}
}
