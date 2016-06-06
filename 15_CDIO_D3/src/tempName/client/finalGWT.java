package tempName.client;


import tempName.client.service.GreetingServiceClientImpl;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class finalGWT implements EntryPoint {


	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		GreetingServiceClientImpl clientImpl = new GreetingServiceClientImpl(GWT.getModuleBaseURL() + "greet");
		RootPanel.get("main").add(clientImpl.getMainGUI());
	}

}
