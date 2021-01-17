package com.servletjsp.web.servlet.pojos;

public class Task {

	private String description;
	private String date;
	private String checked;
	
	public Task () {}
	
	public Task (String description, String date, String checked) {
		this.description = description;
		this.date = date;
		this.checked = checked;
	}
	
	public Task setDescription (String description) {
		this.description = description;
		return this;
	}
	
	public Task setDate (String date) {
		this.date = date;
		return this;
	}
	
	public Task setChecked (String checked) {
		this.checked = checked;
		return this;
	}

	public Task build() {
		return new Task(description, date, checked);

	}
	
	public String getDescription() {
		return description;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getChecked() {
		return checked;
	}
}
