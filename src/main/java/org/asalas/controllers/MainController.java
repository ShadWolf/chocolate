package org.asalas.controllers;

import java.io.Console;

import org.asalas.forms.MsgForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String welcome(Model model) {
		return "redirect:/dashboard";
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
	
	@RequestMapping("/msg")
	public String showMsg(@ModelAttribute("f") MsgForm f,
			ModelMap model) {
		System.out.println( "f msg : " + f.toString());
		model.addAttribute("page", "msgview");
		model.addAttribute("varForm", f.getNextpage());
		if(! "n".equals(f.getMsg())) {
			model.addAttribute("varMsg", f.getMsg());
		}
		if(! "n".equals(f.getErr())) {
			model.addAttribute("varMsg", f.getErr());
		}
		return "mainpage";
	}
}
