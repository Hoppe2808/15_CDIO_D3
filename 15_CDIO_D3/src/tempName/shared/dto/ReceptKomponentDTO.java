package tempName.shared.dto;

import java.io.Serializable;

public class ReceptKomponentDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int prescription_Id, commodity_Id;
	double nom_netto, tolerance;

	// TODO Change to getPrescription_Id

	public int getRecept_Id() {
		return prescription_Id;
	}

	// TODO Change to setPrescription_Id

	public void setRecept_Id(int prescription_Id) {
		this.prescription_Id = prescription_Id;
	}

	// TODO Change to getCommodity_Id

	public int getRaavare_Id() {
		return commodity_Id;
	}

	// TODO Change to setCommodity_Id

	public void setRaavare_Id(int commodity_Id) {
		this.commodity_Id = commodity_Id;
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
		return prescription_Id + " " +  commodity_Id  + " " +nom_netto+" "+ tolerance;
	}
}