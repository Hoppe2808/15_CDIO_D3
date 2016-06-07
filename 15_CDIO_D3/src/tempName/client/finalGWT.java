package tempName.client;


import tempName.client.service.ServiceClientImpl;
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
		ServiceClientImpl clientImpl = new ServiceClientImpl(GWT.getModuleBaseURL() + "greet");
		RootPanel.get("main").add(clientImpl.getMainGUI());
	}

}
