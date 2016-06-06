package tempName.server.data.dto;


public class RaavareBatchDTO {
	
	int rbId;
	int raavareId;
	double maengde;
	
	
	public RaavareBatchDTO(int rbId, int raavareId, double maengde){
		this.raavareId = rbId;
		this.rbId = rbId;
		this.maengde = maengde;		
	}


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
