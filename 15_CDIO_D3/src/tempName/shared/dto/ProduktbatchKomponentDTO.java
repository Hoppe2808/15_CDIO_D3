package tempName.shared.dto;

public class ProduktbatchKomponentDTO {

		int pbId, rbId, oprId;
		double tara, netto;


		public ProduktbatchKomponentDTO(int pbId, int rbId, int oprId, double tara, double netto){
			this.pbId = pbId;
			this.rbId = rbId;
			this.oprId=oprId;
			this.tara= tara;
			this.netto=netto;		
		}

		public int getPbId() {
			return pbId;
		}


		public void setPbId(int pbId) {
			this.pbId = pbId;
		}


		public int getRbId() {
			return rbId;
		}


		public void setRbId(int rbId) {
			this.rbId = rbId;
		}


		public int getOprId() {
			return oprId;
		}


		public void setOprId(int oprId) {
			this.oprId = oprId;
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
			return pbId + " " +  rbId  + " " +tara+" "+ netto+ " " + oprId;
		}
	}
