package org.asalas.commands;

import java.util.List;

import org.asalas.domain.Unity;

public class UnityForm {
	private String id;
	private String name;
	private String convamount;
	private Boolean baseunit;
	private List<Unity> unitlist;
	private String unitid;
	private String simbol;
	
	public String getSimbol() {
		return simbol;
	}
	public void setSimbol(String simbol) {
		this.simbol = simbol;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getConvamount() {
		return convamount;
	}
	public void setConvamount(String convamount) {
		this.convamount = convamount;
	}
	public Boolean getBaseunit() {
		return baseunit;
	}
	public void setBaseunit(Boolean baseunit) {
		this.baseunit = baseunit;
	}
	public List<Unity> getUnitlist() {
		return unitlist;
	}
	public void setUnitlist(List<Unity> unitlist) {
		this.unitlist = unitlist;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
