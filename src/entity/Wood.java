package entity;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import model.Location;



/**
 * Represents wood building material.
 * 
 * @author harrison mfula
 * @since  20-04-2013
 */
@Entity
@NamedQuery(name = "findAllWoods", query = "SELECT w FROM Wood AS w")
public class Wood extends AvailabilityItem  {
	


	
@Embedded WoodCharacteristics 
woodCharacteristics;
	
	@ManyToOne()
//	@JoinColumn(name = "ava_Notice_id")
	private AvailabilityNotice availabilityNotice;
	


	
	public AvailabilityNotice getAvailabilityNotice() {
		return availabilityNotice;
	}

	public void setAvailabilityNotice(AvailabilityNotice availabilityNotice) {
//		availabilityNotice.setWood(this);
		this.availabilityNotice = availabilityNotice;
	}

	public Wood() {
		woodCharacteristics = new WoodCharacteristics();
	}

	public Wood(long quantity, Location location, String color, long density, boolean machinability) {
		this();
		setQuantity(quantity);
		setPhysicalLocation(location);
		setColor(color);
		setDensity(density);
		setMachinability(machinability);
	}

	public String getColor() {
		return woodCharacteristics.getColor();
	}

	public void setColor(String color) {
		woodCharacteristics.setColor(color);
	}

	public float getDensity() {
		return woodCharacteristics.getDensity();
	}

	public void setDensity(float density) {
		woodCharacteristics.setDensity(density);

	}

	public boolean getMachinability() {
		return woodCharacteristics.getMachinability();
	}

	public void setMachinability(boolean machinability) {
		woodCharacteristics.setMachinability(machinability);

	}


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Wood").append('{');
		sb.append("quantity=").append(getQuantity());;
		sb.append(", location=").append(getPhysicalLocation());
		sb.append(", color=").append(woodCharacteristics.getColor());
		sb.append(", density=").append(woodCharacteristics.getDensity());
		sb.append(", machinability=").append(woodCharacteristics.getMachinability());
		sb.append('}');
		return sb.toString();
	}
}
