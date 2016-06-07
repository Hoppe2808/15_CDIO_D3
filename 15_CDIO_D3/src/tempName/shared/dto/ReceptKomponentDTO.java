package tempName.shared.dto;

public class ReceptKomponentDTO {

	int recept_Id, raavare_Id;
	double nom_netto, tolerance;

	public ReceptKomponentDTO (int recept_Id, int raavare_Id,double nom_netto, double tolerance){
		this.recept_Id = recept_Id;
		this.raavare_Id = raavare_Id;
		this.nom_netto=nom_netto;
		this.tolerance=tolerance;
	}
	public int getRecept_Id() {
		return recept_Id;
	}
	public void setRecept_Id(int recept_Id) {
		this.recept_Id = recept_Id;
	}
	public int getRaavare_Id() {
		return raavare_Id;
	}
	public void setRaavare_Id(int raavare_Id) {
		this.raavare_Id = raavare_Id;
	}
	public double getNom_netto() {
		return nom_netto;
	}
	public void setNom_netto(double nom_netto) {
		this.nom_netto = nom_netto;
	}
	public double getTolerance() {
		return tolerance;
	}
	public void setTolerance(double tolerance) {
		this.tolerance = tolerance;
	}
	public String toString(){
		return recept_Id + " " +  raavare_Id  + " " +nom_netto+" "+ tolerance;
	}

}
