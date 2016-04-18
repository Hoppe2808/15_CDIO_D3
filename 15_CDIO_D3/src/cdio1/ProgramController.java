package cdio1;

import java.util.Scanner;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;

import graenseflade.TUI;
import tempName.client.FException;
import tempName.client.Funktionalitet;
import tempName.client.Operatoer;
import tempName.client.PasswordMethods;

public class ProgramController {

	//ALT MED TUI SKAL ÆNDRES TIL AT HÆNGE SAMMEN MED GWT
	
	private Operatoer o = new Operatoer();
	private Funktionalitet f = new Funktionalitet(o);
	private PasswordMethods ak = new PasswordMethods(o);
	TUI tui;

	int valg;
	int ID;
	String input;

	boolean operatoerMenu = true;
	boolean sysAdminMenu = true;

	Scanner sc = new Scanner(System.in);

	public ProgramController(TUI tui) {
		this.tui = tui;
	}

	public void valg(int valg){
		this.valg = valg;
		Scanner sc = new Scanner(System.in);
		//Operatoer login
		if (valg == 1){

			System.out.println("Indtast ID: ");
			try{
				ID = sc.nextInt();
			}catch(InputMismatchException e){
				System.out.println("Du skal indtaste et gyldigt ID (ID'er består af tal mellem 11-99)");
				try {
					tui.hovedMenu();
				} catch (IOException e1) {
					System.out.println("Hov, der skete en fejl - venligst kontakt devs for support.");
				}
			}

			System.out.println("Indtast Adgangskode: ");
			String kode = sc.next();
			if(f.tjekLogin(ID, kode, 1)){
				operatoerMenu(ID);
			}
			else{
				System.out.println("Forkert login");
			}


			//sysAdminLogin
		} else if (valg == 2){

			String kode;
			System.out.println("Indtast ID: ");
			try{
				ID = sc.nextInt();
			}catch(InputMismatchException e){
				System.out.println("Du skal indtaste et gyldigt ID (sysAdmin ID'er beståer af tal mellem 1-10");
				try {
					tui.hovedMenu();
				} catch (IOException e1) {
					System.out.println("Hov, der skete en fejl - venligst kontakt devs for support.");
				}
			}

			System.out.println("Indtast Adgangskode: ");
			kode = sc.next();

			if(f.tjekLogin(ID, kode, 2)){
				sysAdminMenu();
			}else{
				System.out.println("Forkert login");
			}
		}
		else{
			System.out.println("Du skal vælge enten 1 eller 2: ");
			try {
				tui.hovedMenu();
			} catch (IOException e) {
				System.out.println("En ukendt fejl er opstået - venligst kontakt devs for support.");
			}
		}
	}

