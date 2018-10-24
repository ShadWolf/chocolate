package org.asalas.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Unity  extends AbstractDomainClass {
		private String name;
	//	private boolean isBasic; //example gra est la base de l unité de poids	
		private String simbol;
		@Column(precision=5, scale=3)
		private Double amount;
		@ManyToOne(fetch = FetchType.LAZY, optional = true)
		private Unity baseunit;
		
		
		
}
