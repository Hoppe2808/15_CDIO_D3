package tempName.shared.dto;

import java.io.Serializable;

public class ProduktBatchDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	int pbId;
	int receptId;
	int status;
	

	public int getPbId() {
		return pbId;
	}

	public void setPbId(int pbId) {
		this.pbId = pbId;
	}

	// TODO Change to getPrescriptionId
	
	public int getReceptId() {
		return receptId;
	}

	// TODO Change to setPrescriptionId
	
	public void setReceptId(int receptId) {
		this.receptId = receptId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String toString(){
		return pbId + " " + receptId + " " + status;
	}
	
}
