package org.asalas.forms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AchatForm {
	private String ingname; //liste
	private String ingid; // form
	private String quantity; // form et liste
	private String date; //liste
	private String priht; // form et liste
	private String unitsimb; //liste
	private String unitid; //fomrulario
	
	@Override
	public String toString() {
		return "AchatForm [ingname=" + ingname + ", ingid=" + ingid + ", quantity=" + quantity + ", date=" + date
				+ ", priht=" + priht + ", unitsimb=" + unitsimb + ", unitid=" + unitid + "]";
	}
	

}
