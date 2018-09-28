package org.asalas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	  @RequestMapping(value = {"/"}, method = RequestMethod.GET)
	    public String welcome(Model model) {
	    	return "redirect:/login";
	    } 
	   @RequestMapping("/dashboard")
	    public String showDashboard(ModelMap model) {
	    	model.addAttribute("page", "dashboard");
	    	return "mainpage";
	    }
	   
	    @RequestMapping("/charts")
	    public String showCharts(ModelMap model) {
	    	model.addAttribute("page", "charts");
	    	return "mainpage";
	    }
	    @RequestMapping("/tables")
	    public String showTables(ModelMap model) {
	    	model.addAttribute("page", "tables");
	    	return "mainpage";
	    }
	    @RequestMapping("/stocks")
	    public String showStockForm(ModelMap model) {
	    	model.addAttribute("page", "stockforms");
	    	return "mainpage";
	    }
}
