

import static org.junit.Assert.*;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.domain.model.EMaterialType;
import com.domain.model.Location;
import com.domain.model.entity.AvailabilityNotice;
import com.domain.model.entity.Wood;
import com.jbi.controller.AvailabilityManager;



public class JustBuildItTest {

  private static final String PERSISTENCE_UNIT_NAME = "jbi";
  private EntityManagerFactory emFactory;
private EntityManager eManager;

  @Before
  public void setUp() throws Exception {
    emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    eManager = emFactory.createEntityManager();
    eManager.clear();
  }
  
  @After
  public void tearDown() throws Exception {
	 if(eManager!=null && eManager.isOpen() ){
		 eManager.close();
	 }
  }
//  @Test //
//  public void saveAndRetrieveWood() {
//	  String woodAsString = "Wood{quantity=10, location={name=Espoo, address=02600}, color=Brown, density=1.4, machinability=true}";
//	  Wood wood = createWood();
//	  
//	  eManager.getTransaction().begin();
//	  eManager.persist(wood);
//	  eManager.getTransaction().commit();
//	  
//	  Query query = eManager.createNamedQuery("findAllWoods");
//	  assertTrue(query.getResultList().size() == 1);
//	  assertEquals(woodAsString, ((Wood)query.getResultList().get(0)).toString());	  
//	  
//	  
//  }

  private Wood createWood() {
	Wood wood = new Wood();
	  wood.setColor("Brown");
	  wood.setDensity(1.4f);
	  wood.setMachinability(true);
	  wood.setPhysicalLocation(new Location("Espoo", "02600"));
	  wood.setQuantity(10);
	return wood;
}


  @Test
  public void testAddWooodToAvailabilityNotice() {
	  String woodAsString = "Wood{quantity=10, location={name=Espoo, address=02600}, color=Brown, density=1.4, machinability=true}";
	  Wood wood1 = createWood();
	  Wood wood = createWood();
	  AvailabilityNotice an = new AvailabilityNotice();
	  an.setGuidePrice(EMaterialType.WOOD, 200);
	  an.setWood(wood);
	
	  EntityTransaction tx =  eManager.getTransaction();
	  tx.begin();
	  
	  eManager.persist(an);
	  tx.commit();
	  
	  Query query = eManager.createNamedQuery("findAllAvailabilityNotices");
	  assertTrue(query.getResultList().size() == 1);
	  assertEquals(woodAsString, ((AvailabilityNotice)query.getResultList().get(0)).getWood().get(0).toString());	
	  
	  
	  System.err.println("Wood description:  " + ((AvailabilityNotice)query.getResultList().get(0)).getWood().toString());
	  assertEquals("[Wood{quantity=10, location={name=Espoo, address=02600}, color=Brown, density=1.4, machinability=true}]",((AvailabilityNotice)query.getResultList().get(0)).getWood().toString());

	  System.err.println( "Initial Wood list size  :  " +((AvailabilityNotice)query.getResultList().get(0)).getWood().size()); //proof  1 wood item before adding more wood
	  assertEquals(1,((AvailabilityNotice)query.getResultList().get(0)).getWood().size());
	  System.err.println("Guide price for wood:  " + ((AvailabilityNotice)query.getResultList().get(0)).getWood().get(0).getAvailabilityNotice().getGuidePrice(EMaterialType.WOOD));//notice->wood[0]->notice->check guide price
	  
	  query = eManager.createNamedQuery("findAllWoods");
	  assertTrue(query.getResultList().size() == 1);
	 System.err.println("Wood table rows before adding  :  " +query.getResultList().size());
	 
	 
	  assertEquals(200, ((Wood)query.getResultList().get(0)).getAvailabilityNotice().getGuidePrice(EMaterialType.WOOD),0);	 //wood->notice-check guide price
	 
	  
	  
	  query = eManager.createNamedQuery("findAllAvailabilityNotices");
	  assertTrue(query.getResultList().size() == 1);
	  int s = ((AvailabilityNotice)query.getResultList().get(0)).getWood().size();
	  System.err.println("AvailabilityNotice table rows before adding: "+s+ " Wood exist");
	  
	  tx =  eManager.getTransaction();
	  tx.begin();
	  an.setWood(wood1);
	  eManager.persist(an);
	  
	  s = ((AvailabilityNotice)query.getResultList().get(0)).getWood().size();
	  System.err.println("AvailabilityNotice table rows after adding : "+s + " Wood exist");
	 
	  
	  query = eManager.createNamedQuery("findAllWoods");
	  assertTrue(query.getResultList().size() == 2);
	  System.err.println("Wood table rows after adding  : " + query.getResultList().size());
	  
		  tx =  eManager.getTransaction();
		  eManager.remove(an);//remove the used an from DB
		  tx.commit();
	  
  }

  
  
