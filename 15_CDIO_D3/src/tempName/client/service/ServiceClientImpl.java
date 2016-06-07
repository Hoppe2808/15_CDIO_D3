package tempName.client.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import tempName.client.gui.MainGUI;
import tempName.server.data.daointerface.DALException;
import tempName.shared.dto.RaavareBatchDTO;
import tempName.shared.dto.RaavareDTO;
import tempName.shared.dto.ReceptDTO;
import tempName.shared.dto.WeightDTO;

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

	private class defaultCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("Failed");
		}

		@Override
		public void onSuccess(Object result) {
			System.out.println(result);
			if (result instanceof String){
				String loginCheck = (String) result;
				maingui.updateLogin(loginCheck);
			} else if(result instanceof Integer){
				maingui.adminCheck((int) result);
			} else if (result instanceof ArrayList){
				ArrayList data = (ArrayList) result;
				if (data.get(0) instanceof WeightDTO){
					maingui.updateMeasurements(data);				
				} else if (data.get(0) instanceof HashMap){
					maingui.updateOperators(data);
				} else if (data.get(0) instanceof RaavareDTO){
					maingui.updateRaavare(data);
				} else if (data.get(0) instanceof ReceptDTO){
					maingui.updateRecept(data);
				} else if (data.get(0) instanceof RaavareBatchDTO){
					maingui.updateRaavareBatch(data);
				}
			}
		}

	}



}
