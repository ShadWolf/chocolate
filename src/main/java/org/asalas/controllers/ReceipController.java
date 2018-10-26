package org.asalas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.asalas.forms.ReceipForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReceipController {
	private String varMsg;
	
	@GetMapping("/receip")
	public String showReceip(ModelMap model) {
		ReceipForm form = new ReceipForm();
		model.addAttribute("page", "receip");
		model.addAttribute("varForm", form);
		return "mainpage";
	}
	
	private List<ReceipForm> createListReceip(){
		 List<ReceipForm> list = new ArrayList<>();
		 return list;
	}
	
}
