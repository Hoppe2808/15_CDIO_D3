package tempName.shared.dto;

import java.io.Serializable;

public class ReceptDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int receptId;
	private String receptNavn;
	
	public int getReceptId() {
		return receptId;
	}
	
	public void setReceptId(int receptId) {
		this.receptId = receptId;
	}
	
	public String getReceptName() {
		return receptNavn;
	}
	
	public void setReceptName(String receptNavn) {
		this.receptNavn = receptNavn;
	}
}