	public void sysAdminMenu() {
		sysAdminMenu = true;

		System.out.println("");
		System.out.println("Velkommen sysAdmin");
		System.out.println("----------------------------------------");		

		//I en while løkke, hvilket betyder at sysAdmin ikke kan komme ud af sin menu, før han logger ud
		while (sysAdminMenu){
			System.out.println("");

			Scanner sc = new Scanner(System.in);
			System.out.println("Tast 1 for at oprette en ny operatør");
			System.out.println("Tast 2 for at slette en operatør");
			System.out.println("Tast 3 for at ændre i en operatør");
			System.out.println("Tast 4 for at se en operatør");
			System.out.println("Tast 5 for at se målinger lavet, med nettovægtsberegneren");
			System.out.println("Tast 6 for at logge ud");
			try{
				valg = sc.nextInt();
			}catch(InputMismatchException ime){
				System.out.println("Du skal indtaste et gyldigt valg.");
				sysAdminMenu();
			}
			try{
				if (valg == 1){
					System.out.println("Indtast navn: ");
					String navn = sc.next();
					while (navn.length() < 2 || navn.length() > 20){
					System.out.println("Navnet skal være mellem 2 og 20 karakterer");
						navn = sc.next();
					}
					System.out.println("Indtast cpr-nummer: ");
					String cpr = sc.next();
					System.out.println("Er det en administrator? (tast 1 hvis nej, 2 hvis ja)");
					int admin = sc.nextInt();
					f.createOperatoer(navn, cpr, admin);
					System.out.println("En ny operator er nu blevet oprettet.");

				}

				else if (valg == 2){
					System.out.println("Indtast ID for den operatør du ønsker at slette"
							+ "(Advarsel - det er ikke muligt at få operatøren tilbage: ");
					ID = sc.nextInt();
					try {
						f.deleteOperatoer(ID);
						System.out.println("Operatoer "+ID+" er nu blevet slettet!");
					} catch (FException e) {
						System.out.println(e.myprint());
					}
				}

				else if (valg == 3){
					System.out.println("Tast 1 for at ændre navn på en operatør");
					System.out.println("Tast 2 for at ændre cpr-nummer på en operatør");
					System.out.println("Tast 3 for at ændre i adgangskoden på en operatør");
					int m3Valg = sc.nextInt();
					String m3ValgString;

					if(m3Valg == 1){
						System.out.println("Intast operatørens ID");
						m3Valg = sc.nextInt();
						System.out.println("Intast nyt navn til operatør");
						m3ValgString = sc.next();
						while (m3ValgString.length() < 2 || m3ValgString.length() > 20){
							System.out.println("Navnet skal være mellem 2 og 20 karakterer");
							m3ValgString = sc.next();
						}
						try {
							f.updateOperatoer(m3Valg, 1, m3ValgString);
							System.out.println("Operatørens navn er nu ændret");
						} catch (FException e) {
							System.out.println(e.myprint());
						}
					} 

					else if (m3Valg == 2){
						System.out.println("Intast operatørens ID");
						m3Valg = sc.nextInt();
						System.out.println("Intast ny cpr-nummer til operatør");
						m3ValgString = sc.next();
						try {
							f.updateOperatoer(m3Valg, 2, m3ValgString);
							System.out.println("Operatørens cpr-nummer er nu ændret");
						} catch (FException e) {
							System.out.println(e.myprint());
						}
					} 

					else if (m3Valg == 3){
						System.out.println("Intast operatørens ID");
						m3Valg = sc.nextInt();
						System.out.println("Intast ny adgangskode til operatør");
						m3ValgString = ak.checkPass(m3Valg, sc);
						try {
							f.updateOperatoer(m3Valg, 3, m3ValgString);
							System.out.println("Operatørens navn er nu ændret");
						} catch (FException e) {
							System.out.println(e.myprint());
						}
					}
				}

				else if (valg == 4){
					System.out.println("Indtast ID for den operatør du ønsker at se: ");
					ID = sc.nextInt();
					try {
						System.out.println(Arrays.toString(f.getOperatoer(ID)));
					} catch (FException e) {
						System.out.println(e.myprint());
					}
				}

				else if (valg == 5){
					f.showMaalinger();
				}
				else if (valg == 6){
					sysAdminMenu = false;
				}else{
					System.out.println("Ugyldigt valg, prøv igen");
				}
			}catch(InputMismatchException e){
				System.out.println("Du skal indtaste et gyldigt input.");
				sysAdminMenu();
			}
		}

	}

	public void operatoerMenu(int ID) {
		operatoerMenu = true;

		System.out.println("");
		System.out.println("Velkommen operatør");
		System.out.println("------------------------------------------");

		//En while løkke der gør at operatøren ikke kan komme tilbage før han logger ud
		while (operatoerMenu){

			Scanner sc = new Scanner(System.in);
			System.out.println("Tast 1 for at bruge nettovægtberegneren");
			System.out.println("Tast 2 for at skifte adgangskode");
			System.out.println("Tast 3 for at logge ud");
			try{
				valg = sc.nextInt();
			}catch(InputMismatchException e){	
				System.out.println("DU skal indtaste et gyldig valg: ");
				operatoerMenu(ID);	
			}
			try{
				if (valg == 1){
					System.out.println("Indtast tarravægt (kg): ");

					double tarra = sc.nextDouble();
					System.out.println("Indtast bruttovægt (kg): ");
					double brutto = sc.nextDouble();
					double netto = f.nettoVaegt(ID, brutto, tarra);
					System.out.println("Nettovægt: "+ netto);
				}

				else if (valg == 2){
					System.out.println("Indtast din gamle adganskode: ");

					String kode = sc.next();
					String kodex;

					if(ak.correctUserPassword(ID, kode)){
						System.out.println("Indtast ny adgangskode her: ");
						kode = ak.checkPass(ID, sc);
						System.out.println("Indtast ny adgangskode igen: ");
						kodex = sc.next();
						if(!ak.samePass(kode, kodex)){
							System.out.println("Kodeordene er ikke ens!");
						}

						else if(ak.samePass(kode, kodex)){
							f.setKode(ID, kode);
						}
					}else{
						System.out.println("Forkert adganskode!");
						System.out.println("");
					}
				}
				else if (valg == 3){
					operatoerMenu = false;
				}
				else{
					System.out.println("Ugyldigt valg, proev igen");
					System.out.println("");
				}
			}catch(InputMismatchException e){
				System.out.println("Ugyldigt input");
				System.out.println("");
				operatoerMenu(ID);
			}
		}
	}
}
