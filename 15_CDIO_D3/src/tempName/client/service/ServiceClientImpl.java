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
import tempName.shared.dto.RaavareBatchDTO;
import tempName.shared.dto.RaavareDTO;
import tempName.shared.dto.ReceptDTO;

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
	public void createOp(int id, String name, String ini, String cpr, String password, int admin){
		this.service.createOp(id, name, ini, cpr, password, admin, new defaultCallback());
	}
	@Override
	public void updateOp(int id, String name, String ini, String cpr, String password, int admin){
		this.service.updateOp(id, name, ini, cpr, password, admin, new defaultCallback());
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
	public void addRaavare(String rName, String deliverer){
		this.service.addRaavare(rName, deliverer, new defaultCallback());
	}
	@Override
	public void getRaavare(){
		this.service.getRaavare(new defaultCallback());
	}
	@Override
	public void addRecept(String receptName){
		this.service.addRecept(receptName, new defaultCallback());
	}
	@Override
	public void getRecept(){
		this.service.getRecept(new defaultCallback());
	}
	@Override
	public void addRaavareBatch(int raavareID, int maengde){
		this.service.addRaavareBatch(raavareID, maengde, new defaultCallback());
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

	private class defaultCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			String message = caught.getMessage();
			String constraint = "CONSTRAINT";
			if(message.toLowerCase().contains(constraint.toLowerCase())){
				Window.alert("Kan ikke slette, fordi den eksisterer i en anden tabel i databasen");
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
				}
			}
		}

	}




}
