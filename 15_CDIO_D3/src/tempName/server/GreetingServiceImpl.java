package tempName.server;

import tempName.client.service.GreetingService;
import tempName.server.data.daoimpl.MYSQLOperatoerDAO;
import tempName.server.data.daoimpl.MYSQLWeightDAO;
import tempName.server.data.daointerface.DALException;
import tempName.server.data.dto.OperatoerDTO;
import tempName.server.data.dto.WeightDTO;
import tempName.server.data.password.PasswordMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {
	
	private MYSQLWeightDAO weightDAO = new MYSQLWeightDAO();
	private MYSQLOperatoerDAO operatoerDAO = new MYSQLOperatoerDAO();
	private PasswordMethods passMeth = new PasswordMethods();
	
	public boolean checkLogin(int id, String pass){
		boolean check = passMeth.correctUserPassword(id, pass);
		System.out.println("HEjejeje");
		return check;
	}

	public ArrayList<HashMap> getOperators(){
		List<OperatoerDTO> rawList = new ArrayList<OperatoerDTO>();
		ArrayList<HashMap> finalData = new ArrayList<HashMap>();
		try {
			rawList = this.operatoerDAO.getOperatoerList();
		} catch (DALException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < rawList.size(); i++){
			finalData.add(new HashMap());
			finalData.get(i).put("ID", rawList.get(i).getOprId());
			finalData.get(i).put("Username", rawList.get(i).getOprNavn());
			finalData.get(i).put("Password", rawList.get(i).getPassword());
			finalData.get(i).put("cpr", rawList.get(i).getCpr());
			finalData.get(i).put("Initials", rawList.get(i).getIni());
			finalData.get(i).put("AdminStatus", rawList.get(i).getAdminStatus());
		}
		return finalData;
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList> getMeasurements(){
		List<WeightDTO> rawList = new ArrayList<WeightDTO>();
		ArrayList<ArrayList> finalData = new ArrayList<ArrayList>();
		try {
			rawList = this.weightDAO.getWeightList();
		} catch (DALException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < rawList.size(); i++){
			finalData.add(new ArrayList<>());
			finalData.get(i).add(rawList.get(i).getWID());
			finalData.get(i).add(rawList.get(i).getopID());
			finalData.get(i).add(rawList.get(i).getMS());
		}
		
		return finalData;
	}


}