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
import tempName.shared.dto.RaavareBatchDTO;
import tempName.shared.dto.RaavareDTO;
import tempName.shared.dto.ReceptDTO;

public class AdminMenu implements FarmaAdminInt{

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
		final Button raavareBatchMenu = new Button("Råvare batch");
		final Button produktBatchMenu = new Button("Produkt batch");
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
		final Label id = new Label("Bruger ID: ");
		final TextBox idText = new TextBox();
		final Label username = new Label("Brugernavn: ");
		final TextBox userText = new TextBox();
		final Label password = new Label("Adgangskode: ");
		final TextBox passText = new TextBox();
		final Label cpr = new Label("Cpr-nummer: ");
		final TextBox cprText = new TextBox();
		final Label ini = new Label("Initialer: ");
		final TextBox iniText = new TextBox();
		final Label adminLabel = new Label("Vælg bruger type:");
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
				if (cprText.getText().length() != 10){
					Window.alert("Dit cpr-nummer skal være 10 karakterer langt");
				} else if (iniText.getText().length() > 3 && iniText.getText().length() < 2){
					Window.alert("Dine initialer skal være mellem 2 og 3 karakterer");
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
				if(ini.length() > 3){
					failure.setText("Initialerne må maks være 3 karaktere langt");
				}else{
					if(cpr.length()!= 10){
						failure.setText("Et cpr-nummer er 10 karakterer langt");
					}else{
						mainGUI.serviceImpl.createOp(name, ini, cpr, password, admin);
						adminMenu();						
					}
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

	@Override
	public void recept() {
		CellTable<ReceptDTO> table = new CellTable<ReceptDTO>();
		TextColumn<ReceptDTO> rID = new TextColumn<ReceptDTO>(){

			@Override
			public String getValue(ReceptDTO object) {
				return Integer.toString(object.getReceptId());
			}

		};
		TextColumn<ReceptDTO> rName = new TextColumn<ReceptDTO>(){

			@Override
			public String getValue(ReceptDTO object) {
				return object.getReceptName();
			}

		};

		table.addColumn(rID, "Raavare ID");
		table.addColumn(rName, "Raavare Name");

		ListDataProvider<ReceptDTO> dataProvider = new ListDataProvider<ReceptDTO>();

		dataProvider.addDataDisplay(table);

		List<ReceptDTO> list = new ArrayList<ReceptDTO>();
		list = dataProvider.getList();
		for (ReceptDTO mm : recept) {
			list.add(mm);
		}

		final Button back = new Button("<- Back");
		container.clear();
		container.setSpacing(9);
		container.add(table);
		container.add(back);

		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				adminMenu();
			}
		});
	}
	@Override
	public void raavareBatch() {
		CellTable<RaavareBatchDTO> table = new CellTable<RaavareBatchDTO>();
		TextColumn<RaavareBatchDTO> rbID = new TextColumn<RaavareBatchDTO>(){

			@Override
			public String getValue(RaavareBatchDTO object) {
				return Integer.toString(object.getRbId());
			}

		};
		TextColumn<RaavareBatchDTO> rID = new TextColumn<RaavareBatchDTO>(){

			@Override
			public String getValue(RaavareBatchDTO object) {
				return Integer.toString(object.getRaavareId());
			}

		};
		TextColumn<RaavareBatchDTO> maengde = new TextColumn<RaavareBatchDTO>(){

			@Override
			public String getValue(RaavareBatchDTO object) {
				return Double.toString(object.getMaengde());
			}

		};

		table.addColumn(rbID, "RaavareBatch ID");
		table.addColumn(rID, "Raavare ID");
		table.addColumn(maengde, "Mængde");

		ListDataProvider<RaavareBatchDTO> dataProvider = new ListDataProvider<RaavareBatchDTO>();

		dataProvider.addDataDisplay(table);

		List<RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
		list = dataProvider.getList();
		for (RaavareBatchDTO mm : raavareBatch) {
			list.add(mm);
		}

		final Button back = new Button("<- Back");
		container.clear();
		container.setSpacing(9);
		container.add(table);
		container.add(back);

		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				adminMenu();
			}
		});
	}
	@Override
	public void produktBatch() {
		CellTable<ProduktBatchDTO> table = new CellTable<ProduktBatchDTO>();
		TextColumn<ProduktBatchDTO> pbID = new TextColumn<ProduktBatchDTO>(){

			@Override
			public String getValue(ProduktBatchDTO object) {
				return Integer.toString(object.getPbId());
			}

		};
		TextColumn<ProduktBatchDTO> status = new TextColumn<ProduktBatchDTO>(){

			@Override
			public String getValue(ProduktBatchDTO object) {
				return Integer.toString(object.getStatus());
			}

		};
		TextColumn<ProduktBatchDTO> rID = new TextColumn<ProduktBatchDTO>(){

			@Override
			public String getValue(ProduktBatchDTO object) {
				return Integer.toString(object.getReceptId());
			}

		};

		table.addColumn(pbID, "ProduktBatch ID");
		table.addColumn(status, "Status");
		table.addColumn(rID, "Recept ID");

		ListDataProvider<ProduktBatchDTO> dataProvider = new ListDataProvider<ProduktBatchDTO>();

		dataProvider.addDataDisplay(table);

		List<ProduktBatchDTO> list = new ArrayList<ProduktBatchDTO>();
		list = dataProvider.getList();
		for (ProduktBatchDTO mm : produktBatch) {
			list.add(mm);
		}

		final Button back = new Button("<- Tilbage");
		container.clear();
		container.setSpacing(9);
		container.add(table);
		container.add(back);

		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				adminMenu();
			}
		});
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
}
