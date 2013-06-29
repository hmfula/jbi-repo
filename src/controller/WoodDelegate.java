package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "woodDelegate", eager = true)
@RequestScoped
public class WoodDelegate{
	
	private String message = "Hello wood delegate!";
	
	public String getWoodMessage() {
        return message;
    }
    public void setWoodMessage(String message) {
        this.message = message;
    }
}