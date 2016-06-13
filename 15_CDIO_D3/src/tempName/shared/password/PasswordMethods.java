package tempName.shared.password;
import java.util.ArrayList;
import java.util.List;

import tempName.shared.dto.OperatoerDTO;
public class PasswordMethods {
	private PasswordData AD = new PasswordData();
	private ArrayList<String> character = AD.getCharacter();
	private boolean smallLetters;
	private boolean kapLetters;
	private boolean numbers;
	private boolean symbols;
	private int different;
	/**
	 * @param password Indsæt adgangskoden som skal kontrolleres
	 * @return true hvis den er på 6 tegn eller derover
	 */
	public boolean checkPassLength(String password){
		if(password.length()<7&&password.length()>8){
			//			System.out.print("Din adgangskode skal bestå af mindst 6 karakterer!");
			return false;
		}else if(password.length()>=6){
			return true;
		}else{
			System.out.println("Fejl i kontrolKodeLaengde");
			return false;
		}
	}
	/**
	 * @param password Indsæt adgangskoden som skal kontrolleres
	 * @return true hvis den består...
	 */
	public boolean checkPass(String password){
		AD.array();
		numbers=false;
		symbols= false;
		smallLetters=false;
		kapLetters=false;
		different = 0;
		for(int j=0; j<=9; j++){
			if(password.contains(character.get(j))){
				numbers = true;
				different++;
				break;
			}
		}
		for(int j=10; j<=35; j++){
			if(password.contains(character.get(j))){
				smallLetters = true;
				different++;
				break;
			}
		}
		for(int j=36; j<=61; j++){
			if(password.contains(character.get(j))){
				kapLetters = true;
				different++;
				break;
			}
		}
		for(int j=62; j<=68; j++){
			if(password.contains(character.get(j))){
				symbols = true;
				different++;
				break;
			}
		}
		if(!numbers||!smallLetters||!kapLetters||!symbols){
			if(different<3){
				return false;
			}
		}
		return true;
	}
	/**
	 * @param password1 Første kode
	 * @param password2 Anden kode
	 * @return true hvis koderne er ens METODEN ER TIL OPRETTELSE AF NY KODE
	 */
	public boolean samePass(String password1, String password2) {
		if(password1.equals(password2)) return true;
		else return false;
	}
	/**
	 * @return true hvis koden stemmer med brugerens adgangskode
	 */
	public boolean correctUserPassword(String password, List<OperatoerDTO> list){
		for (int i = 0 ; i < list.size(); i++){
			if (password.equals(list.get(i).getPassword())){
				return true;		
			}		
		}
		return false;
	}
}