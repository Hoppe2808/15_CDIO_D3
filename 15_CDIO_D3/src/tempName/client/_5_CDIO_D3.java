package tempName.client;

import tempName.shared.FieldVerifier;

import java.util.Arrays;

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
import com.google.gwt.user.client.ui.Image;
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
	private Funktionalitet f = new Funktionalitet(op);
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
		loginScreen();
	}
	
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
				if (name.matches("^[0-9][0-9]?$|^100$")){
					id = Integer.parseInt(name);
					password = loginPassword.getText();
					for (int i = 0; i < op.getOperatoerArrayLaengde(); i++){
						if (op.getOprId(i) == id){
							if (f.tjekLogin(id, password, op.getAdminStatus(i))){
								loggedIn = true;
								createOp();
							}
						}
					}
					if (!(loggedIn)){
						Window.alert("Wrong login");						
					}
				} else {
					Window.alert("Username must be a number bewteen 0 - 100");
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
	private void createOp() {
		final Label createHeader = new Label("Create operator");
		final Button submitButton = new Button("Send");
		final TextBox username = new TextBox();
		final Label usernameLabel = new Label("Name: ");
		final TextBox cprField = new TextBox();
		final Label cprLabel = new Label("Cpr-number: ");
		final RadioButton adminYes = new RadioButton("radioGroup", "Yes");
		final RadioButton adminNo = new RadioButton("radioGroup", "No");
		final Label adminLabel = new Label("Is it an admin? ");
		final Label errorLabel = new Label();
		final Button logOut = new Button();

		// We can add style names to widgets
		submitButton.addStyleName("sendButton");
		logOut.addStyleName("logOutButton");
		
		adminYes.setValue(false);
		adminNo.setValue(true);
		
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(3);
		panel.add(adminYes);
		panel.add(adminNo);


		
		submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				name = username.getText();
				cpr = cprField.getText();
				if (adminYes.getValue()){
					admin = true;
				} else if(adminNo.getValue()){
					admin = false;
				} else {
					Window.alert("Something went wrong in checking for admin status");
				}
				
				f.createOperatoer(name, cpr, admin);
				try {
					Window.alert(Arrays.toString(f.getOperatoer(1)));
				} catch (FException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		logOut.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loginScreen();
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
		RootPanel.get("passwordLabelContainer").clear();
		RootPanel.get("sendButtonContainer").clear();
		RootPanel.get("sendButtonContainer").add(submitButton);
		RootPanel.get("isAdminButtonContainer").add(panel);
		RootPanel.get("adminLabelContainer").add(adminLabel);
		RootPanel.get("errorLabelContainer").clear();
		RootPanel.get("errorLabelContainer").add(errorLabel);
		RootPanel.get("logOutContainer").add(logOut);
		// Focus the cursor on the name field when the app loads
		username.setFocus(true);
		username.selectAll();
	}
}
