package tempName.client.service;

public interface GreetingServiceClientInt {
	void getMeasurements();
	void getOperators();
	void checkLogin(int id, String pass);
	void connectDatabase();
	void createOp(int id, String name, String ini, String cpr, String password, int admin);
	void updateOp(int id, String name, String ini, String cpr, String password, int admin);
	void getAdmin(int id);
	void addMeasurement(double mm, int id);
}
