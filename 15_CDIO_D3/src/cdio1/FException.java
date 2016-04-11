package cdio1;

public class FException extends Exception{


	private static final long serialVersionUID = 1L;

	int ID;
	FException(int ID){
		this.ID = ID;
	}
	
	public String myprint(){
		return "Den valgte operatør, med ID: " + ID + " eksisterer ikke.";
	}
}
