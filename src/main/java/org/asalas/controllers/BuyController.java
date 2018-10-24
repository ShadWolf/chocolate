package org.asalas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.asalas.domain.Achat;
import org.asalas.domain.Ingredient;
import org.asalas.domain.Unity;
import org.asalas.forms.AchatForm;
import org.asalas.forms.IngsForm;
import org.asalas.forms.UnityForm;
import org.asalas.services.AchatService;
import org.asalas.services.IngService;
import org.asalas.services.UnityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class BuyController {
	@Autowired
	AchatService achatService;
	
	@Autowired
	UnityService unityService;

	@Autowired
	private IngService ingserv;
	
	private String varMsg;
	
	@GetMapping("/buyform")
	public String showBuy(ModelMap model) {
		AchatForm aform =  new AchatForm(); //objet qui stoquera les donnees du formulaire d'achat.
		List<IngsForm> ifl = createListIngsForm();
		List<UnityForm> ufl =  createListUnitForm(); //lista de unidades
		List<AchatForm> afl = createListAchatForm(); 
		model.addAttribute("page", "buyform");
		model.addAttribute("varMsg", varMsg);
		model.addAttribute("varList", afl);
		model.addAttribute("varListSec", ifl);
		model.addAttribute("varListTre", ufl);
		model.addAttribute("varForm", aform );
		return "mainpage";
	} 
	
	private List<AchatForm> createListAchatForm(){
			List<AchatForm> afl = new ArrayList<>();
			List<Achat> al;
			try {
				al = achatService.listAll();
				for(Achat a : al) {
					AchatForm e = new AchatForm();
					e.setIngname(a.getIngredient().getName());
					e.setUnitsimb(a.getUnit().getSimbol());
					e.setQuantity(a.getQuantity().toString());
					e.setPriht(a.getPrixht().toString());
					e.setDate(a.getFecha().toString());
					afl.add(e);
				}
			} catch (Exception ex) {};
			return afl;
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
	//add Achat
	@PostMapping("/buyadd")
	public String addAchat(@ModelAttribute AchatForm form, ModelMap model) {
		
		
		Achat a = this.convAchatFormToAchat(form);
		achatService.save(a);
		this.varMsg = "Achat enregistre avec succes!";
		return showBuy(model);
	}
	private Achat convAchatFormToAchat( AchatForm form ) {
		Achat a = new Achat();
		System.out.println("Fomulario Achat Conversion" + form.toString() );
		
		Ingredient ing = ingserv.findById(Integer.valueOf(form.getIngid()));
		
		Long quantity = Long.valueOf(form.getQuantity());
		
		ing.setQuantity(ing.getQuantity() + quantity);
		a.setPrixht(Double.valueOf(form.getPriht()));
		a.setIngredient(ing);
		a.setQuantity(quantity);
		a.setUnit(unityService.findById(Integer.valueOf(form.getUnitid())));
		return a;
	}
	//del
	//delsuccess
}
