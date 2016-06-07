package tempName.client.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

import tempName.server.data.dto.RaavareDTO;
import tempName.server.data.dto.ReceptDTO;
import tempName.server.data.dto.WeightDTO;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void getMeasurements(AsyncCallback<ArrayList<WeightDTO>> callback);
	void getOperators(AsyncCallback<ArrayList<HashMap<String, String>>> callback);
	void checkLogin(int id, String pass, AsyncCallback callback);
	void connectDatabase(AsyncCallback callback);
	void createOp(int id, String name, String ini, String cpr, String password, int admin, AsyncCallback<Void> callback);
	void updateOp(int id, String name, String ini, String cpr, String password, int admin, AsyncCallback<Void> callback);
	void getAdmin(int id, AsyncCallback callback);
	void addMeasurement(double mm, int id, AsyncCallback callback);
	void addRaavare(String rName, String deliverer, AsyncCallback callback);
	void getRaavare(AsyncCallback<ArrayList<RaavareDTO>> callback);
	void addRecept(String receptName, AsyncCallback callback);
	void getRecept(AsyncCallback<ArrayList<ReceptDTO>> callback);
	void addRaavareBatch(int raavareID, int maengde, AsyncCallback callback);
}
