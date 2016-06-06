package tempName.server.data.dto;

import java.io.Serializable;

public class RaavareDTO implements Serializable{
	private int rID;
	private String rName;
	private String deliverer;
	
	public int getrID() {
		return rID;
	}
	public void setrID(int rID) {
		this.rID = rID;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getDeliverer() {
		return deliverer;
	}
	public void setDeliverer(String deliverer) {
		this.deliverer = deliverer;
	}

	public String toString(){
		return rID + " " + rName + " " + deliverer;
	}

}
