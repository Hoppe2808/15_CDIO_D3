package tempName.shared.dto;

public class ProduktBatchKomponentDTO {

		int pbId, rbId, oprId;
		double tara, netto;

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
