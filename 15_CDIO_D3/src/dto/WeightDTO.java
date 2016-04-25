package dto;

/**
 * Data transfer object for weight measurements
 */

public class WeightDTO {

	int opID;
	double ms;
	
	/**
	 * The object used to transfer the weight details to the database
	 * @param opID The operator behind the measurement
	 * @param ms the weight of the measurement
	 */
	public WeightDTO (int opID, double ms){
		super();
		this.opID = opID;
		this.ms = ms;		
	}
	
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
}