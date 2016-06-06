package tempName.server.data.dto;

import java.io.Serializable;

public class ReceptDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int receptId;
	private String receptNavn;
	
	public int receptId() {
		return receptId;
	}
	public void receptId(int receptId) {
		this.receptId = receptId;
	}
	public String receptNavn() {
		return receptNavn;
	}
	public void receptNavn(String recpetNavn) {
		this.receptNavn = recpetNavn;
	}
}
