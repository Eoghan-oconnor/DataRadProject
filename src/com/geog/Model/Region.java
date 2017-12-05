package com.geog.Model;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class Region {
	
	private String code;
	private String regCode;
	private String regName;
	private String regDetails;
	
	public Region() {
		super();
		
	}
	
	public Region(String code, String reCode, String regName, String regDetails){
		this.code = code;
		this.regCode = reCode;
		this.regName = regName;
		this.regDetails = regDetails;
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	public String getRegName() {
		return regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}

	public String getRegDetails() {
		return regDetails;
	}

	public void setRegDetails(String regDetails) {
		this.regDetails = regDetails;
	}
	
	
	

}
