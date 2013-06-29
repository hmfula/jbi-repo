package controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import entity.AvailabilityNotice;

import javax.faces.bean.ManagedBean;

import java.util.List;

/**
 * @author Harrison Mfula
 * Practice makes perfect       
 */
@ManagedBean(name = "controller", eager = true)
@RequestScoped
public class JbiController {


    @EJB
    private AvailabilityManager aManagerEJB;

    private AvailabilityNotice availabilityNotice;
   
    public AvailabilityNotice getAvailabilityNotice() {
		return availabilityNotice;
	}
   
    public JbiController(){
    	availabilityNotice = new AvailabilityNotice();
    }

	public void setAvailabilityNotice(AvailabilityNotice availabilityNotice) {
		availabilityNotice = aManagerEJB.findAvailabilityNotice(availabilityNotice.getId());
		if(availabilityNotice == null){
			aManagerEJB.createAvailabilityNotice(availabilityNotice);
			
		}
	}

	public AvailabilityNotice findAvailabilityNotice(long id ){
		return  aManagerEJB.findAvailabilityNotice(id);
	}
	
	@SuppressWarnings("unused")
	private List<AvailabilityNotice> availabilityNoticeList;


    public String createAvailabilityNotice() {
    		aManagerEJB.createAvailabilityNotice(availabilityNotice);
    		availabilityNoticeList = aManagerEJB.findAllAvailabilityNotices();
    	
        return "listAvailabilityNotices.xhtml";
    }


    public List<AvailabilityNotice> getAvailabilityNoticeList() {
        return aManagerEJB.findAllAvailabilityNotices();
    }

    public void setAuctionList(List<AvailabilityNotice> anList) {
        availabilityNoticeList = anList;
    }
    

    
    ///test code
	//Use case backing beans
    @ManagedProperty(value="#{message}")
    private Message messageBean;

    @ManagedProperty(value="#{woodDelegate}")
    private WoodDelegate woodBean;
     
    @ManagedProperty(value="#{param.pageId}")
 	private String pageId;
    
    public void setPageId(String pageId) {
 	this.pageId = pageId;
 	}
    
    //test variable
    private String message;

   
    
    public String showPage(){
 	if(pageId == null){
 	return "home";
 	}
 	if(pageId.equals("1")){
 	return "searchAvailability";
 	}else if(pageId.equals("2")){
 	return "createAvailability";
 	}else if(pageId.equals("3")){
 	return "viewBids";
 	}else if(pageId.equals("4")){
 	return "createBid";
 	}else if(pageId.equals("5")){
 	return "home";
 	}else{
 	return "home";
 	}
 	}
    
    //Needed by JSF - java bean style
    public void setMessageBean(Message message) {
       this.messageBean = message;
    }
     public void setWoodBean(WoodDelegate wood) {
       this.woodBean = wood;
    }
    
    //Business methods
   
 	private int searchId;
  
 	public int getSearchId() {
 		return searchId;
 	}
 	public void setSearchId(int searchId) {
 		this.searchId = searchId;
 	}
   
   
   
   
    
    
     public String getMessage() {
       if(messageBean != null){
          message = messageBean.getMessage();
       }       
       return message;
    }
    
    //for output you need getter method
    public String getWoodMessage() {
       if(woodBean != null){
          message = woodBean.getWoodMessage();
       }       
       return message;
    }
    
	
    
    
}