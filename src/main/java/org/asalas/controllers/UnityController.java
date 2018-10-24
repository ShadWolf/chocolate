package org.asalas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.asalas.domain.Unity;
import org.asalas.forms.UnityForm;
import org.asalas.services.UnityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UnityController {
	@Autowired
	private UnityService unityService;
	
	 private String varMsg;
	// List Unity + formulario
	@GetMapping("/unitform")
	public String showUnityForm(ModelMap model){
		
	  	UnityForm unitform =  new UnityForm();
	  	List<UnityForm> ufl = createListUnityForm();
    	model.addAttribute("page", "unitform");
    	model.addAttribute("varMsg", varMsg);
    	model.addAttribute("varList", ufl);
    	model.addAttribute("varForm", unitform );
    	/*
    	 * NOTA BENE:
    	 le formulaire doit comporter un objet special qui lui permetra de recuperer les donnes de maniere 
    	 numerique et permetra en post traitement de convertir ses données en objet 
    	 
    	 par example ici dans le formulaire on a besoin de recuprer l'id de l unite de base parent.
    	 Pour en suite pouvoir aller chercher cette unité de base et la stocke dans un objet avant de sauvegarder 
    	 a nouveau l unité de base.
    	 	L' objet unityFrom ici servira d intermediare thymeleaf le renvera rempli 
    	 	et la methode addunit traitera la conversion de set objet simplifier en objet definitif de notre 
    	 	base de donnee.
    	 * */
    	return "mainpage";
	}
	private List<UnityForm> createListUnityForm(){
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
	

	// Add Unity
	@PostMapping("/unitadd")
	public String addUnity(@ModelAttribute UnityForm unitForm, ModelMap model) {
		String msg = null ;
    	System.out.println("getUniyForm" + unitForm.toString());
    	
    	Unity u = this.convUnityFormToUnity(unitForm);
    	
    	unityService.save( u );
  
    	//unit.setConvunit(UnityService.getById(unitForm.getUnitid()));
    	
    	
    	model.addAttribute("page", "unitform");
    	msg = "Unite enregistre avec succes!";
    	model.addAttribute("varMsg", msg);
    	List<UnityForm> ufl = createListUnityForm();
    	model.addAttribute("varList", ufl);
    	UnityForm unitform = new UnityForm();
    	model.addAttribute("varForm", unitform );
    	return "mainpage";
		// return "mainpage";
	}
	
	private Unity convUnityFormToUnity(UnityForm uform){
		Unity u =  new Unity();
		
    	if(uform.getUnitparentid() != null && 
    	!StringUtils.isEmpty(uform.getUnitparentid()) ) {
			Integer id = Integer.valueOf(uform.getUnitparentid());
			Unity un = unityService.findById(id);
			u.setBaseunit( un );
			u.setAmount(Double.valueOf(uform.getAmount()));
		} else {
			u.setBaseunit(null);
			u.setAmount(Double.valueOf("0"));
		}
		u.setName(uform.getName());
		u.setSimbol(uform.getSimbol());
		return u;
	}

		// Remove Unity
	@PostMapping("/unitdel/{id}")
	public String delUnity(@PathVariable String id, ModelMap model) {
		varMsg = "Unite supprimee avec succes!";
		unityService.delId(Integer.valueOf(id));
		return "redirect:/unitform";
	}
}
