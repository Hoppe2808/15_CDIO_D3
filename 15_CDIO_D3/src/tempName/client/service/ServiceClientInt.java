package tempName.client.service;

public interface ServiceClientInt {
	void getMeasurements();
	void getOperators();
	void checkLogin(int id, String pass);
	void connectDatabase();
	void createOp(int id, String name, String ini, String cpr, String password, int admin);
	void updateOp(int id, String name, String ini, String cpr, String password, int admin);
	void getAdmin(int id);
	void addMeasurement(double mm, int id);
	void addRaavare(String rName, String deliverer);
	void getRaavare();
	void addRecept(String receptName);
	void getRecept();
	void addRaavareBatch(int raavareID, int maengde);
	void getRaavareBatch();
	void addProduktBatch(int status, int receptID);
	void getProduktBatch();
	void updateRaavare(int id, String name, String deliverer);
	void deleteRaavare(int id);
	void deleteRecept(int id);
}
