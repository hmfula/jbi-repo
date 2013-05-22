package com.domain.model.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.domain.model.Location;

/**
 * Realizes the base concept of an item available to the building material exchange. All items available extend this class.
 * 
 * @author harrison mfula
 * @since  25-04-2013
 */

@MappedSuperclass
public  class  AvailabilityItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	protected long quantity;
	
	protected Location location;

	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id =id;
	}
	
	

	public Long getQuantity() {
		return quantity;
	}
	
	public void setQuantity(long quantity) {
		this.quantity = quantity;

	}
	public Location getPhysicalLocation() {
		return location;
	}

	
	public void setPhysicalLocation(Location location) {
		this.location = location;

	}

}
