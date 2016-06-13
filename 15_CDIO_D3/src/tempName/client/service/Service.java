package tempName.client.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import tempName.server.data.daointerface.DALException;
import tempName.shared.dto.ProduktBatchDTO;
import tempName.shared.dto.ProduktBatchKomponentDTO;
import tempName.shared.dto.RaavareBatchDTO;
import tempName.shared.dto.RaavareDTO;
import tempName.shared.dto.ReceptDTO;
import tempName.shared.dto.ReceptKomponentDTO;
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
	ArrayList<ProduktBatchKomponentDTO> getProduktKomp();
	ArrayList<ReceptKomponentDTO> getReceptKomp();
	String checkLogin(int id, String pass);
	void connectDatabase();
	void createOp(String name, String ini, String cpr, String password, int admin) throws DALException;
	void updateOp(int id, String name, String ini, String cpr, String password, int admin) throws DALException;
	int getAdmin(int id);
	void addMeasurement(double mm, int id);
	void addRaavare(int id, String rName, String deliverer);
	void addRecept(int id, String receptName);
	void addRaavareBatch(int id, int raavareID, int maengde) throws DALException;
	void addProduktBatch(int status, int receptID) throws DALException;
	void updateRaavare(int id, String name, String deliverer);
	void deleteRaavare(int id) throws DALException;
	void updateRecept(int id, String name);
	void deleteRecept(int id) throws DALException;
	void updateRaavareBatch(int id, int rID, double maengde) throws DALException;
	void deleteRaavareBatch(int id) throws DALException;
	void updateProduktBatch(int id, int status, int rID) throws DALException;
	void deleteProduktBatch(int id) throws DALException;
	void updateProduktKomp(int pbID, int rbID, double tara, double netto, int oprID) throws DALException;
	void updateReceptKomp(int receptID, int raavareID, double nomNetto, double tolerance) throws DALException;
	void deleteProduktKomp(int pbID, int rbID) throws DALException;
	void deleteReceptKomp(int receptID, int raavareID) throws DALException;
}
