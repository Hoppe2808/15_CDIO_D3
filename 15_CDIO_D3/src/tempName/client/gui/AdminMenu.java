package tempName.client.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import tempName.server.data.daointerface.DALException;
import tempName.shared.dto.ProduktBatchDTO;
import tempName.shared.dto.ProduktBatchKomponentDTO;
import tempName.shared.dto.RaavareBatchDTO;
import tempName.shared.dto.RaavareDTO;
import tempName.shared.dto.ReceptDTO;
import tempName.shared.dto.ReceptKomponentDTO;

public class AdminMenu{

	private VerticalPanel container;
	private MainGUI mainGUI;
	private int id;
	private int admin;
	private String ini, cpr, name, password;
	private ArrayList<HashMap> operators;
	private ArrayList<RaavareDTO> raavare;
	private ArrayList<ReceptDTO> recept;
	private ArrayList<RaavareBatchDTO> raavareBatch;
	private ArrayList<ProduktBatchDTO> produktBatch;

	public AdminMenu(VerticalPanel container, MainGUI mainGUI, int id, ArrayList<HashMap> operators){

		this.container = container;
		this.mainGUI = mainGUI;
		this.id = id;
		this.operators = operators;
	}

	public void adminMenu(){

		final Label adminHeader = new Label("Administator Menu");
		adminHeader.addStyleName("HeaderLabel");
		adminHeader.getElement().setAttribute("align", "center");
		final Button createOp = new Button("Opret ny operatør");
		final Button editOp = new Button("Ændre attributer for operatør");
		final Button inspectOp = new Button("Inspicer en operatør");
		final Button raavaremenu = new Button("Råvarer");
		final Button receptmenu = new Button("Recepter");
		final Button raavareBatchMenu = new Button("Råvarebatch");
		final Button produktBatchMenu = new Button("Produktbatch");
		final Button produktKompMenu = new Button("Produktbatchkompent");
		final Button receptKompMenu = new Button("Receptkomponent");
		final Button logout = new Button("Log ud");

		container.clear();
		container.setSpacing(9);
		container.add(adminHeader);
		container.add(createOp);
		container.add(editOp);
		container.add(inspectOp);
		container.add(raavaremenu);
		container.add(receptmenu);
		container.add(raavareBatchMenu);
		container.add(produktBatchMenu);
		container.add(produktKompMenu);
		container.add(receptKompMenu);
		container.add(logout);

		createOp.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				createOp();
			}
		});
		editOp.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				editOp();
			}
		});
		inspectOp.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.serviceImpl.getOperators();
			}
		});
		raavaremenu.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.serviceImpl.getRaavare();
			}
		});
		receptmenu.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.serviceImpl.getRecept();
			}
		});
		raavareBatchMenu.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.serviceImpl.getRaavareBatch();
			}
		});
		produktBatchMenu.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.serviceImpl.getProduktBatch();
			}
		});
		produktKompMenu.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.serviceImpl.getProduktKomp();;
			}
		});		
		receptKompMenu.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.serviceImpl.getReceptKomp();;
			}
		});
		logout.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.loginScreen();
			}

		});
	}
	private void inspectOp() {
		final Label inspectHeader = new Label("Inspicér en operatør");
		final Label oper = new Label("");
		final Button inspect = new Button("Inspicér");
		final Button back = new Button("<- Tilbage");
		final TextBox lb = new TextBox();
		container.clear();
		container.setSpacing(9);
		container.add(inspectHeader);
		container.add(oper);
		container.add(lb);
		container.add(inspect);
		container.add(back);

		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				adminMenu();
			}
		});
		inspect.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				int answer = Integer.parseInt(lb.getText());
				boolean found = false;
				for (int i = 0; i < operators.size(); i++){
					if (Integer.parseInt((String) operators.get(i).get("ID")) == answer){
						oper.setText((String) operators.get(i).get("Username") + " - " + (String) operators.get(i).get("cpr") + " - " + (String) operators.get(i).get("Password") + " - " + (String) operators.get(i).get("Initials")
								+ " - " + (String) operators.get(i).get("AdminStatus"));
						found = true;
					} 
				}				
				if (!found){
					oper.setText("Ingen operatør med ID: " + answer + " blev fundet. Indtast venligst et gyldigt ID.");
				}		
			}
		});
		lb.addKeyDownHandler(new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER){
					int answer = Integer.parseInt(lb.getText());
					boolean found = false;
					for (int i = 0; i < operators.size(); i++){
						if (Integer.parseInt((String) operators.get(i).get("ID")) == answer){
							oper.setText((String) operators.get(i).get("Username") + " - " + (String) operators.get(i).get("cpr") + " - " + (String) operators.get(i).get("Password") + " - " + (String) operators.get(i).get("Initials")
									+ " - " + (String) operators.get(i).get("AdminStatus"));
							found = true;
						} 
					}				
					if (!found){
						oper.setText("Ingen operatør med ID: " + answer + " blev fundet. Indtast venligst et gyldigt ID.");
					}		
				}				
			}	
		});    
	}
	private void editOp() {
		final Label editHeader = new Label("Rediger en operatør");
		editHeader.addStyleName("HeaderLabel");
		final Label id = new Label("ID for brugeren du vil redigere: ");
		final TextBox idText = new TextBox();
		final Label username = new Label("Nyt brugernavn: ");
		final TextBox userText = new TextBox();
		final Label password = new Label("Ny adgangskode: ");
		final TextBox passText = new TextBox();
		final Label cpr = new Label("Nyt cpr-nummer: ");
		final TextBox cprText = new TextBox();
		final Label ini = new Label("Nye initialer: ");
		final TextBox iniText = new TextBox();
		final Label adminLabel = new Label("Vælg ny bruger type:");
		final ListBox lb = new ListBox();
		lb.addItem("Admin");
		lb.addItem("Operatør");
		lb.addItem("Farmaceut");
		lb.addItem("Værksfører");
		final Button submit = new Button("Opdater");
		final Button back = new Button("<- Tilbage");
		container.clear();
		container.setSpacing(9);
		container.add(editHeader);
		container.add(id);
		container.add(idText);
		container.add(username);
		container.add(userText);
		container.add(password);
		container.add(passText);
		container.add(ini);
		container.add(iniText);
		container.add(cpr);
		container.add(cprText);
		container.add(adminLabel);
		container.add(lb);
		container.add(submit);
		container.add(back);

		submit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				boolean exists = false;
				for (int i = 0; i < operators.size(); i++){
					if (operators.get(i).get("ID") == idText.getText()){
						exists = true;
					}
				}
				if (lb.getSelectedItemText().equals("Admin")){
					admin = 1;
				} else if(lb.getSelectedItemText().equals("Operatør")){
					admin = 2;
				} else if(lb.getSelectedItemText().equals("Farmaceut")){
					admin = 3;
				}else if(lb.getSelectedItemText().equals("Værksfører")){
					admin = 4;
				}else {
					Window.alert("Noget gik galt ved tjek af bruger status");
				}
				if (!(exists)){
					Window.alert("Indtast venligst et gyldigt ID for en eksisterende operatør");
				} else if (cprText.getText().length() != 11){
					Window.alert("Dit cpr-nummer skal være 11 karakterer langt");
				} else if(cprText.getText().charAt(7) == '-'){
					Window.alert("Cpr-nummeret skal være indskrevet på korrekt form. Ek.s: 112233-4444");
				} else if (iniText.getText().length() > 3 || iniText.getText().length() < 2){
					Window.alert("Dine initialer skal være mellem 2 og 3 karakterer");
				}else if(iniText.getText().matches("[a-zA-Z ]*\\d+.*")){
					Window.alert("Dine initialer må ikke indeholde tal");
				} else if (idText.getText().isEmpty()){
					Window.alert("Du skal indtaste ID'et for den bruger du vil redigere");
				} else if (userText.getText().isEmpty()){
					Window.alert("Brugernavnet kan ikke være tomt");
				} else if (passText.getText().isEmpty()){
					Window.alert("Adgangskoden kan ikke være tom");
				} else if (passText.getText().length()<6){
					Window.alert("Adgangskoden skal bestå af mindst 6 cifre");
				} else{
					mainGUI.serviceImpl.updateOp(Integer.parseInt(idText.getText()), userText.getText(), iniText.getText(), cprText.getText(), passText.getText(), admin);
				}
			}
		});

		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				adminMenu();
			}
		});
	}
	private void createOp() {
		final Label headerLabel = new Label("Opret en bruger");
		headerLabel.addStyleName("HeaderLabel");
		final Label failure = new Label("");
		final Button submitButton = new Button("Opret");
		final TextBox username = new TextBox();
		final Label usernameLabel = new Label("Navn: ");
		final TextBox iniField = new TextBox();
		final Label iniLabel = new Label("Initialer: ");
		final TextBox cprField = new TextBox();
		final Label cprLabel = new Label("Cpr-nummer: ");
		final TextBox pwField = new TextBox();
		final Label pwLabel = new Label("Adgangskode: ");
		final Label adminLabel = new Label("Vælg bruger type:");
		final ListBox lb = new ListBox();
		lb.addItem("Admin");
		lb.addItem("Operatør");
		lb.addItem("Farmaceut");
		lb.addItem("Værksfører");
		final Button back = new Button("<- Tilbage");
		container.clear();
		container.setSpacing(9);
		container.add(headerLabel);
		container.add(failure);
		container.add(usernameLabel);
		container.add(username);
		container.add(pwLabel);
		container.add(pwField);
		container.add(iniLabel);
		container.add(iniField);
		container.add(cprLabel);
		container.add(cprField);
		container.add(adminLabel);
		container.add(lb);
		container.add(submitButton);
		container.add(back);

		// We can add style names to widgets
		submitButton.addStyleName("sendButton");
		submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				name = username.getText();
				ini = iniField.getText();
				cpr = cprField.getText();
				password = pwField.getText();
				if (lb.getSelectedItemText().equals("Admin")){
					admin = 1;
				} else if(lb.getSelectedItemText().equals("Operatør")){
					admin = 2;
				} else if(lb.getSelectedItemText().equals("Farmaceut")){
					admin = 3;
				}else if(lb.getSelectedItemText().equals("Værksfører")){
					admin = 4;
				}else {
					Window.alert("Noget gik galt ved tjek for bruger status");
				}
				if (cpr.length() != 11){
					Window.alert("Dit cpr-nummer skal være 11 karakterer langt");
				} else if(cpr.charAt(7) == '-'){
					Window.alert("Cpr-nummeret skal være indskrevet på korrekt form. Ek.s: 112233-4444");
				}else if (ini.length() > 3 || ini.length() < 2){
					Window.alert("Dine initialer skal være mellem 2 og 3 karakterer");
				}else if(ini.matches("[a-zA-Z ]*\\d+.*")){
					Window.alert("Dine initialer må ikke indeholde tal");
				}else if (username.getText().isEmpty()){
					Window.alert("Brugernavnet kan ikke være tomt");
				}else if (pwField.getText().isEmpty()){
					Window.alert("Adgangskoden kan ikke være tom");
				}else if(pwField.getText().length()<6){
					Window.alert("Adgangskoden skal bestå af mindst 6 cifre");
				}else{
					mainGUI.serviceImpl.createOp(name, ini, cpr, password, admin);
					adminMenu();						
				}
			}
		});
		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				adminMenu();
			}
		});

		// Focus the cursor on the name field when the app loads
		username.setFocus(true);
		username.selectAll();
	}
	public void updateRaavareBatch(ArrayList<RaavareBatchDTO> raavareBatch){
		this.raavareBatch = raavareBatch;
		RaavareBatchMenu raavareBatchMenu = new RaavareBatchMenu(container, raavareBatch, 1, mainGUI);
		raavareBatchMenu.raavareBatch();
	}
	public void updateProduktBatch(ArrayList<ProduktBatchDTO> produktBatch){
		this.produktBatch = produktBatch;
		ProduktBatchMenu produktBatchMenu = new ProduktBatchMenu(container, produktBatch, 1, mainGUI);
		produktBatchMenu.produktBatch();
	}
	public void updateOperators(ArrayList<HashMap> operators){
		this.operators = operators;
		inspectOp();
	}
	public void updateRaavare(ArrayList<RaavareDTO> raavare){
		this.raavare = raavare;
		RaavareMenu raavareMenu = new RaavareMenu(container, raavare, 1, mainGUI);
		raavareMenu.raavare();
	}
	public void updateRecept(ArrayList<ReceptDTO> recept){
		this.recept = recept;
		ReceptMenu receptMenu = new ReceptMenu(container, recept, 1, mainGUI);
		receptMenu.recept();
	}
	public void updateProduktKomp(ArrayList<ProduktBatchKomponentDTO> produktKomp){
		ProduktKompMenu produktKompMenu = new ProduktKompMenu(container, produktKomp, 1, mainGUI);
		produktKompMenu.produktKomp();
	}
	public void updateReceptKomp(ArrayList<ReceptKomponentDTO> receptKomp){
		ReceptKompMenu receptKompMenu = new ReceptKompMenu(container, receptKomp, 1, mainGUI);
		receptKompMenu.receptKomp();
	}
}
