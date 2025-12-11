package com.app.dto;

public class FileInfo {
	private String name;
	  private String url;

	  public FileInfo(String name, String url) {
	    this.name = name;
	    this.url = url;
	  }

	  public String getName() { return name; }
	  public String getUrl() { return url; }
}
