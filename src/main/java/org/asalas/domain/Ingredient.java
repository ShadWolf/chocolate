package org.asalas.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Ingredient  extends AbstractDomainClass {
	private String name;
	private boolean alergene = false;
	//utilitaires pour faciliter les calculs de cout de revient et de stock dispo
	private Long quantity =  new Long(0); //s incremente a la recharge se decremente a l'usage. 
	// se met a 0 lors de la perempcion
	@Column(precision=5, scale=2)
	private float prixminunit = 0.0F; // init au moment de l'achat si on a des kilo on cacul le prix au gramme
	//si on a des litre on calcule le prix au centilitre.
	private Date dateperemtion = null; //date de perempcion des ingredients. init au dernier achat. sistem de gestion des ingredients non utiliser et jetter a la poubelle.
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Unity unit;  
}
