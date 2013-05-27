package com.jbi.controller;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


import com.domain.model.entity.AvailabilityNotice;
import com.domain.model.entity.Wood;
//@Stateless

public class AvailabilityManager {

//    @PersistenceContext(unitName = "chapter10PU")
  
	  private static final String PERSISTENCE_UNIT_NAME = "jbi";
	  private EntityManagerFactory factory;
	  private EntityManager em;
	  
	  
	  public AvailabilityManager(){
		  
		  factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		  em = factory.createEntityManager();
		  
	  }
    
	@SuppressWarnings("unchecked")
	public AvailabilityNotice findAvailabilityNotice(long availabilityNoticeId) {
		  Query query = em.createNamedQuery("findAllAvailabilityNotices");
		  for(AvailabilityNotice avN : (List<AvailabilityNotice>)query.getResultList()){
			 if(avN.getId() == availabilityNoticeId){
				return avN; 
			 }
			  
		  }
		return null;
	}
	

	public boolean createAvailabilityNotice(AvailabilityNotice availabilityNotice) {
		 EntityTransaction tx = em.getTransaction();
		  
		  tx.begin();
		  em.persist(availabilityNotice);
		  tx.commit();
		
		  return   findAllAvailabilityNotices().contains(availabilityNotice) ? true : false;
		  
	}

	public boolean removeAvailabilityNotice(long availabilityNoticeId) {
		 EntityTransaction tx = em.getTransaction();
		  
		  tx.begin();
		  AvailabilityNotice availabilityNotice = findAvailabilityNotice(availabilityNoticeId);
		  em.remove(availabilityNotice);
		  tx.commit();
		  return findAllAvailabilityNotices().contains(availabilityNotice) ? false : true;
	}
	

	public List<AvailabilityNotice> findAllAvailabilityNotices() {
		Query query = em.createNamedQuery("findAllAvailabilityNotices");
		@SuppressWarnings("unchecked")
		List<AvailabilityNotice> list = ((List<AvailabilityNotice>) query.getResultList());
		return list;
	}

	public boolean removeWoodFromAvailabilityNotice(AvailabilityNotice  availabilityNotice, Wood wood) {
		 EntityTransaction tx = em.getTransaction();		  
		  			tx.begin();
					em.remove(wood);
					tx.commit();
					em.refresh(availabilityNotice);
		AvailabilityNotice updatedAvailabilityNotice= findAvailabilityNotice(availabilityNotice.getId());
		return updatedAvailabilityNotice.getWood().contains(wood) ? false : true;
	}

	public boolean addWoodToAvailabilityNotice(AvailabilityNotice availabilityNotice, Wood wood) {
		 EntityTransaction tx = em.getTransaction();		  
		  tx.begin();
		  availabilityNotice.setWood(wood);
		  em.persist(availabilityNotice);
		  tx.commit();

		return  findAllAvailabilityNotices().contains(availabilityNotice) ? true : false;
	}

}
