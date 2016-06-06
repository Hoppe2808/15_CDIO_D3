package tempName.server.data.dto;

import java.io.Serializable;

public class ReceptDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int rec_Id;
	private String rec_navn;
	
	public int getRec_Id() {
		return rec_Id;
	}
	public void setRec_Id(int rec_Id) {
		this.rec_Id = rec_Id;
	}
	public String getRec_navn() {
		return rec_navn;
	}
	public void setRec_navn(String rec_navn) {
		this.rec_navn = rec_navn;
	}
}
