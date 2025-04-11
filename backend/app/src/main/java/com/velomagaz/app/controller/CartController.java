package com.velomagaz.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.velomagaz.app.ViewModel.CartViewModel;
import com.velomagaz.app.service.CartSessionItemManager;
import com.velomagaz.app.service.CartViewModelBuilder;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartSessionItemManager sessionItemManager;
	
	@Autowired
	private CartViewModelBuilder cartViewModelBuilder;
	
	@GetMapping
	public String index(HttpSession session, Model model) {
		CartViewModel cartViewModel = cartViewModelBuilder.build((List<String>)session.getAttribute("items"));
		model.addAttribute("cart", cartViewModel);
		
		System.out.print(session.getAttribute("items"));
		
		return "cart/index";
	}
	
	@GetMapping("/add")
	public ResponseEntity<Void> addToCart(@RequestParam String id, HttpSession session){

		session.setAttribute("items", sessionItemManager.add((List<String>)session.getAttribute("items"), id)); 

		
		
		return ResponseEntity.ok().build();
	}
}
