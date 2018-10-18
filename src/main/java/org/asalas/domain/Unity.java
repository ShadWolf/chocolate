package org.asalas.domain;

import javax.persistence.*;

@Entity
public class Unity  extends AbstractDomainClass {
		private String name;
	//	private boolean isBasic; //example gra est la base de l unité de poids	
		private String simbol;
		private Long amount;
		@ManyToOne(fetch = FetchType.LAZY, optional = true)
		private Unity baseunit;
		
		
		
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSimbol() {
			return simbol;
		}
		public void setSimbol(String simbol) {
			this.simbol = simbol;
		}
		public Unity getBaseunit() {
			return baseunit;
		}
		public void setBaseunit(Unity baseunit) {
			this.baseunit = baseunit;
		}
		public Long getAmount() {
			return amount;
		}
		public void setAmount(Long amount) {
			this.amount = amount;
		}
		
		
}
