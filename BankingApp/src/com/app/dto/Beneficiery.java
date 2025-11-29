package com.app.dto;

public record Beneficiery(String name,String accno) {

	public boolean validateBene(String name) {
	if(name!=null && name.equals("james"))return false;
	else return true;
	}
	
	
	
	public boolean equals(Object o) {
		return true;
	}
}
