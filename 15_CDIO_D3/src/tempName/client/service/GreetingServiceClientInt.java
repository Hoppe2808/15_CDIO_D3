package tempName.client.service;

import tempName.server.data.daointerface.DALException;

public interface GreetingServiceClientInt {
	void getMeasurements();
	void getOperators();
	void checkLogin(int id, String pass);
	void connectDatabase();
	void createOp(int id, String name, String ini, String cpr, String password, boolean admin);
}
