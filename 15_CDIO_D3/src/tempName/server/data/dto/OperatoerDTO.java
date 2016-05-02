package tempName.server.data.dto;

/**
* Operatør Data Transfer Object (DTO)
*/

public class OperatoerDTO {
  /** Operatør id i området 1-99999999. Vælges af brugerne */
  int oprId; 
  /** Operatør navn min. 2 max. 20 karakterer */
  String oprNavn; 
  /** Operatør initialer min. 2 max. 3 karakterer */
  String ini; 
  /** Operatør cpr-nr 10 karakterer */
  String cpr; 
  /** Operatør password min. 7 max. 8 karakterer */
  String password;
  /** The operators admin status. TRUE = admin, FALSE = not dmin */
  boolean admin;
	
	
  public OperatoerDTO(int oprId, String oprNavn, String ini, String cpr, String password, boolean admin) {
    super();
    this.oprId = oprId;
    this.oprNavn = oprNavn;
    this.ini = ini;
    this.cpr = cpr;
    this.password = password;
    this.admin = admin;
  }


  public int getOprId() {
    return oprId;
  }


  public void setOprId(int oprId) {
    this.oprId = oprId;
  }


  public String getOprNavn() {
    return oprNavn;
  }


  public void setOprNavn(String oprNavn) {
    this.oprNavn = oprNavn;
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
  
  public boolean getAdminStatus(){
	  return admin;
  }
  
  public void setAdminStatus(boolean admin){
	  this.admin = admin;
  }
  
  public String toString(){
	return oprId + " " + oprNavn + " " + ini + " "+ cpr + " "+ password + " " + admin;
	  
  }
}