package tempName.shared.dto;

import java.io.Serializable;

/**
 * Data transfer object for weight measurements
 */

public class WeightDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int wID;
	private int opID;
	private double ms;

	public int getopID(){
		return opID;
	}

	public void setopID(int opID){
		this.opID = opID;
	}

	public double getMS(){
		return ms;
	}

	public void setMS(double ms){
		this.ms = ms;
	}
	
	public void setWID(int wID){
		this.wID = wID;
	}
	
	public int getWID(){
		return wID;
	}

	public String toString(){
		return wID + " " + ms + " " + opID;
	}
}