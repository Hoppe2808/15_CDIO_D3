package cdio1;

import java.util.ArrayList;

public class Operatoer implements IOperatoer{

	private ArrayList<op> opArray = new ArrayList<op>();

	public Operatoer(){
		this.addOp(10, "Admin", "Abc02324", "", 2);
	}

	/**
	 * Tilføjer en ny operatoer
	 */
	public void addOp(int oprId, String oprNavn, String adgangskode, String cpr, boolean admin){
		opArray.add(new op(oprId, oprNavn, adgangskode, cpr, admin));
	}

	/**
	 * Soetter id'et paa et operator objekt
	 * @param index : Placeringen i Array Listen metoden bliver kaldt
	 * @param oprId Den id man vil bruge
	 */
	public void setOprId(int index, int oprId){
		(opArray.get(index)).setOprId(oprId);
	}

	/**
	 * @param index : the place in the array list the method will be called
	 * @return The ID of the operator
	 */
	public int getOprId(int index){
		return (opArray.get(index)).getOprId();
	}

	/**
	 * @param index : the place in the array list the method will be called
	 * @return The Name of the operator
	 */
	public String getOprNavn(int index){
		return (opArray.get(index)).getOprNavn();
	}

	/**
	 * Soetter navnet paa et operator objekt
	 * @param index : Placeringen i Array Listen metoden bliver kaldt
	 * @param oprNavn Navnet der skal bruges
	 */
	public void setOprNavn(int index, String oprNavn) {
		(opArray.get(index)).setOprNavn(oprNavn);
	}

	/**
	 * @param index : the place in the array list the method will be called
	 * @return The Ini (?) of the operator
	 */
	public String getAdgangskode(int index){		
		return (opArray.get(index)).getIni();
	}

	/**
	 * Soetter adgangskode paa et givent operator objekt
	 * @param index  : Placeringen i Array Listen metoden bliver kaldt
	 * @param pwd : adgangskode
	 */
	public void setAdgangskode(int index, String pwd){
		(opArray.get(index)).setIni(pwd);
	}

	/**
	 * @param index : the place in the array list the method will be called
	 * @return The CPR.nr of the operator
	 */
	public String getCpr(int index){
		return (opArray.get(index)).getCpr();
	}
	
	/**
	 * Soetter cpr-nummeret for en given operatoer
	 * @param index : Placeringen i Array Listen metoden bliver kaldt
	 * @param value : Cpr-nummer
	 */

	public void setCpr(int index, String value){
		(opArray.get(index)).setCpr(value);
	}

	/**
	 * For at faa admin status af det enkelte operatoer objekt
	 * @param index : Placeringen i Array Listen metoden bliver kaldt
	 * @return Adminstatus
	 */
	
	public int getAdminStatus(int index){
		return (opArray.get(index)).getAdmin();
	}

	/**
	 * For at faa maengden af operatoer objekter der er i arraylisten
	 * @return Stoerrelsen paa Arraylisten
	 */
	public int getOperatoerArrayLaengde(){
		return opArray.size();
	}
	
	/**
	 * Fjerner en operatoer fra arraylisten
	 * @param index : Placeringen i Array Listen metoden bliver kaldt
	 */
	
	public void fjernOperatoer(int index){
		opArray.remove(index);
	}


	private class op{

		private int oprId;
		private boolean admin;
		private String oprNavn;
		private String adgangskode;
		private String cpr;

		private op(int oprId, String oprNavn, String adgangskode, String cpr, boolean admin){

			this.cpr = cpr;
			this.adgangskode = adgangskode;
			this.oprNavn = oprNavn;
			this.oprId = oprId;
			this.admin = admin;
		}

		private int getOprId() {
			return oprId;
		}
		
		private void setOprId(int oprId) {
			this.oprId = oprId;
		}
		
		private String getOprNavn() {
			return oprNavn;
		}
		
		private void setOprNavn(String oprNavn) {
			this.oprNavn = oprNavn;
		}
		
		private String getIni() {
			return adgangskode;
		}
		
		private void setIni(String pwd) {
			this.adgangskode = pwd;
		}
		
		private String getCpr() {
			return cpr;
		}
		
		private int getAdmin(){
			return admin;
		}
		
		private void setCpr(String value){
			this.cpr = value;
		}
	}
}
