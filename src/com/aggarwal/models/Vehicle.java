package com.aggarwal.models;
public class Vehicle {
	private int id;
	private int year;
	private String make;
	private String model;
	
	@Override
	public int hashCode() {
		int result = 0;
		int primeOffset = 31;
		result += primeOffset*((Integer)this.id).hashCode();
		result += primeOffset*((Integer)this.year).hashCode();
		result += primeOffset*make.hashCode();
		result += primeOffset*model.hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vehicle) {
			Vehicle other = (Vehicle) obj;
			return other.id == this.id && other.make.equals(this.make) 
					&& other.model.equals(this.model) && other.year == this.year;
		} else {
			return false;
		}
	}
	
	public Vehicle(int id, int year, String make, String model) {
		if(id < 0) {
			throw new IllegalArgumentException("Id has to be positive" + id);
		}
		if(year < 1950 || year > 2050) {
			throw new IllegalArgumentException("Year has to be between 1950 and 2050" + year);
		}
		if(make == null || make.equals("")) {
			throw new IllegalArgumentException("Make has to be non empty");
		}
		if(model == null || model.equals("")) {
			throw new IllegalArgumentException("Model has to be non empty");
		}
		setId(id);
		setYear(year);
		setMake(make);
		setModel(model);
	}

	public String getModel() {
		return this.model;
	}
	public void setModel(String model) {
		if(model == null || model.equals("")) {
			throw new IllegalArgumentException("Model has to be non empty");
		}
		this.model = model;
	}

	public String getMake() {
		return this.make;
	}
	public void setMake(String make) {
		if(make == null || make.equals("")) {
			throw new IllegalArgumentException("Make has to be non empty");
		}
		this.make = make;
	}

	public int getYear() {
		return this.year;
	}
	public void setYear(int year) {
		if(year < 1950 || year > 2050) {
			throw new IllegalArgumentException("Year has to be between 1950 and 2050" + year);
		}
		this.year = year;
	}

	public int getId() {
		return this.id;
	}
	private void setId(int id) {
		if(id < 0) {
			throw new IllegalArgumentException("Id has to be positive" + id);
		}
		this.id = id;	
	}
}
