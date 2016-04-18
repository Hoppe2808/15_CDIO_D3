package tempName.client;

public interface IFunktionalitet {

	/**
	 * Tjekker hvorvidt operatørens login passer
	 * @param ID Operatoerens indtastede ID
	 * @param pass Operatoerens indtastede password
	 * @param admin Admin status (1 = nej, 2 = ja)
	 * @return True, hvis login er rigtigt
	 */
	boolean tjekLogin(int ID, String pass, boolean admin);

	/**
	 * Metode der udregner nettovaegt ud fra brutto og taravaegt og gemmer det i en arraylist
	 * @param ID Operatoerens ID
	 * @param brutto 
	 * @param tarra
	 * @return Nettovaegt
	 */
	double nettoVaegt(int ID, double brutto, double tarra);

	/**
	 * Saetter en operatoers adgangskode
	 * @param ID Operatoerens ID
	 * @param kode Operatoerens nye kode
	 */
	void setKode(int ID, String kode);

	/**
	 * Opretter en ny operatoer
	 * @param navn Den nye operatoers navn
	 * @param cpr Den nye operatoers CPR-nummer
	 * @param admin Om han er admin (1 = nej, 2 = ja)
	 */
	void createOperatoer(String navn, String cpr, boolean admin);

	/**
	 * Sletter en operatoer
	 * @param iD Operatoerens ID
	 * @throws FException Hvis operatoeren ikke findes
	 */
	void deleteOperatoer(int iD) throws FException;

	/**
	 * Henter en operatoer fra arraylisten
	 * @param iD Operatoerens ID
	 * @return En operatoers informationer
	 * @throws FException Hvis operatoeren ikke findes
	 */
	String[] getOperatoer(int iD) throws FException;

	/**
	 * Viser alle maalinger foretaget af operatoere
	 */
	void showMaalinger();

	/**
	 * Opdaterer en operatoer
	 * @param iD Operatoerens ID
	 * @param choice Hvad der ønskes at laves på operatoeren
	 * @param value Det, som valget skal ændres til
	 * @throws FException hvis operatoeren ikke findes
	 */
	void updateOperatoer(int iD, int choice, String value) throws FException;
}
