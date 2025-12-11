package com.app.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class FlightUploadResult {

	private List<String>success=new ArrayList<>();
	private List<String>errors=new ArrayList<>();
	
	public void addSuccess(String msg) {
		success.add(msg);
	}
	
	public void addError(String msg) {
		errors.add(msg);
	}
}
