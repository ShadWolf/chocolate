package org.asalas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.asalas.domain.Ingredient;
import org.asalas.domain.Unity;
import org.asalas.forms.IngsForm;
import org.asalas.forms.MsgForm;
import org.asalas.forms.UnityForm;
import org.asalas.services.IngService;
import org.asalas.services.UnityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IngsController {
	@Autowired
	private IngService ingserv;
	
	@Autowired
	private UnityService unityService;
	
	private String varMsg;
	
	@GetMapping("/ingform")
	public String showIngs(ModelMap model) {
		IngsForm ingform =  new IngsForm();
	  	List<IngsForm> ifl = createListIngsForm();
	  	List<UnityForm> ufl =  createListUnitForm();
	  	model.addAttribute("varMsg", varMsg);
    	model.addAttribute("page", "ingsform");
    	model.addAttribute("varList", ifl);
    	model.addAttribute("varListSec", ufl);
    	model.addAttribute("varForm", ingform );
		return "mainpage";
	}  
	
	private List<UnityForm> createListUnitForm(){
		List<UnityForm> ufl = new ArrayList<>();
    	List<Unity> ul;
    	//recuperar todas las unidades existente
    	try {
    		ul = unityService.listAll();
    		for (Unity u : ul) {
    			UnityForm e = new UnityForm();
    			e.setName(u.getName());
    			e.setSimbol(u.getSimbol());
    			e.setId(u.getId().toString());
    			if(u.getBaseunit() != null) {
    				e.setBasename(u.getBaseunit().getName());
    				e.setAmount(u.getAmount().toString());
    			}
    			ufl.add(e);
    		}
    	} catch (Exception ex) {}
    	return ufl;
	}
	
	private List<IngsForm> createListIngsForm() {
		List<IngsForm> ufl =  new ArrayList<>();
    	List<Ingredient> ul;
    	//recuperar todas las unidades existente
    	try {
    		ul = ingserv.listAll();
    		for (Ingredient u : ul) {
    			IngsForm e = new IngsForm();
    			e.setName(u.getName());
    			e.setQuantity(u.getQuantity().toString());;
    			e.setId(u.getId().toString());
    			e.setAlergene(u.isAlergene());
    			if(u.getUnit() != null) {
    				e.setUnitname(u.getUnit().getName());
    			}
    			ufl.add(e);
    		}
    	} catch (Exception ex) {}
    	return ufl;
	}
	//add
		@PostMapping("/ingadd")
		public String addUnity(@ModelAttribute IngsForm ingForm,
				RedirectAttributes redirectAttributes) {
	    	Ingredient i = this.convIngFormToIngredient(ingForm);  	
	    	ingserv.save( i );
	  
	    	varMsg = "Unite enregistre avec succes!";
	    	MsgForm f = new MsgForm();
	    	f.setNextpage("unitform");
	    	f.setMsg(varMsg);
	    	redirectAttributes.addFlashAttribute("f", f);
	    	return "redirect:/msg";
		}
		
		private Ingredient convIngFormToIngredient(IngsForm form) {
			Ingredient i =  new Ingredient();

			i.setAlergene(form.isAlergene());
			i.setName(form.getName());
			i.setUnit(unityService.findById(Integer.valueOf(form.getUnitid() )));
			return i;
		}
	//del
		@PostMapping("/ingdel/{id}")
		public String delIng(@PathVariable String id,
				RedirectAttributes redirectAttributes) {
			varMsg = "Ingredient " + ingserv.delId(Integer.valueOf(id)) + " supprimee avec succes!"; 
			MsgForm f = new MsgForm();
	    	f.setNextpage("unitform");
	    	f.setMsg(varMsg);
	    	redirectAttributes.addFlashAttribute("f", f);
	    	return "redirect:/msg";
		}
}