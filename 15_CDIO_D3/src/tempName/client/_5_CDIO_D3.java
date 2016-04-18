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

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class _5_CDIO_D3 implements EntryPoint {
	private String name;
	private int id;
	private String password;
	private String cpr;
	private boolean admin;
	private boolean loggedIn;
	
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
	public void loginScreen(){
		final Label loginHeader = new Label("Login");
		final Button loginButton = new Button("Login");
		final TextBox loginUsername = new TextBox();
		final Label logUserLabel = new Label("Username: ");
		final TextBox loginPassword = new TextBox();
		final Label logPassLabel = new Label("Password: ");
		final Label loginError = new Label();
		
		loginButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				name = loginUsername.getText();
				if (name.matches("^[1-9][0-9]?$|^100$")){
					id = Integer.parseInt(name);
					password = loginPassword.getText();
					if (op.getOprId(id) != 0){
						loggedIn = true;						
					}
				} else {
					Window.alert("Wrong login");
				}
			}
		});
		
		RootPanel.get("headerContainer").add(loginHeader);
		RootPanel.get("nameFieldContainer").add(loginUsername);
		RootPanel.get("nameLabelContainer").add(logUserLabel);
		RootPanel.get("passwordFieldContainer").add(loginPassword);
		RootPanel.get("passwordLabelContainer").add(logPassLabel);
		RootPanel.get("sendButtonContainer").add(loginButton);
		RootPanel.get("errorLabelContainer").add(loginError);
		
	}
	public void onModuleLoad() {
		
		while (!(loggedIn)){
			loginScreen();
		}
		createOp();
		

	}
	private void createOp() {
		final Label createHeader = new Label("Create operator");
		final Button submitButton = new Button("Send");
		final TextBox username = new TextBox();
		final Label usernameLabel = new Label("Name: ");
		final TextBox cprField = new TextBox();
		final Label cprLabel = new Label("Password: ");
		final TextBox passwordField = new TextBox();
		final Label passwordLabel = new Label("Cpr-number: ");
		final RadioButton adminYes = new RadioButton("radioGroup", "Yes");
		final RadioButton adminNo = new RadioButton("radioGroup", "No");
		final Label adminLabel = new Label("Is it an admin? ");
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
				Window.alert(op.getOprNavn(1));
			}
		});
		
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("headerContainer").clear();
		RootPanel.get("headerContainer").add(createHeader);
		RootPanel.get("nameFieldContainer").clear();
		RootPanel.get("nameFieldContainer").add(username);
		RootPanel.get("nameLabelContainer").clear();;
		RootPanel.get("nameLabelContainer").add(usernameLabel);
		RootPanel.get("cprFieldContainer").add(cprField);
		RootPanel.get("cprLabelContainer").add(cprLabel);
		RootPanel.get("passwordFieldContainer").clear();
		RootPanel.get("passwordFieldContainer").add(passwordField);
		RootPanel.get("passwordLabelContainer").clear();;
		RootPanel.get("passwordLabelContainer").add(passwordLabel);
		RootPanel.get("sendButtonContainer").clear();
		RootPanel.get("sendButtonContainer").add(submitButton);
		RootPanel.get("isAdminButtonContainer").add(panel);
		RootPanel.get("adminLabelContainer").add(adminLabel);
		RootPanel.get("errorLabelContainer").clear();
		RootPanel.get("errorLabelContainer").add(errorLabel);
		// Focus the cursor on the name field when the app loads
		username.setFocus(true);
		username.selectAll();
	}
}
