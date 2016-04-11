package cdio1;

import java.util.ArrayList;

public class Vaegt implements IVaegt{

	private ArrayList<v> vArray = new ArrayList<v>();

	/**
	 * Tilfoejer en ny maaling lavet af en operatør
	 * @param maalingsID Et unikt ID for maalingen 
	 * @param vaegt Den udregnede nettovaegt
	 * @param Id Operatoerens ID
	 */
	public void addMaaling(int maalingsID, double vaegt, int Id){
		vArray.add(new v(maalingsID, vaegt, Id));
	}

	/**
	 * Henter et maalingsID fra arraylisten
	 * @param index 
	 * @return
	 */
	public double getMaalingsID(int index){
		return (vArray.get(index)).getMaalingsID();
	}

	/**
	 * Returnere en vaegt paa et et givent sted i arrayet
	 * @param index : Placeringen i Array Listen metoden bliver kaldt
	 * @return Vaegten af maalingen
	 */
	public double getVaegt(int index){
		return (vArray.get(index)).getVaerdi();
	}
	
	/**
	 * Returnere den Id der er lagt til maalingen
	 * @param index : Placeringen i Array Listen metoden bliver kaldt
	 * @return iD for maalingen
	 */
	public int getId(int index){
		return (vArray.get(index)).getId();
	}
	
	/**
	 * Finder laengden af arrayet
	 * @return en intiger med laengden af arrayet
	 */
	public int getVaegtArratLaengde(){
		return vArray.size();
	}

	public String toString(int index) {
		return "Måling: "+ getMaalingsID(index) + " Operatør: " + getId(index) + " Vægt: " + getVaegt(index);
	}


	private class v{

		private double vaegt;
		private int Id;
		private int maalingsID;

		private v(int maalingsID, double vaegt, int Id){

			this.vaegt = vaegt;
			this.Id = Id;
			this.maalingsID = maalingsID;

		}

		private double getMaalingsID() {
			return maalingsID;
		}

		private double getVaerdi(){
			return vaegt;
		}
		private int getId(){
			return Id;
		}
	}
}