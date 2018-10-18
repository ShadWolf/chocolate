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
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UnityController {
	@Autowired
	private UnityService unityService;
	
	 
	// List Unity + formulario
	@GetMapping("/unitform")
	public String showUnityForm(ModelMap model){
		
	  	UnityForm unitform =  new UnityForm();
    	List<Unity> ul;
    	//recuperar todas las unidades existente
    	try {
    		ul = unityService.listAll();
    	} catch (Exception ex) {
    		ul =  new ArrayList<>();	
    	}
    	model.addAttribute("page", "unitform");
    	model.addAttribute("varList", ul);
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
	// Add Unity
	@PostMapping("/unitadd")
	public String addUnity(@ModelAttribute UnityForm unitForm, ModelMap model) {
		String msg = null ;
    	System.out.println("getUniyForm" + unitForm.toString());
    	
    	Unity u = this.convUnityFormToUnity(unitForm);
    	
    	unityService.save( u );
  
    	//unit.setConvunit(UnityService.getById(unitForm.getUnitid()));
    	msg = "Unit&eacute; enregistr&eacure;e avec succ&eagrave;s!";
    	model.addAttribute("page", "stockforms");
    	model.addAttribute("varMsg", msg);
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
			u.setAmount(Long.valueOf(uform.getAmount()));
		} else {
			u.setBaseunit(null);
			u.setAmount(Long.valueOf("0"));
		}
		u.setName(uform.getName());
		u.setSimbol(uform.getSimbol());
		return u;
	}

		// Remove Unity
}