  @Test
  public void testRemoveWoodFromAvailabilityNotice() {
	  
	  Wood wood1 = createWood();
	  Wood wood = createWood();
	  AvailabilityNotice an = new AvailabilityNotice();
	  an.setGuidePrice(EMaterialType.WOOD, 200);
	  an.setWood(wood);
	  an.setWood(wood1);
	  
	  EntityTransaction tx =  eManager.getTransaction();
	  
	  tx.begin();
	  eManager.persist(an);
	  tx.commit();
	  
	  Query query = eManager.createNamedQuery("findAllAvailabilityNotices");
	  assertTrue(query.getResultList().size() == 1);
	  
	  int s = ((AvailabilityNotice)query.getResultList().get(0)).getWood().size();
	  System.err.println("Wood table rows BEFORE  removal, list size is: "+s);
	  an.getWood().size();
	  
	  tx =  eManager.getTransaction();
	  
	  tx.begin();
      Wood we = ((AvailabilityNotice)query.getResultList().get(0)).getWood().get(0);
      eManager.remove(we);
	  tx.commit();
	  
	  eManager.refresh(an);
	 
	  s = ((AvailabilityNotice)query.getResultList().get(0)).getWood().size();
	  System.err.println("Wood table rows AFTER removal, list size is: "+s);
     
	  assertTrue(an.getWood().size() == 1);
	  
  }
  
  
  @Test
  public void testFindAvailabilityNotice() {
	  
	  AvailabilityManager availabilityManager = new AvailabilityManager();
	  long availabilityNoticeId = 4L;
	  AvailabilityNotice availabilityNotice = availabilityManager.findAvailabilityNotice(availabilityNoticeId);
	  System.err.println("Searching for availabilityNotice with Id: " + availabilityNotice.getId()); 
	  
	  System.err.println("Found availabilityNotice with Id: " + availabilityNotice.getId() + ". It contains  "+ availabilityNotice.getWood().toString());
  }
  
  @Test
  public void testCreateAvailabilityNotice() {
	  AvailabilityManager availabilityManager = new AvailabilityManager();
	  AvailabilityNotice an = new AvailabilityNotice();
      boolean availabilityNoticeStatus = availabilityManager.createAvailabilityNotice(an);
      assertTrue(availabilityNoticeStatus);
	  
	  System.err.println("availabilityNotice created:  " + availabilityNoticeStatus + "  Id is : " + an.getId());
	  
  }
  @Test
  public void testRemoveAvailabilityNotice() {
	  AvailabilityManager availabilityManager = new AvailabilityManager();
	  AvailabilityNotice an = new AvailabilityNotice();
	  
	  availabilityManager.createAvailabilityNotice(an);
	  List<AvailabilityNotice> allNotices = availabilityManager.findAllAvailabilityNotices();
	  System.err.println("availabilityNotice list size BEFORE REMOVAL:  " + allNotices.size());
	  
      boolean availabilityNoticeStatus = availabilityManager.removeAvailabilityNotice(4);
      System.err.println("availabilityNoticeStatus flag after remove:  " + availabilityNoticeStatus);
      assertTrue(availabilityNoticeStatus);
      
       allNotices = availabilityManager.findAllAvailabilityNotices();
      System.err.println("availabilityNotice list size  AFTER REMOVAL:  " + allNotices.size());	  
  }
  
  @Test
  public void testAddWoodToAvailabilityNotice() {
	  
	  AvailabilityManager availabilityManager = new AvailabilityManager();
	  
	  
	  
	  Wood wood = new Wood();
	  wood.setColor("yellow");
	  wood.setDensity(300f);
	  wood.setMachinability(true);
	  wood.setPhysicalLocation(new Location("KILO", "600"));
	  wood.setQuantity(1);
	  
	  AvailabilityNotice an = new AvailabilityNotice();
	  boolean availabilityNoticeStatus  = availabilityManager.addWoodToAvailabilityNotice(an,wood );
      assertEquals(1, availabilityManager.findAvailabilityNotice(an.getId()).getWood().size());
      assertTrue(availabilityNoticeStatus);
      
	  System.err.println("XXXXXX  AFTER ADDING OF WOOD to AvailabilityNotice:   " + availabilityManager.findAvailabilityNotice(an.getId()).getWood());
  }
  
  @Test
  public void testRemoveAvailabilityNoticeWood() {
	  AvailabilityManager availabilityManager = new AvailabilityManager();
	  Wood wood = createWood();
	  AvailabilityNotice an = new AvailabilityNotice();
	  boolean availabilityNoticeStatus  = availabilityManager.addWoodToAvailabilityNotice(an,wood );
	  System.err.println("XXXXXX  BEFORE REMOVAL OF WOOD:  " + availabilityManager.findAvailabilityNotice(an.getId()).getWood().size()); 
	  System.err.println("Flag before: " + false); 
	  availabilityNoticeStatus  = availabilityManager.removeWoodFromAvailabilityNotice(an, wood);
	 
	  System.err.println("XXXXXX  AFTER REMOVAL OF WOOD:   " + availabilityManager.findAvailabilityNotice(an.getId()).getWood().size()); 
	  System.err.println("XXXXXX AVAILABILITY NOTICE :   " + availabilityManager.findAvailabilityNotice(an.getId()).toString());
	  
  System.err.println("Flag after: " +availabilityNoticeStatus);
  }
  
  
} 
