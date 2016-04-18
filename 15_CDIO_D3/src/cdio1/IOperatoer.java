package cdio1;

public interface IOperatoer {

	void addOp(int oprId, String oprNavn, String adgangskode, String cpr, boolean admin);
	void setOprId(int index, int oprId);
	void setOprNavn(int index, String oprNavn);
	void setCpr(int index, String value);
	void setAdgangskode(int index, String ini);
	void fjernOperatoer(int index);

	int getOprId(int index);
	int getAdminStatus(int index);
	int getOperatoerArrayLaengde();
	
	String getOprNavn(int index);
	String getAdgangskode(int index);
	String getCpr(int index);
}