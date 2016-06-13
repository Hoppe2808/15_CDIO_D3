package tempName.shared.password;

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
}