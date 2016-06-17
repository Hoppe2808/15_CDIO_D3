package tempName.shared.dto;

import java.io.Serializable;

/**
 * Operator Data Transfer Object (DTO)
 */

public class OperatoerDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Operatør id i området 1-99999999. Vælges af brugerne */
	int oprId; 
	/** Operatør navn min. 2 max. 20 karakterer */
	String oprName; 
	/** Operatør initialer min. 2 max. 3 karakterer */
	String ini; 
	/** Operatør cpr-nr 10 karakterer */
	String cpr; 
	/** Operatør password min. 7 max. 8 karakterer */
	String password;
	/** The operators admin status. 1 = Admin, 2 = Operatør, 3 = Farmaceut, 4 = Værksfører */
	int admin;

	public int getOprId() {
		return oprId;
	}

	public void setOprId(int oprId) {
		this.oprId = oprId;
	}

	// TODO Change to getOprName

	public String getOprNavn() {
		return oprName;
	}

	// TODO Change to setOprName
	
	public void setOprNavn(String oprName) {
		this.oprName = oprName;
	}

	public String getIni() {
		return ini;
	}

	public void setIni(String ini) {
		this.ini = ini;
	}

	public String getCpr() {
		return cpr;
	}

	public void setCpr(String cpr) {
		this.cpr = cpr;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	} 

	public int getAdminStatus(){
		return admin;
	}

	public void setAdminStatus(int admin){
		this.admin = admin;
	}

	public String toString(){
		return oprId + " " + oprName + " " + ini + " "+ cpr + " "+ password + " " + admin;

	}
}
