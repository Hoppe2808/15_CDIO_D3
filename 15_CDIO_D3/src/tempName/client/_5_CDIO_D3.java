package tempName.client;


import tempName.client.service.GreetingServiceClientImpl;
import tempName.server.data.database.Connector;

import java.sql.SQLException;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class _5_CDIO_D3 implements EntryPoint {

	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		GreetingServiceClientImpl clientImpl = new GreetingServiceClientImpl(GWT.getModuleBaseURL() + "greet");
		RootPanel.get().add(clientImpl.getMainGUI());
	}

}
