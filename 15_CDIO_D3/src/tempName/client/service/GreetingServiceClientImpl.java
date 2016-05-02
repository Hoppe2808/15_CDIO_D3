package tempName.client.service;

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
	public void greetServer(String name) {
		this.service.greetServer("Johnson", new defaultCallback());
		
	}
	
	public MainGUI getMainGUI(){
		return this.maingui;
	}

	private class defaultCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("Failed");
		}

		@Override
		public void onSuccess(Object result) {
			if (result instanceof String){
				String greeting = (String) result;
				maingui.updateLabel(greeting);
			}
		}
		
	}

}
