package tempName.client.gui;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import tempName.client.service.GreetingServiceClientImpl;
import tempName.server.data.dto.WeightDTO;

public class MainGUI extends Composite {
	private String name;
	private int id;
	private String password;
	protected GreetingServiceClientImpl serviceImpl;
	private VerticalPanel container = new VerticalPanel();
	private OperatoerMenu opMenu;
	private FarmaceutMenu faMenu;
	private ArrayList<WeightDTO> measurements = new ArrayList<WeightDTO>();
	private ArrayList<HashMap> operators = new ArrayList<HashMap>();
	private AdminMenu AM = new AdminMenu(container, serviceImpl, this, id, operators, measurements);

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

	public void updateLogin(String check) {
		if (check.equals("true")){
			serviceImpl.getAdmin(id);
		} else if (check.equals("false")){
			Window.alert("FAIL");
		} else {
			Window.alert("Something went wrong in login check");
		}
	}
	public void adminCheck(int admin){
		if (admin == 1){
			AM.adminMenu();
		} else if (admin == 2){
			opMenu = new OperatoerMenu(container, serviceImpl, this, id);
			opMenu.opMenu();
		} else if (admin == 3){
			faMenu = new FarmaceutMenu(container, measurements, this);
			faMenu.farmaMenu();
		} else {
			System.out.println("Lul");
		}
	}
	public void updateMeasurements(ArrayList mm){
		measurements = mm;
		AM.updateMeasurements(measurements);
	}

	public void updateOperators(ArrayList op){
		operators = op;
		AM.updateOperators(operators);
	}
}
