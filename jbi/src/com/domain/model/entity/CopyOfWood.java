package com.domain.model.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;


import com.domain.model.Location;

/**
 * Represents wood building material.
 * 
 * @author harrison mfula
 * @since  20-04-2013
 */
@Entity
@NamedQuery(name = "findAllWoods", query = "SELECT w FROM Wood AS w")
public class CopyOfWood extends AvailabilityItem  {
	


	

	private String color;
	private float density;
	private boolean machinability;
	
//	@ManyToOne()
//	@JoinColumn(name = "ava_Notice_id")
//	private AvailabilityNotice availabilityNotice;
	


//	
//	public AvailabilityNotice getAvailabilityNotice() {
//		return availabilityNotice;
//	}
//
//	public void setAvailabilityNotice(AvailabilityNotice availabilityNotice) {
//		this.availabilityNotice = availabilityNotice;
//	}

	public CopyOfWood() {
	}

	public CopyOfWood(long quantity, Location location, String color, long density, boolean machinability) {
		setQuantity(quantity);
		setPhysicalLocation(location);
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


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Wood").append('{');
		sb.append("id=").append(id);
		sb.append(", quantity=").append(getQuantity());;
		sb.append(", location=").append(getPhysicalLocation());
		sb.append(", color=").append(color);
		sb.append(", density=").append(density);
		sb.append(", machinability=").append(machinability);
		sb.append('}');
		return sb.toString();
	}
}
