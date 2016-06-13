package tempName.client.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import tempName.client.gui.MainGUI;
import tempName.shared.dto.ProduktBatchDTO;
import tempName.shared.dto.ProduktBatchKomponentDTO;
import tempName.shared.dto.RaavareBatchDTO;
import tempName.shared.dto.RaavareDTO;
import tempName.shared.dto.ReceptDTO;
import tempName.shared.dto.ReceptKomponentDTO;

public class ServiceClientImpl implements ServiceClientInt{
	private ServiceAsync service;
	private MainGUI maingui;

	public ServiceClientImpl(String url){
		this.service = GWT.create(Service.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);

		this.maingui = new MainGUI(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void getMeasurements() {
		this.service.getMeasurements(new defaultCallback());

	}
	@SuppressWarnings("unchecked")
	@Override
	public void getOperators() {
		this.service.getOperators(new defaultCallback());

	}
	@Override
	public void checkLogin(int id, String pass){
		this.service.checkLogin(id, pass, new defaultCallback());
	}
	@Override
	public void createOp(String name, String ini, String cpr, String password, int admin){
		this.service.createOp(name, ini, cpr, password, admin, new operatoerCallback(1));
	}
	@Override
	public void updateOp(int id, String name, String ini, String cpr, String password, int admin){
		this.service.updateOp(id, name, ini, cpr, password, admin, new operatoerCallback(2));
	}
	public MainGUI getMainGUI(){
		return this.maingui;
	}

	@Override
	public void connectDatabase() {
		this.service.connectDatabase(new defaultCallback());

	}
	@Override
	public void getAdmin(int id){
		this.service.getAdmin(id, new defaultCallback());
	}
	@Override
	public void addMeasurement(double mm, int id){
		this.service.addMeasurement(mm, id, new defaultCallback());
	}
	@Override
	public void addRaavare(int id, String rName, String deliverer){
		this.service.addRaavare(id, rName, deliverer, new defaultCallback());
	}
	@Override
	public void getRaavare(){
		this.service.getRaavare(new defaultCallback());
	}
	@Override
	public void addRecept(int id, String receptName){
		this.service.addRecept(id, receptName, new defaultCallback());
	}
	@Override
	public void getRecept(){
		this.service.getRecept(new defaultCallback());
	}
	@Override
	public void addRaavareBatch(int id, int raavareID, int maengde){
		this.service.addRaavareBatch(id, raavareID, maengde, new defaultCallback());
	}
	@Override
	public void getRaavareBatch(){
		this.service.getRaavareBatch(new defaultCallback());
	}
	@Override
	public void addProduktBatch(int status, int receptID){
		this.service.addProduktBatch(status, receptID, new defaultCallback());
	}
	@Override
	public void getProduktBatch(){
		this.service.getProduktBatch(new defaultCallback());
	}
	@Override
	public void updateRaavare(int id, String name, String deliverer){
		this.service.updateRaavare(id, name, deliverer, new defaultCallback());
	}
	@Override
	public void deleteRaavare(int id){
		this.service.deleteRaavare(id, new defaultCallback());
	}
	
	public void updateRecept(int id, String name){
		this.service.updateRecept(id, name, new defaultCallback());
	}
	@Override
	public void deleteRecept(int id){
		this.service.deleteRecept(id, new defaultCallback());
	}
	@Override
	public void deleteRaavareBatch(int id) {
		this.service.deleteRaavareBatch(id, new defaultCallback());
	}
	@Override
	public void updateRaavareBatch(int id, int rID, double maengde){
		this.service.updateRaavareBatch(id, rID, maengde, new defaultCallback());
	}
	@Override
	public void updateProduktBatch(int id, int status, int rID){
		this.service.updateProduktBatch(id, status, rID, new defaultCallback());
	}
	@Override
	public void deleteProduktBatch(int id){
		this.service.deleteProduktBatch(id, new defaultCallback());
	}

	@Override
	public void getProduktKomp() {
		this.service.getProduktKomp(new defaultCallback());
	}
	
	@Override
	public void getReceptKomp() {
		this.service.getReceptKomp(new defaultCallback());
	}
	
	@Override
	public void addProduktKomp(int pbID, int rbID, double tara, double netto, int oprID) {
		this.service.addProduktKomp(pbID, rbID, tara, netto, oprID, new defaultCallback());
	}
	
	@Override
	public void addReceptKomp(int receptID, int raavareID, double nomNetto, double tolerance) {
		this.service.addReceptKomp(receptID, raavareID, nomNetto, tolerance, new defaultCallback());
	}
	
	@Override
	public void updateProduktKomp(int pbID, int rbID, double tara, double netto, int oprID) {
		this.service.updateProduktKomp(pbID, rbID, tara, netto, oprID, new defaultCallback());
	}
	
	@Override
	public void updateReceptKomp(int receptID, int raavareID, double nomNetto, double tolerance) {
		this.service.updateReceptKomp(receptID, raavareID, nomNetto, tolerance, new defaultCallback());
	}
	
	@Override
	public void deleteProduktKomp(int pbID, int rbID) {
		this.service.deleteProduktKomp(pbID, rbID, new defaultCallback());
	}
	
	@Override
	public void deleteReceptKomp(int receptID, int raavareID) {
		this.service.deleteReceptKomp(receptID, raavareID, new defaultCallback());
	}
	private class defaultCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			String message = caught.getMessage();
			String constraint = "CONSTRAINT";
			if(message.toLowerCase().contains(constraint.toLowerCase())){
				Window.alert("Kan ikke ændre i databasen, fordi der er et problem med fremmednøgler." +
			" Dette kan enten betyde, at du prøver at slette noget, der findes i en anden tabel, " +
						"eller at du prøver at bruge et ID fra en anden tabel, hvor ID'et ikke eksisterer");
			}
		}

		@Override
		public void onSuccess(Object result) {
			if (result instanceof String){
				String loginCheck = (String) result;
				maingui.updateLogin(loginCheck);
			} else if(result instanceof Integer){
				maingui.adminCheck((int) result);
			} else if (result instanceof ArrayList){
				ArrayList data = (ArrayList) result;
				if (data.get(0) instanceof HashMap){
					maingui.updateOperators(data);
				} else if (data.get(0) instanceof RaavareDTO){
					maingui.updateRaavare(data);
				} else if (data.get(0) instanceof ReceptDTO){
					maingui.updateRecept(data);
				} else if (data.get(0) instanceof RaavareBatchDTO){
					maingui.updateRaavareBatch(data);
				} else if (data.get(0) instanceof ProduktBatchDTO){
					maingui.updateProduktBatch(data);
				} else if (data.get(0) instanceof ProduktBatchKomponentDTO){
					maingui.updateProduktBatchKomp(data);
				} else if (data.get(0) instanceof ReceptKomponentDTO){
					maingui.updateReceptKomp(data);
				}
			}
		}
	}
	private class operatoerCallback implements AsyncCallback{
		int type;
		
		public operatoerCallback(int type){
			this.type = type;
		}

		@Override
		public void onFailure(Throwable caught) {
			String message = caught.getMessage();
			String trunctation = "truncation:";
			String password = "'password'";
			String oprName = "'opr_navn'";
			if(message.toLowerCase().contains(trunctation.toLowerCase())){
				if (message.toLowerCase().contains(password.toLowerCase())){
					Window.alert("Din adgangskode er for lang");
				} else if(message.toLowerCase().contains(oprName.toLowerCase())){
					Window.alert("Navnet er for langt");
				}
			}
			
		}

		@Override
		public void onSuccess(Object result) {
			if(type == 1){
				Window.alert("Operatør blev oprettet");
			} else if(type == 2){
				Window.alert("Operatør blev redigeret");
			}
		}
		
	}



}
