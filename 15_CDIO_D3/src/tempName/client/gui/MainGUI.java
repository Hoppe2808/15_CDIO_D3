package tempName.client.gui;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mysql.fabric.xmlrpc.base.Array;

import tempName.client.service.ServiceClientImpl;
import tempName.shared.dto.ProduktBatchDTO;
import tempName.shared.dto.RaavareBatchDTO;
import tempName.shared.dto.RaavareDTO;
import tempName.shared.dto.ReceptDTO;
import tempName.shared.dto.WeightDTO;

public class MainGUI extends Composite {
	private String name;
	private int id;
	private String password;
	private boolean initialized;
	private int menu;
	protected ServiceClientImpl serviceImpl;
	private VerticalPanel container = new VerticalPanel();
	private ArrayList<WeightDTO> measurements = new ArrayList<WeightDTO>();
	private ArrayList<HashMap> operators = new ArrayList<HashMap>();
	private ArrayList<RaavareDTO> raavare = new ArrayList<RaavareDTO>();
	private ArrayList<ReceptDTO> recept = new ArrayList<ReceptDTO>();
	private ArrayList<RaavareBatchDTO> raavareBatch = new ArrayList<RaavareBatchDTO>();
	private ArrayList<ProduktBatchDTO> produktBatch = new ArrayList<ProduktBatchDTO>();
	private FarmaceutMenu faMenu = new FarmaceutMenu(container, this);
	private AdminMenu AM = new AdminMenu(container, this, id, operators);
	private VaerksfoererMenu vaMenu = new VaerksfoererMenu(container, this);
	private Label loginError = new Label();

	public MainGUI(ServiceClientImpl serviceImpl){
		this.serviceImpl = serviceImpl;
		this.serviceImpl.connectDatabase();
		loginScreen();
	}
	public void loginScreen(){
		menu = 0;
		loginError.setText("");
		final Label loginHeader = new Label("Log ind");
		loginHeader.addStyleName("HeaderLabel");
		final Button loginButton = new Button("Log ind");
		final TextBox loginUsername = new TextBox();
		final Label logUserLabel = new Label("Brugernavn: ");
		final TextBox loginPassword = new TextBox();
		final Label logPassLabel = new Label("Adgangskode: ");
		if (!(initialized)){
			initWidget(container);
			initialized = true;
		}
		loginHeader.getElement().setAttribute("align", "center");
		container.getElement().setAttribute("align", "center");
		container.clear();
		container.add(loginHeader);
		container.add(logUserLabel);
		container.add(loginUsername);
		container.add(logPassLabel);
		container.add(loginPassword);
		container.add(loginButton);
		container.add(loginError);

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				loginUsername.setFocus(true);
			}
		});
		loginButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				name = loginUsername.getText();
				if (name.matches("^[0-9][0-9]?$|^100$")){
					id = Integer.parseInt(name);
					password = loginPassword.getText();
					serviceImpl.checkLogin(id, password);
				} else {
					Window.alert("Brugernavnet skal være et tal mellem 0 - 100");
				}
			}
		});
		loginPassword.addKeyDownHandler(new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event) {
				if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					name = loginUsername.getText();
					if (name.matches("^[0-9][0-9]?$|^100$")){
						id = Integer.parseInt(name);
						password = loginPassword.getText();
						serviceImpl.checkLogin(id, password);
					} else {
						Window.alert("Brugernavnet skal være et tal mellem 0 - 100");
					}
				}
			}
		});
		loginUsername.addKeyDownHandler(new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event) {
				if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					name = loginUsername.getText();
					if (name.matches("^[0-9][0-9]?$|^100$")){
						id = Integer.parseInt(name);
						password = loginPassword.getText();
						serviceImpl.checkLogin(id, password);
					} else {
						Window.alert("Brugernavnet skal være et tal mellem 0 - 100");
					}
				}
			}
		});
	}

	public void updateLogin(String check) {
		if (check.equals("true")){
			serviceImpl.getAdmin(id);
		} else if (check.equals("false")){
			Window.alert("Forkert brugernavn eller adgangskode");
		} else {
			Window.alert("Noget gik galt med log ind tjek");
		}
	}
	public void adminCheck(int admin){
		if (admin == 1){
			AM.adminMenu();
			menu = 1;
		} else if (admin == 2){
			loginError.setText("En operatør kan ikke logge ind her");
		} else if (admin == 3){
			menu = 2;
			faMenu.farmaMenu();
		} else if (admin == 4){
			menu = 3;
			vaMenu.foremanMenu();
		}
	}
	public void updateOperators(ArrayList op){
		operators = op;
		AM.updateOperators(operators);
	}
	public void updateRaavare(ArrayList raavare){
		this.raavare = raavare;
		if (menu == 1){
			AM.updateRaavare(raavare);
		}else if (menu == 2){
			faMenu.updateRaavare(raavare);
		}
	}
	public void updateRecept(ArrayList recept){
		this.recept = recept;
		if (menu == 1){
			AM.updateRecept(recept);
		}else if (menu == 2){
			faMenu.updateRecept(recept);
		}
	}
	public void updateRaavareBatch(ArrayList raavareBatch){
		this.raavareBatch = raavareBatch;
		if (menu == 1){
			AM.updateRaavareBatch(raavareBatch);
		}else if (menu == 2){
			faMenu.updateRaavareBatch(raavareBatch);
		}else if (menu == 3){
			vaMenu.updateRaavareBatch(raavareBatch);
		}
	}
	public void updateProduktBatch(ArrayList produktBatch){
		this.produktBatch = produktBatch;
		if (menu == 1){
			AM.updateProduktBatch(produktBatch);
		}else if (menu == 2){
			faMenu.updateProduktBatch(produktBatch);
		}else if (menu == 3){
			vaMenu.updateProduktBatch(produktBatch);
		}
	}
}
