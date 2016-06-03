package tempName.client.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import tempName.client.service.GreetingServiceClientImpl;
import tempName.server.data.dto.WeightDTO;

public class MainGUI extends Composite {
	private String name;
	private int id;
	private String password;
	private String ini;
	private String cpr;
	private boolean admin;
	private boolean userAdmin;
	private ArrayList<WeightDTO> measurements = new ArrayList<WeightDTO>();
	private ArrayList<HashMap> operators = new ArrayList<HashMap>();
	private GreetingServiceClientImpl serviceImpl;
	private VerticalPanel container = new VerticalPanel();
	private OperatoerMenu opMenu;
	private AdminMenu AM = new AdminMenu(container, serviceImpl, this, id);

	public MainGUI(GreetingServiceClientImpl serviceImpl){
		this.serviceImpl = serviceImpl;
		this.serviceImpl.connectDatabase();
		loginScreen();
	}
	public void loginScreen(){
		final Button loginButton = new Button("Login");
		final TextBox loginUsername = new TextBox();
		final Label logUserLabel = new Label("Username: ");
		final TextBox loginPassword = new TextBox();
		final Label logPassLabel = new Label("Password: ");
		initWidget(container);
		container.clear();
		container.add(logUserLabel);
		container.add(loginUsername);
		container.add(logPassLabel);
		container.add(loginPassword);
		container.add(loginButton);

		loginButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				name = loginUsername.getText();
				if (name.matches("^[0-9][0-9]?$|^100$")){
					id = Integer.parseInt(name);
					password = loginPassword.getText();
					serviceImpl.checkLogin(id, password);
				} else {
					Window.alert("Username must be a number bewteen 0 - 100");
				}
			}
		});
	}
	public void loginScreen2(){
		final Button loginButton = new Button("Login");
		final TextBox loginUsername = new TextBox();
		final Label logUserLabel = new Label("Username: ");
		final TextBox loginPassword = new TextBox();
		final Label logPassLabel = new Label("Password: ");
		container.clear();
		container.add(logUserLabel);
		container.add(loginUsername);
		container.add(logPassLabel);
		container.add(loginPassword);
		container.add(loginButton);

		loginButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				name = loginUsername.getText();
				if (name.matches("^[0-9][0-9]?$|^100$")){
					id = Integer.parseInt(name);
					password = loginPassword.getText();
					serviceImpl.checkLogin(id, password);
				} else {
					Window.alert("Username must be a number bewteen 0 - 100");
				}
			}
		});
	}

	public void updateMeasurements(ArrayList mm){
		measurements = mm;
	}

	public void updateOperators(ArrayList op){
		operators = op;
	}
	public void updateLogin(String check) {
		if (check.equals("true")){
			serviceImpl.getAdmin(id);
		} else if (check.equals("false")){
			Window.alert("FAIL");
		} else {
			Window.alert("Something went wrong in login check");
		}
	}
	public void adminCheck(boolean admin){
		if (admin){
			AM.adminMenu();
		} else {
			opMenu = new OperatoerMenu(container, serviceImpl, this, id);
			opMenu.opMenu();
		}
	}
}
