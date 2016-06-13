package tempName.client.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

import tempName.shared.dto.ProduktBatchDTO;
import tempName.shared.dto.ProduktBatchKomponentDTO;
import tempName.shared.dto.RaavareBatchDTO;
import tempName.shared.dto.RaavareDTO;
import tempName.shared.dto.ReceptDTO;
import tempName.shared.dto.ReceptKomponentDTO;
import tempName.shared.dto.WeightDTO;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ServiceAsync {
	void getOperators(AsyncCallback<ArrayList<HashMap<String, String>>> callback);
	void checkLogin(int id, String pass, AsyncCallback callback);
	void connectDatabase(AsyncCallback callback);
	void createOp(String name, String ini, String cpr, String password, int admin, AsyncCallback<Void> callback);
	void updateOp(int id, String name, String ini, String cpr, String password, int admin, AsyncCallback<Void> callback);
	void getAdmin(int id, AsyncCallback callback);
	void addRaavare(int id, String rName, String deliverer, AsyncCallback callback);
	void getRaavare(AsyncCallback<ArrayList<RaavareDTO>> callback);
	void addRecept(int id, String receptName, AsyncCallback callback);
	void getRecept(AsyncCallback<ArrayList<ReceptDTO>> callback);
	void addRaavareBatch(int id, int raavareID, int maengde, AsyncCallback callback);
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
	void deleteProduktKomp(int pbID, int rbID, AsyncCallback<Void> callback);
	void deleteReceptKomp(int receptID, int raavareID, AsyncCallback<Void> callback);
	void getProduktKomp(AsyncCallback<ArrayList<ProduktBatchKomponentDTO>> callback);
	void getReceptKomp(AsyncCallback<ArrayList<ReceptKomponentDTO>> callback);
	void updateProduktKomp(int pbID, int rbID, double tara, double netto, int oprID, AsyncCallback<Void> callback);
	void updateReceptKomp(int receptID, int raavareID, double nomNetto, double tolerance, AsyncCallback<Void> callback);
}
