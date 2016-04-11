package cdio1;

import java.util.ArrayList;
import java.util.Scanner;

public class AdgangskodeMetoder {

	private AdgangskodeData AD = new AdgangskodeData();
	private Operatoer o;

	private ArrayList<String> karakterer = AD.getKarakterer();
	private boolean smaaBogstaver;
	private boolean storeBogstaver;
	private boolean tal;
	private boolean tegn;
	private int forskelige;

	public AdgangskodeMetoder(Operatoer o) {
		this.o = o;
	}
	
	/**
	 * Metode der tester hvorvidt en bruger nye password stemmer overens
	 * med de satte regler
	 * @param id Operatørens ID
	 * @param sc scanner objekt der sendes videre
	 * @return returnere koden, når den er gået igennem kontroltjekket
	 */
	public String kontrollerKode(int id, Scanner sc){
		String kode;
		do{
			kode = sc.next();
			if(!this.kontrolKodeLaengde(kode)){
				System.out.print("Din adgangskode skal bestå af mindst 6 karakterer!");
			}
			if(!this.kontrolKode(kode)){
				System.out.println("Din adgangskode skal indholde mindst 3 følgende: Tal, Specialtegn, Stort Bogstav, Lille Bogstav");
			}
		}while(!this.kontrolKode(kode));
		return kode;
	}
	
	/**
	 * @param adgangskode Indsæt adgangskoden som skal kontrolleres
	 * @return true hvis den er på 6 tegn eller derover
	 */
	public boolean kontrolKodeLaengde(String adgangskode){
		if(adgangskode.length()<6){
			//			System.out.print("Din adgangskode skal bestå af mindst 6 karakterer!");
			return false;
		}else if(adgangskode.length()>=6){
			return true;
		}else{
			System.out.println("Fejl i kontrolKodeLaengde");
			return false;
		}
	}

	/**
	 * @param adgangskode Indsæt adgangskoden som skal kontrolleres
	 * @return true hvis den består...
	 */
	public boolean kontrolKode(String adgangskode){

		AD.array();

		tal=false;
		tegn= false;
		smaaBogstaver=false;
		storeBogstaver=false;
		forskelige = 0;

		for(int j=0; j<=9; j++){
			if(adgangskode.contains(karakterer.get(j))){
				tal = true;
				forskelige++;
				break;
			}
		}
		
		for(int j=10; j<=35; j++){
			if(adgangskode.contains(karakterer.get(j))){
				smaaBogstaver = true;
				forskelige++;
				break;
			}
		}
		
		for(int j=36; j<=61; j++){
			if(adgangskode.contains(karakterer.get(j))){
				storeBogstaver = true;
				forskelige++;
				break;
			}
		}
		
		for(int j=62; j<=68; j++){
			if(adgangskode.contains(karakterer.get(j))){
				tegn = true;
				forskelige++;
				break;
			}
		}

		if(!tal||!smaaBogstaver||!storeBogstaver||!tegn){
			if(forskelige<3){
				return false;
			}
		}
		return true;
	}

	/**
	 * @param kode Første kode
	 * @param kodex Anden kode
	 * @return true hvis koderne er ens METODEN ER TIL OPRETTELSE AF NY KODE
	 */
	public boolean ensKode(String kode, String kodex) {

		if(kode.equals(kodex)) return true;
		else return false;
	}
	
	/**
	 * 
	 * @return true hvis koden stemmer med brugerens adgangskode
	 */
	public boolean korrektBrugerKode(int iD, String kode){

		int index = -1;	
		for (int i = 0 ; i < o.getOperatoerArrayLaengde() ; i++){		
			if (iD == o.getOprId(i)){			
				index = i;			
			}		
		}

		if(o.getAdgangskode(index).equals(kode)){
			return true;
		}else{
			return false;
		}
	}
}
