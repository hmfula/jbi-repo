package com.domain.model.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.domain.model.EMaterialType;

/**
 * Realizes the concept of an availability notice used by the inventory managers to make construction materials available for bidding.
 * 
 * @author harrison mfula
 * @since  20-04-2013
 */
@Entity
//@Table(name="availability_notice")//make the name well readable in DB by renaming 
@NamedQuery(name = "findAllAvailabilityNotices", query = "SELECT an FROM AvailabilityNotice AS an")

public class AvailabilityNotice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private	long id;
	
	private Timestamp availableDate;

	private float guidePrice;


	
	@OneToMany(mappedBy = "availabilityNotice", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})//describes a field in the owning entity// wood cannot exist without an availability notice
	private List<Wood> woodInventory = new ArrayList<Wood>();
	

	@Transient
	private Map<EMaterialType, Float> guidePrices = new HashMap<EMaterialType, Float>(); 
	
	public AvailabilityNotice() {
	}
	
	
	//Begin API
	
	
	public long getId() {
		return id;
	}
	
	
	public Timestamp getAvailableDate() {
		return availableDate;
	}
	
	public void setAvailableDate(Timestamp availableDate) {
		this.availableDate = availableDate;
	}
	
	
	public void setGuidePrice(EMaterialType materialType, float guidePrice) {
		if(guidePrices.get(materialType) == null){
			guidePrices.put(materialType, guidePrice);
		}
		
	}
	
	public List<Wood> getWood() {
		return woodInventory;
	}
	public void setWood(Wood wood) {
		wood.setAvailabilityNotice(this);//This is important to update the other side of the relationship
		woodInventory.add(wood);
	}
	
	//End API
	
	
	
	
	public float getGuidePrice(EMaterialType materialType) {
		
		//TODO To be moved to a pricer component
		switch(materialType){
		
		case WOOD:
			guidePrice =guidePrices.get(EMaterialType.WOOD);
			break;
			
		case CONCRETE:
			guidePrice = guidePrices.get(EMaterialType.CONCRETE);
			break;
			
		case STEEL:
			guidePrice = guidePrices.get(EMaterialType.STEEL);
			break;
			
		default:
			System.out.println("Unknown product");
			break;
			
		}
		return guidePrice;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName()).append('{');
		sb.append("id=").append(id);
//		sb.append(", woodInventory=").append(woodInventory);;
//		sb.append(", steelInventory=").append(steelInventory);
//		sb.append(", concreteInventory=").append(concreteInventory);
		sb.append(", guidePrices=").append(guidePrices);
		sb.append(", availableDate=").append(availableDate);
		
		sb.append('}');
		return sb.toString();
	}
	
}
