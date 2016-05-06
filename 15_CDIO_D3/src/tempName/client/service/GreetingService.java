package tempName.client.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import tempName.server.data.daointerface.DALException;
import tempName.server.data.dto.WeightDTO;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	ArrayList<WeightDTO> getMeasurements();

	ArrayList<HashMap<String, String>> getOperators();

	String checkLogin(int id, String pass);

	void connectDatabase();
	
	void createOp(int id, String name, String ini, String cpr, String password, boolean admin);
	
	void updateOp(int id, String name, String ini, String cpr, String password, boolean admin);
	
	boolean getAdmin(int id);
}
