package com.domain.model;

import java.io.Serializable;
/**
 * Represents a location of the building material.
 * 
 * @author harrison mfula
 * @since  20-04-2013
 */

public class Location implements Serializable {

	private static final long serialVersionUID = 3056706664781364075L;
	private String name;
	private String address;
	
	
	public Location(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public Location() {
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append('{');
		sb.append("name=").append(name).append(',');
		sb.append(" address=").append(address);
		sb.append('}');
		return sb.toString();
	}
	
}
