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

import tempName.client.service.ServiceClientImpl;
import tempName.server.data.dto.RaavareBatchDTO;
import tempName.server.data.dto.RaavareDTO;
import tempName.server.data.dto.ReceptDTO;
import tempName.server.data.dto.WeightDTO;

public class MainGUI extends Composite {
	private String name;
	private int id;
	private String password;
	protected ServiceClientImpl serviceImpl;
	private VerticalPanel container = new VerticalPanel();
	private OperatoerMenu opMenu;
	private FarmaceutMenu faMenu;
	private ArrayList<WeightDTO> measurements = new ArrayList<WeightDTO>();
	private ArrayList<HashMap> operators = new ArrayList<HashMap>();
	private ArrayList<RaavareDTO> raavare = new ArrayList<RaavareDTO>();
	private ArrayList<ReceptDTO> recept = new ArrayList<ReceptDTO>();
	private ArrayList<RaavareBatchDTO> raavareBatch = new ArrayList<RaavareBatchDTO>();
	private AdminMenu AM = new AdminMenu(container, serviceImpl, this, id, operators, measurements);
	private VaerksfoererMenu vaMenu = new VaerksfoererMenu(container, measurements, this);

	public MainGUI(ServiceClientImpl serviceImpl){
		this.serviceImpl = serviceImpl;
		this.serviceImpl.connectDatabase();
		loginScreen();
	}
	public void loginScreen(){
		final Label loginHeader = new Label("Login");
		loginHeader.addStyleName("HeaderLabel");
		final Button loginButton = new Button("Login");
		final TextBox loginUsername = new TextBox();
		final Label logUserLabel = new Label("Username: ");
		final TextBox loginPassword = new TextBox();
		final Label logPassLabel = new Label("Password: ");
		initWidget(container);
		container.clear();
		container.add(loginHeader);
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
		final Label loginHeader = new Label("Login");
		loginHeader.addStyleName("HeaderLabel");
		final Button loginButton = new Button("Login");
		final TextBox loginUsername = new TextBox();
		final Label logUserLabel = new Label("Username: ");
		final TextBox loginPassword = new TextBox();
		final Label logPassLabel = new Label("Password: ");
		container.clear();
		container.add(loginHeader);
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
		} else if (admin == 4){
			vaMenu.foremanMenu();
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
	public void updateRaavare(ArrayList raavare){
		this.raavare = raavare;
		AM.updateRaavare(raavare);
		faMenu.updateRaavare(raavare);
	}
	public void updateRecept(ArrayList recept){
		this.recept = recept;
		AM.updateRecept(recept);
		faMenu.updateRecept(recept);
	}
	public void updateRaavareBatch(ArrayList raavareBatch){
		this.raavareBatch = raavareBatch;
		vaMenu.updateRaavareBatch(raavareBatch);
	}
}
