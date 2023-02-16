package com.example.web.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

	@GetMapping(value = "/user/list")
	public String displayList(Model model) {

		model.addAttribute("id", 100);
		model.addAttribute("name", "サンプル太郎");
		model.addAttribute("address", "東京都新宿区1-2-3");
		model.addAttribute("phone", "090-1234-1234");
		model.addAttribute("updateDate", new Date());

		return "user/list";
	}

	@GetMapping("/user/{id}")
	public String displayDetail(@PathVariable Long id, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("name", "サンプル太郎");
		model.addAttribute("address", "東京都新宿区1-2-3");
		model.addAttribute("phone", "090-1234-1234");
		model.addAttribute("updateDate", new Date());
		model.addAttribute("createDate", new Date());
		model.addAttribute("deleteDate", null);

		return "user/detail";
	}
}