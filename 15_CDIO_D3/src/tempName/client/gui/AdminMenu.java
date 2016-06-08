package tempName.client.gui;

import java.util.ArrayList;
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
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
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

		final Label adminHeader = new Label("Admin Menu");
		adminHeader.addStyleName("HeaderLabel");
		final Button createOp = new Button("Create new operator");
		final Button editOp = new Button("Change attributes of operator");
		final Button inspectOp = new Button("Inspect an operator");
		final Button raavaremenu = new Button("Check råvarer");
		final Button receptmenu = new Button("Check recepter");
		final Button raavareBatchMenu = new Button("Check råvare batch");
		final Button produktBatchMenu = new Button("Check produkt batch");
		final Button logout = new Button("Logout");
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
				inspectOp();
			}
		});
		raavaremenu.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				System.out.println("RMENU");
				mainGUI.serviceImpl.getRaavare();
				Timer t = new Timer() {
					@Override
					public void run() {
						raavare();

					}
				};
				t.schedule(100);
			}
		});
		receptmenu.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				System.out.println("REMENU");
				mainGUI.serviceImpl.getRecept();
				Timer t = new Timer() {
					@Override
					public void run() {
						recept();

					}
				};
				t.schedule(100);
			}
		});
		raavareBatchMenu.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				System.out.println("RBMENU");
				mainGUI.serviceImpl.getRaavareBatch();
				Timer t = new Timer() {
					@Override
					public void run() {
						raavareBatch();

					}
				};
				t.schedule(100);
			}
		});
		produktBatchMenu.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.serviceImpl.getProduktBatch();
				Timer t = new Timer() {
					@Override
					public void run() {
						produktBatch();

					}
				};
				t.schedule(100);
			}
		});
		logout.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.loginScreen();
			}

		});
	}
	private void inspectOp() {
		final Label inspectHeader = new Label("Inspect an operator");
		final Label oper = new Label("");
		final Button inspect = new Button("Inspect");
		final Button back = new Button("<- Back");
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
					oper.setText("No operator with Id: " + answer + " was found. Please enter a valid Operator Id.");
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
						oper.setText("No operator with Id: " + answer + " was found. Please enter a valid Operator Id.");
					}		
				}				
			}	
		});    
	}
	private void editOp() {
		final Label editHeader = new Label("Edit an operator");
		final Label id = new Label("User ID: ");
		final TextBox idText = new TextBox();
		final Label username = new Label("Username: ");
		final TextBox userText = new TextBox();
		final Label password = new Label("Password: ");
		final TextBox passText = new TextBox();
		final Label cpr = new Label("Cpr-number: ");
		final TextBox cprText = new TextBox();
		final Label ini = new Label("Initialer: ");
		final TextBox iniText = new TextBox();
		final RadioButton adminYes = new RadioButton("radioGroup", "Yes");
		final RadioButton adminNo = new RadioButton("radioGroup", "No");
		final Label adminLabel = new Label("Is it an admin? ");
		final Button submit = new Button("Submit");
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(3);
		panel.add(editHeader);
		panel.add(adminLabel);
		panel.add(adminYes);
		panel.add(adminNo);

		final Button back = new Button("<- Back");
		container.clear();
		container.setSpacing(9);
		container.add(id);
		container.add(idText);
		container.add(username);
		container.add(userText);
		container.add(ini);
		container.add(iniText);
		container.add(cpr);
		container.add(cprText);
		container.add(password);
		container.add(passText);
		container.add(panel);
		container.add(submit);
		container.add(back);

		submit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (adminYes.getValue()){
					admin = 1;
				} else if(adminNo.getValue()){
					admin = 2;
				} else {
					Window.alert("Something went wrong in checking for admin status");
				}
				try{
					if (cprText.getText().length() != 10){
						Window.alert("Dit cpr-nummer skal være 10 karakterer langt");
					} else if (iniText.getText().length() > 3 && iniText.getText().length() < 2){
						Window.alert("Dine initialer skal være mellem 2 og 3 karakterer");
					} else{
						mainGUI.serviceImpl.updateOp(Integer.parseInt(idText.getText()), userText.getText(), iniText.getText(), cprText.getText(), passText.getText(), admin);
						Window.alert("Operator updated");						
					}
				} catch(Exception e){
					Window.alert("Operator not updated");
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
		final Label failure = new Label("");
		final Button submitButton = new Button("Send");
		final TextBox username = new TextBox();
		final Label usernameLabel = new Label("Name: ");
		final TextBox iniField = new TextBox();
		final Label iniLabel = new Label("Initialer: ");
		final TextBox cprField = new TextBox();
		final Label cprLabel = new Label("Cpr-number: ");
		final TextBox pwField = new TextBox();
		final Label pwLabel = new Label("Password: ");
		final RadioButton adminYes = new RadioButton("radioGroup", "Yes");
		final RadioButton adminNo = new RadioButton("radioGroup", "No");
		final Label adminLabel = new Label("Is it an admin? ");
		final Button back = new Button("<- Back");
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(3);
		panel.add(adminYes);
		panel.add(adminNo);
		container.clear();
		container.setSpacing(9);

		container.add(failure);
		container.add(usernameLabel);
		container.add(username);
		container.add(iniLabel);
		container.add(iniField);
		container.add(cprLabel);
		container.add(cprField);
		container.add(pwLabel);
		container.add(pwField);
		container.add(adminLabel);
		container.add(panel);
		container.add(submitButton);
		container.add(back);

		// We can add style names to widgets
		submitButton.addStyleName("sendButton");

		adminYes.setValue(false);
		adminNo.setValue(true);

		submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				id = 0;
				name = username.getText();
				ini = iniField.getText();
				cpr = cprField.getText();
				password = pwField.getText();
				if (adminYes.getValue()){
					admin = 1;
				} else if(adminNo.getValue()){
					admin = 2;
				} else {
					Window.alert("Something went wrong in checking for admin status");
				}
				if(ini.length() > 3){
					failure.setText("Initialerne må maks være 3 karaktere langt");
				}else{
					if(cpr.length()!= 10){
						failure.setText("Et cpr-nummer er 10 karakterer langt");
					}else{

						mainGUI.serviceImpl.createOp(id, name, ini, cpr, password, admin);
						Window.alert("Succes!");
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
	public void raavare() {
		CellTable<RaavareDTO> table = new CellTable<RaavareDTO>();
		TextColumn<RaavareDTO> rID = new TextColumn<RaavareDTO>(){

			@Override
			public String getValue(RaavareDTO object) {
				return Integer.toString(object.getrID());
			}

		};
		TextColumn<RaavareDTO> rName = new TextColumn<RaavareDTO>(){

			@Override
			public String getValue(RaavareDTO object) {
				return object.getrName();
			}

		};
		TextColumn<RaavareDTO> deliverer = new TextColumn<RaavareDTO>(){

			@Override
			public String getValue(RaavareDTO object) {
				return object.getDeliverer();
			}

		};

		table.addColumn(rID, "Raavare ID");
		table.addColumn(rName, "Raavare Name");
		table.addColumn(deliverer, "Deliverer");

		ListDataProvider<RaavareDTO> dataProvider = new ListDataProvider<RaavareDTO>();

		dataProvider.addDataDisplay(table);

		List<RaavareDTO> list = new ArrayList<RaavareDTO>();
		list = dataProvider.getList();
		for (RaavareDTO mm : raavare) {
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
	public void updateRaavareBatch(ArrayList<RaavareBatchDTO> raavareBatch){
		this.raavareBatch = raavareBatch;
	}
	public void updateProduktBatch(ArrayList<ProduktBatchDTO> produktBatch){
		this.produktBatch = produktBatch;
	}
	public void updateOperators(ArrayList<HashMap> operators){
		this.operators = operators;
	}
	public void updateRaavare(ArrayList<RaavareDTO> raavare){
		this.raavare = raavare;
	}
	public void updateRecept(ArrayList<ReceptDTO> recept){
		this.recept = recept;
	}
}
