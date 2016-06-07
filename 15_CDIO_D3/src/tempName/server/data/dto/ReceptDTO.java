package tempName.server.data.dto;

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
	public String getReceptNavn() {
		return receptNavn;
	}
	public void setReceptNavn(String recpetNavn) {
		this.receptNavn = recpetNavn;
	}
}
