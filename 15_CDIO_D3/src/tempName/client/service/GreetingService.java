package tempName.client.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import tempName.server.data.dto.RaavareDTO;
import tempName.server.data.dto.ReceptDTO;
import tempName.server.data.dto.WeightDTO;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	ArrayList<WeightDTO> getMeasurements();

	ArrayList<HashMap<String, String>> getOperators();
	
	ArrayList<RaavareDTO> getRaavare();
	
	ArrayList<ReceptDTO> getRecept();

	String checkLogin(int id, String pass);

	void connectDatabase();

	void createOp(int id, String name, String ini, String cpr, String password, int admin);

	void updateOp(int id, String name, String ini, String cpr, String password, int admin);

	int getAdmin(int id);

	void addMeasurement(double mm, int id);
	
	void addRaavare(String rName, String deliverer);
	
	void addRecept(String receptName);
}
