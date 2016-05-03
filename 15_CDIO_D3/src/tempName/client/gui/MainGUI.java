package tempName.client.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;

import tempName.client.service.GreetingServiceAsync;
import tempName.client.service.GreetingServiceClientImpl;
import tempName.server.data.daoimpl.MYSQLWeightDAO;
import tempName.server.data.daointerface.DALException;
import tempName.server.data.dto.WeightDTO;

public class MainGUI extends Composite {
	private String name;
	private int id;
	private String password;
	private String cpr;
	private boolean admin;
	private boolean loggedIn;
	private ArrayList measurements = new ArrayList();
	private ArrayList<HashMap> operators = new ArrayList<HashMap>();
	private GreetingServiceClientImpl serviceImpl;

	public MainGUI(GreetingServiceClientImpl serviceImpl){
		this.serviceImpl = serviceImpl;
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
		final VerticalPanel container = new VerticalPanel();
		initWidget(container);
		container.add(logUserLabel);
		container.add(loginUsername);
		container.add(logPassLabel);
		container.add(loginPassword);
		container.add(loginButton);

		loginButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loggedIn = false;
				name = loginUsername.getText();
				if (name.matches("^[0-9][0-9]?$|^100$")){
					id = Integer.parseInt(name);
					password = loginPassword.getText();
					serviceImpl.checkLogin(id, password);

					if (!(loggedIn)){
						Window.alert("Wrong login");						
					} else {
						Window.alert("Correct Login");
						adminMenu();
					}
				} else {
					Window.alert("Username must be a number bewteen 0 - 100");
				}
			}
		});

	}
	private void adminMenu(){
		final Label adminHeader = new Label("Admin Menu");
		final Button createOp = new Button("Create new operator");
		final Button deleteOp = new Button("Delete an operator");
		final Button editOp = new Button("Change attributes of operator");
		final Button inspectOp = new Button("Inspect an operator");
		final Button measurements = new Button("Check measurements");
		final Button logout = new Button("Logout");
		final VerticalPanel container = new VerticalPanel();
		container.setSpacing(9);
		container.add(createOp);
		container.add(deleteOp);
		container.add(editOp);
		container.add(inspectOp);
		container.add(measurements);

		createOp.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				createOp();
			}
		});
		deleteOp.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				deleteOp();
			}
		});
		editOp.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				editOp();
			}
		});
		inspectOp.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				inspectOp();
			}
		});
		measurements.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//				measurements();
			}
		});
		logout.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loginScreen();
			}
		});
	}
	private void inspectOp() {
		final Label inspectHeader = new Label("Inspect an operator");
		final ListBox lb = new ListBox();
		final Button inspect = new Button("Inspect");
		final Button back = new Button("<- Back");
		final VerticalPanel container = new VerticalPanel();
		container.setSpacing(9);
		container.add(lb);
		container.add(inspect);

		RootPanel.get("headerContainer").clear();
		RootPanel.get("headerContainer").add(inspectHeader);
		RootPanel.get("bodyContainer").clear();
		RootPanel.get("bodyContainer").add(container);
		RootPanel.get("logout").clear();
		RootPanel.get("back").clear();
		RootPanel.get("back").add(back);

		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				adminMenu();
			}
		});
	}
	private void editOp() {
		final Label editHeader = new Label("Edit an operator");
		final ListBox lb = new ListBox();
		final Label username = new Label("Username: ");
		final TextBox userText = new TextBox();
		final Label password = new Label("Password: ");
		final TextBox passText = new TextBox();
		final Label cpr = new Label("Cpr-number: ");
		final TextBox cprText = new TextBox();
		final RadioButton adminYes = new RadioButton("radioGroup", "Yes");
		final RadioButton adminNo = new RadioButton("radioGroup", "No");
		final Label adminLabel = new Label("Is it an admin? ");
		final Button submit = new Button("Submit");
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(3);
		panel.add(adminYes);
		panel.add(adminNo);

		final Button back = new Button("<- Back");
		final VerticalPanel container = new VerticalPanel();
		container.setSpacing(9);
		container.add(lb);
		container.add(username);
		container.add(userText);
		container.add(cpr);
		container.add(cprText);
		container.add(password);
		container.add(passText);
		container.add(panel);
		container.add(submit);

		RootPanel.get("headerContainer").clear();
		RootPanel.get("headerContainer").add(editHeader);
		RootPanel.get("bodyContainer").clear();
		RootPanel.get("bodyContainer").add(container);
		RootPanel.get("logout").clear();
		RootPanel.get("back").clear();
		RootPanel.get("back").add(back);

		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				adminMenu();
			}
		});
	}
	private void deleteOp() {
		final Label deleteHeader = new Label("Delete operator");
		final ListBox lb = new ListBox();
		final Button delete = new Button("Delete");
		final Button back = new Button("<- Back");
		final VerticalPanel container = new VerticalPanel();
		container.add(lb);
		container.add(delete);
		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				adminMenu();
			}
		});
		RootPanel.get("headerContainer").clear();
		RootPanel.get("headerContainer").add(deleteHeader);
		RootPanel.get("bodyContainer").clear();
		RootPanel.get("bodyContainer").add(container);
		RootPanel.get("logout").clear();
		RootPanel.get("back").clear();
		RootPanel.get("back").add(back);
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
		final Button back = new Button("<- Back");
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(3);
		panel.add(adminYes);
		panel.add(adminNo);
		final VerticalPanel container = new VerticalPanel();
		container.setSpacing(9);

		container.add(usernameLabel);
		container.add(username);
		container.add(cprLabel);
		container.add(cprField);
		container.add(adminLabel);
		container.add(panel);
		container.add(submitButton);

		// We can add style names to widgets
		submitButton.addStyleName("sendButton");

		adminYes.setValue(false);
		adminNo.setValue(true);




		submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//				name = username.getText();
				//				cpr = cprField.getText();
				//				if (adminYes.getValue()){
				//					admin = true;
				//				} else if(adminNo.getValue()){
				//					admin = false;
				//				} else {
				//					Window.alert("Something went wrong in checking for admin status");
				//				}
				//				
				//				f.createOperatoer(name, cpr, admin);
				//				try {
				//					Window.alert(Arrays.toString(f.getOperatoer(1)));
				//				} catch (FException e) {
				//					// TODO Auto-generated catch block
				//					e.printStackTrace();
				//				}
			}
		});
		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				adminMenu();
			}
		});

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("headerContainer").clear();
		RootPanel.get("headerContainer").add(createHeader);
		RootPanel.get("bodyContainer").clear();
		RootPanel.get("bodyContainer").add(container);
		RootPanel.get("logout").clear();
		RootPanel.get("back").clear();
		RootPanel.get("back").add(back);

		// Focus the cursor on the name field when the app loads
		username.setFocus(true);
		username.selectAll();
	}

	private void measurements() {
		final Label measureHeader = new Label("Measurements");
		serviceImpl.getMeasurements();

		final Button back = new Button("<- Back");
		final VerticalPanel container = new VerticalPanel();
		container.setSpacing(9);
		RootPanel.get("headerContainer").clear();
		RootPanel.get("headerContainer").add(measureHeader);
		RootPanel.get("bodyContainer").clear();
		RootPanel.get("bodyContainer").add(container);
		RootPanel.get("logout").clear();
		RootPanel.get("back").clear();
		RootPanel.get("back").add(back);

		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				adminMenu();
			}
		});
	}
	public void updateMeasurements(ArrayList mm){
		measurements = mm;
	}
	public void updateOperators(ArrayList op){
		operators = op;
	}
	public void updateLogin(boolean check) {
		if (check){
			loggedIn = true;	
		} else{
			loggedIn = false;
		}


	}
}
