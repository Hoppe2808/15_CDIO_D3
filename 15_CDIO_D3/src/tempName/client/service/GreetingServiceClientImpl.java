package tempName.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class GreetingServiceClientImpl implements GreetingServiceClientInt{
	private GreetingServiceAsync service;
	
	public GreetingServiceClientImpl(String url){
		this.service = GWT.create(GreetingService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
	}

	@Override
	public void greetServer(String name) {
		this.service.greetServer("Johnson", new defaultCallback());
		
	}

	private class defaultCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("Failed");
		}

		@Override
		public void onSuccess(Object result) {
			System.out.println("Success");
		}
		
	}

}
