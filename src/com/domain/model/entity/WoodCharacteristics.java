package com.domain.model.entity;

import javax.persistence.Embeddable;

/**
 * Represents wood building material.
 * 
 * @author harrison mfula
 * @since  20-04-2013
 */
@Embeddable
public class WoodCharacteristics implements Characteristics{
	

	private String color;
	private float density;
	private boolean machinability;
	
	public WoodCharacteristics() {
	}

	public WoodCharacteristics( String color, long density, boolean machinability) {
	
		setColor(color);
		setDensity(density);
		setMachinability(machinability);
	}



	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public float getDensity() {
		return density;
	}

	public void setDensity(float density) {
		this.density = density;

	}

	public boolean getMachinability() {
		return machinability;
	}

	public void setMachinability(boolean machinability) {
		this.machinability = machinability;

	}


}
