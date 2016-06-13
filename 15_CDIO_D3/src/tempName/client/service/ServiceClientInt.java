package tempName.client.service;

public interface ServiceClientInt {
	void getMeasurements();
	void getOperators();
	void checkLogin(int id, String pass);
	void connectDatabase();
	void createOp(String name, String ini, String cpr, String password, int admin);
	void updateOp(int id, String name, String ini, String cpr, String password, int admin);
	void getAdmin(int id);
	void addMeasurement(double mm, int id);
	void addRaavare(int id, String rName, String deliverer);
	void getRaavare();
	void addRecept(int id, String receptName);
	void getRecept();
	void addRaavareBatch(int rBID, int raavareID, int maengde);
	void getRaavareBatch();
	void addProduktBatch(int status, int receptID);
	void getProduktBatch();
	void updateRaavare(int id, String name, String deliverer);
	void deleteRaavare(int id);
	void deleteRecept(int id);
	void updateRaavareBatch(int id, int rID, double maengde);
	void deleteRaavareBatch(int id);
	void updateProduktBatch(int id, int status, int rID);
	void deleteProduktBatch(int id);
	void getProduktKomp();
	void getReceptKomp();
	void updateProduktKomp(int pbID, int rbID, double tara, double netto, int oprID);
	void updateReceptKomp(int receptID, int raavareID, double nomNetto, double tolerance);
	void deleteProduktKomp(int pbID, int rbID);
	void deleteReceptKomp(int receptID, int raavareID);
}
