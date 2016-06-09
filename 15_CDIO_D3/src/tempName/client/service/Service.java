package tempName.client.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import tempName.server.data.daointerface.DALException;
import tempName.shared.dto.ProduktBatchDTO;
import tempName.shared.dto.RaavareBatchDTO;
import tempName.shared.dto.RaavareDTO;
import tempName.shared.dto.ReceptDTO;
import tempName.shared.dto.WeightDTO;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface Service extends RemoteService {
	ArrayList<WeightDTO> getMeasurements();
	ArrayList<HashMap<String, String>> getOperators();
	ArrayList<RaavareDTO> getRaavare();
	ArrayList<ReceptDTO> getRecept();
	ArrayList<RaavareBatchDTO> getRaavareBatch();
	ArrayList<ProduktBatchDTO> getProduktBatch();
	String checkLogin(int id, String pass);
	void connectDatabase();
	void createOp(int id, String name, String ini, String cpr, String password, int admin);
	void updateOp(int id, String name, String ini, String cpr, String password, int admin);
	int getAdmin(int id);
	void addMeasurement(double mm, int id);
	void addRaavare(String rName, String deliverer);
	void addRecept(String receptName);
	void addRaavareBatch(int raavareID, int maengde);
	void addProduktBatch(int status, int receptID);
	void updateRaavare(int id, String name, String deliverer);
	void deleteRaavare(int id) throws DALException;
}
