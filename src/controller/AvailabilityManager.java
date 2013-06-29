package controller;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import entity.AvailabilityNotice;
import entity.Wood;

@Stateless
public class AvailabilityManager {

   
	  private static final String PERSISTENCE_UNIT_NAME = "jbi";
//	  private EntityManagerFactory factory;
	 
	  @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	  private EntityManager em;
	  
	  
	  public AvailabilityManager(){
//		  
//		  factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//		  em = factory.createEntityManager();
		  
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
//		 EntityTransaction tx = em.getTransaction();
		  
//		  tx.begin();
		  em.persist(availabilityNotice);
//		  tx.commit();
		
		  return   findAllAvailabilityNotices().contains(availabilityNotice) ? true : false;
		  
	}

	public boolean removeAvailabilityNotice(long availabilityNoticeId) {
//		 EntityTransaction tx = em.getTransaction();
		  
//		  tx.begin();
		  AvailabilityNotice availabilityNotice = findAvailabilityNotice(availabilityNoticeId);
		  em.remove(availabilityNotice);
//		  tx.commit();
		  return findAllAvailabilityNotices().contains(availabilityNotice) ? false : true;
	}
	

	public List<AvailabilityNotice> findAllAvailabilityNotices() {
		Query query = em.createNamedQuery("findAllAvailabilityNotices");
		@SuppressWarnings("unchecked")
		List<AvailabilityNotice> list = ((List<AvailabilityNotice>) query.getResultList());
		return list;
	}

	public boolean removeWoodFromAvailabilityNotice(AvailabilityNotice  availabilityNotice, Wood wood) {
//		 EntityTransaction tx = em.getTransaction();		  
//		  			tx.begin();
					em.remove(wood);
//					tx.commit();
					em.refresh(availabilityNotice);
		AvailabilityNotice updatedAvailabilityNotice= findAvailabilityNotice(availabilityNotice.getId());
		return updatedAvailabilityNotice.getWood().contains(wood) ? false : true;
	}

	public boolean addWoodToAvailabilityNotice(AvailabilityNotice availabilityNotice, Wood wood) {
//		 EntityTransaction tx = em.getTransaction();		  
//		  tx.begin();
		  availabilityNotice.setWood(wood);
		  em.persist(availabilityNotice);
//		  tx.commit();

		return  findAllAvailabilityNotices().contains(availabilityNotice) ? true : false;
	}
	
	
	
	
	public boolean addWoodToAvailabilityNotice(AvailabilityNotice availabilityNotice, int woodAmount) {
//		 EntityTransaction tx = em.getTransaction();		  
//		  tx.begin();
		  availabilityNotice.setWood1(woodAmount);
		  em.persist(availabilityNotice);
//		  tx.commit();

		return  findAllAvailabilityNotices().contains(availabilityNotice) ? true : false;
	}

}
