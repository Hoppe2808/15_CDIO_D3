package tempName.client.password;

import java.util.ArrayList;

public class PasswordData implements IPassword{

	private ArrayList<String> characters = new ArrayList<String>();

	private String password = "";
	private boolean smallLetters;
	private boolean kapLetters;
	private boolean number;
	private boolean symbols;
	private int random;
	private int different;

	public ArrayList<String> getCharacter(){
		array();
		return characters;
	}
	public void array(){
		for (int i = 0; i<=9 ; i++){
			characters.add(Integer.toString(i));
		}
		for (char letter = 'a'; letter <= 'z'; letter++){
			characters.add(Character.toString(letter));
		}
		for (char letter = 'A'; letter <= 'Z'; letter++){
			characters.add(Character.toString(letter));
		}
		characters.add(".");
		characters.add("-");
		characters.add("_");
		characters.add("+");
		characters.add("!");
		characters.add("?");
		characters.add("=");
	}
	//tal starter ved index 		0-9
	//smaa bogstaver ved index 		10-35
	//store bogstaver ved index 	36-61
	//tegn ved index 				62-68


	/**
	 * 
	 * @return Genererer et tilfaeldigt kodeord med DTU's krav for et kodeord.
	 * @param Indsaet hvor langt du oensker kodeordet skal vaere
	 * 
	 */
	public String getNewKode(int passLength){

		password = "";

		number=false;
		symbols= false;
		smallLetters=false;
		kapLetters=false;
		different=0;

		//Opretter arrayet
		array();

		while(password.length()<passLength){
			//2. (se 1. laengere nede) Naar de alle er blacklisted, bliver de alle whitelisted igen.
			if(smallLetters && kapLetters && number && symbols||different>=3){
				smallLetters=false;
				kapLetters=false;
				number=false;
				symbols=false;
			}
			//Generer et tilfaeldigt tal som bruges til arrayet.
			random = (int) (Math.random()*69);

			//Indsaetter vaerdien i koden hvis den er whitelisted.
			if(random<=9&&!number){
				password = password + characters.get(random);
				different++;
			}
			if(random>=10 && random<=35&&!smallLetters){
				password = password + characters.get(random);
				different++;
			}
			if(random>=36 && random<=61&&!kapLetters){
				password = password + characters.get(random);
				different++;
			}
			if(random>=62 && random<=68&&!symbols){
				password = password + characters.get(random);
				different++;
			}

			//1. For at der er 1 af hver 4 mulige tegn bliver de blacklisted her 1 af gangen.
			if(random<=9)number=true;
			if(random>=10 && random<=35)smallLetters=true;
			if(random>=36 && random<=61)kapLetters=true;
			if(random>=62 && random<=68)symbols=true;
		}
		return password;
	}
}