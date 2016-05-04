package tempName.client.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void getMeasurements(AsyncCallback<ArrayList<ArrayList>> callback);
	void getOperators(AsyncCallback<ArrayList<HashMap<String, String>>> callback);
	void checkLogin(int id, String pass, AsyncCallback callback);
	void connectDatabase(AsyncCallback callback);
	void createOp(int id, String name, String ini, String cpr, String password, boolean admin, AsyncCallback<Void> callback);
}
