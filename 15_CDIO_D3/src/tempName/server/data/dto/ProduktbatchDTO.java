package tempName.server.data.dto;

public class ProduktbatchDTO  {
	int pbId;
	int recpetId;
	int status;
	int oprId;
	int rbId;
	double tara;
	double netto;
	
	public ProduktbatchDTO (int pbId,int recpetId, int status, int oprId, int rbId,double tara, double netto){
		this.pbId = pbId;
		this.recpetId = recpetId;
		this.status = status;
		this.oprId = oprId;
		this.rbId = rbId;
		this.tara = tara;
		this.netto = netto;
	}

	public int getPbId() {
		return pbId;
	}

	public void setPbId(int pbId) {
		this.pbId = pbId;
	}

	public int getRecpetId() {
		return recpetId;
	}

	public void setRecpetId(int recpetId) {
		this.recpetId = recpetId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOprId() {
		return oprId;
	}

	public void setOprId(int oprId) {
		this.oprId = oprId;
	}

	public int getRbId() {
		return rbId;
	}

	public void setRbId(int rbId) {
		this.rbId = rbId;
	}

	public double getTara() {
		return tara;
	}

	public void setTara(double tara) {
		this.tara = tara;
	}

	public double getNetto() {
		return netto;
	}

	public void setNetto(double netto) {
		this.netto = netto;
	}

	public String toString(){
		return pbId + " " + recpetId + " " + status + " " + oprId + " "+ rbId + ""+ tara+" "+ netto;
	}
}
