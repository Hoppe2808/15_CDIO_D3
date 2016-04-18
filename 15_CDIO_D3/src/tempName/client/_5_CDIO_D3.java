package tempName.client;

import tempName.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import cdio1.Operatoer;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class _5_CDIO_D3 implements EntryPoint {
	private String name;
	private String password;
	private String cpr;
	private boolean admin;
	
	private Operatoer op = new Operatoer();
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button submitButton = new Button("Send");
		final TextBox username = new TextBox();
		final TextBox cprField = new TextBox();
		final TextBox passwordField = new TextBox();
		final RadioButton adminYes = new RadioButton("radioGroup", "Yes");
		final RadioButton adminNo = new RadioButton("radioGroup", "No");
		username.setText("Name");
		cprField.setText("CprNr");
		passwordField.setText("Password");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		submitButton.addStyleName("sendButton");
		
		adminYes.setValue(false);
		adminNo.setValue(true);
		
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(3);
		panel.add(adminYes);
		panel.add(adminNo);

		
		submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				name = username.getText();
				password = passwordField.getText();
				cpr = cprField.getText();
				if (adminYes.getValue()){
					admin = true;
				} else if(adminNo.getValue()){
					admin = false;
				} else {
					Window.alert("Something went wrong in checking for admin status");
				}
				op.addOp(11, name, password, cpr, admin);
			}
		});
		
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(username);
		RootPanel.get("cprFieldContainer").add(cprField);
		RootPanel.get("passwordFieldContainer").add(passwordField);
		RootPanel.get("sendButtonContainer").add(submitButton);
		RootPanel.get("isAdminButtonContainer").add(panel);
		RootPanel.get("errorLabelContainer").add(errorLabel);
		// Focus the cursor on the name field when the app loads
		username.setFocus(true);
		username.selectAll();
		

	}
}
