package tempName.server.data.dto;

import java.io.Serializable;

public class RaavareBatchDTO implements Serializable{
	
	int rbId;
	int raavareId;
	double maengde;

	public int getRbId() {
		return rbId;
	}


	public void setRbId(int rbId) {
		this.rbId = rbId;
	}


	public int getRaavareId() {
		return raavareId;
	}


	public void setRaavareId(int raavareId) {
		this.raavareId = raavareId;
	}


	public double getMaengde() {
		return maengde;
	}


	public void setMaengde(double maengde) {
		this.maengde = maengde;
	}
	public String toString(){
		return rbId + " " + raavareId + " " + maengde;
	}

}
