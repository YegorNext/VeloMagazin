package com.velomagaz.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.velomagaz.app.service.CartSessionItemManager;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartSessionItemManager sessionItemManager;
	
	@GetMapping
	public String index(HttpSession session) {
		return "cart/index";
	}
	
	@PostMapping("/add")
	public ResponseEntity<Void> addToCart(@RequestParam String id, HttpSession session){

		session.setAttribute("items", sessionItemManager.add((List<String>)session.getAttribute("items"), id)); 
		
		return ResponseEntity.ok().build();
	}
}
