package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

	@GetMapping(value = "/")
	public String index(Model model) {
		model.addAttribute("message", "ようこそ");
		return "index";
	}

}