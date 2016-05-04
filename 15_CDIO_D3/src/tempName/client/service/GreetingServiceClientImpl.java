package tempName.client.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import tempName.client.gui.MainGUI;

public class GreetingServiceClientImpl implements GreetingServiceClientInt{
	private GreetingServiceAsync service;
	private MainGUI maingui;
	
	public GreetingServiceClientImpl(String url){
		this.service = GWT.create(GreetingService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		
		this.maingui = new MainGUI(this);
	}
	
	@Override
	public void getMeasurements() {
		this.service.getMeasurements(new defaultCallback());
		
	}
	@Override
	public void getOperators() {
		this.service.getOperators(new defaultCallback());
		
	}
	@Override
	public void checkLogin(int id, String pass){
		this.service.checkLogin(id, pass, new defaultCallback());
	}
	@Override
	public void createOp(int id, String name, String ini, String cpr, String password, boolean admin) {
		this.service.createOp(id, name, ini, cpr, password, admin, new defaultCallback());
	}
	
	public MainGUI getMainGUI(){
		return this.maingui;
	}
	
	@Override
	public void connectDatabase() {
		this.service.connectDatabase(new defaultCallback());
		
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
				System.out.println(loginCheck);
				maingui.updateLogin(loginCheck);
			} else if (result instanceof ArrayList){
				ArrayList data = (ArrayList) result;
				if (data.get(0) instanceof ArrayList){
					maingui.updateMeasurements(data);				
				} else if (data.get(0) instanceof HashMap){
					maingui.updateOperators(data);
				}
			}
		}
		
	}



}
