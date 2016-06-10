package tempName.client.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

import tempName.shared.dto.ProduktBatchDTO;
import tempName.shared.dto.RaavareBatchDTO;
import tempName.shared.dto.RaavareDTO;
import tempName.shared.dto.ReceptDTO;
import tempName.shared.dto.WeightDTO;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ServiceAsync {
	void getMeasurements(AsyncCallback<ArrayList<WeightDTO>> callback);
	void getOperators(AsyncCallback<ArrayList<HashMap<String, String>>> callback);
	void checkLogin(int id, String pass, AsyncCallback callback);
	void connectDatabase(AsyncCallback callback);
	void createOp(String name, String ini, String cpr, String password, int admin, AsyncCallback<Void> callback);
	void updateOp(int id, String name, String ini, String cpr, String password, int admin, AsyncCallback<Void> callback);
	void getAdmin(int id, AsyncCallback callback);
	void addMeasurement(double mm, int id, AsyncCallback callback);
	void addRaavare(String rName, String deliverer, AsyncCallback callback);
	void getRaavare(AsyncCallback<ArrayList<RaavareDTO>> callback);
	void addRecept(String receptName, AsyncCallback callback);
	void getRecept(AsyncCallback<ArrayList<ReceptDTO>> callback);
	void addRaavareBatch(int raavareID, int maengde, AsyncCallback callback);
	void getRaavareBatch(AsyncCallback<ArrayList<RaavareBatchDTO>> callback);
	void addProduktBatch(int status, int receptID, AsyncCallback callback);
	void getProduktBatch(AsyncCallback<ArrayList<ProduktBatchDTO>> callback);
	void updateRaavare(int id, String name, String deliverer, AsyncCallback callback);
	void deleteRaavare(int id, AsyncCallback callback);
	void updateRecept(int id, String name, AsyncCallback callback);
	void deleteRecept(int id, AsyncCallback callback);
	void updateRaavareBatch(int id, int rID, double maengde, AsyncCallback callback);
	void deleteRaavareBatch(int id, AsyncCallback callback);
	void updateProduktBatch(int id, int status, int rID, AsyncCallback callback);
	void deleteProduktBatch(int id, AsyncCallback callback);
}
