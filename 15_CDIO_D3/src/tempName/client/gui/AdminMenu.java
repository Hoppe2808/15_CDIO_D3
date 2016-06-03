package tempName.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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

import tempName.server.data.dto.WeightDTO;

public class AdminMenu {
	
	public AdminMenu(){
		
	}
	
	
	private void adminMenu(){

		final Label adminHeader = new Label("Admin Menu");
		final Button createOp = new Button("Create new operator");
		final Button editOp = new Button("Change attributes of operator");
		final Button inspectOp = new Button("Inspect an operator");
		final Button measurements = new Button("Check measurements");
		final Button logout = new Button("Logout");
		container.clear();
		container.setSpacing(9);
		container.add(createOp);
		container.add(editOp);
		container.add(inspectOp);
		container.add(measurements);
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
				serviceImpl.getOperators();
				inspectOp();
			}
		});
		measurements.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				serviceImpl.getMeasurements();
				Timer t = new Timer() {
					@Override
					public void run() {
						measurements();

					}
				};
				t.schedule(100);
			}
		});
		logout.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loginScreen2();
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
			@Override
			public void onClick(ClickEvent event) {
				int answer = Integer.parseInt(lb.getText());
				for (int i = 0; i < operators.size(); i++){
					if (Integer.parseInt((String) operators.get(i).get("ID")) == answer){
						oper.setText((String) operators.get(i).get("Username") + " - " + (String) operators.get(i).get("cpr") + " - " + (String) operators.get(i).get("Password") + " - " + (String) operators.get(i).get("Initials")
								+ " - " + (String) operators.get(i).get("AdminStatus"));
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
					admin = true;
				} else if(adminNo.getValue()){
					admin = false;
				} else {
					Window.alert("Something went wrong in checking for admin status");
				}
				try{
					if (cprText.getText().length() != 10){
						Window.alert("Dit cpr-nummer skal være 10 karakterer langt");
					} else if (iniText.getText().length() > 3 && iniText.getText().length() < 2){
						Window.alert("Dine initialer skal være mellem 2 og 3 karakterer");
					} else{
						serviceImpl.updateOp(Integer.parseInt(idText.getText()), userText.getText(), iniText.getText(), cprText.getText(), passText.getText(), admin);
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
					admin = true;
				} else if(adminNo.getValue()){
					admin = false;
				} else {
					Window.alert("Something went wrong in checking for admin status");
				}
				if(ini.length() > 3){
					failure.setText("Initialerne må maks være 3 karaktere langt");
				}else{
					if(cpr.length()!= 10){
						failure.setText("Et cpr-nummer er 10 karakterer langt");
					}else{

						serviceImpl.createOp(id, name, ini, cpr, password, admin);
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

	private void measurements() {
		CellTable<WeightDTO> table = new CellTable<WeightDTO>();
		TextColumn<WeightDTO> wID = new TextColumn<WeightDTO>(){

			@Override
			public String getValue(WeightDTO object) {
				return Integer.toString(object.getWID());
			}

		};
		TextColumn<WeightDTO> meas = new TextColumn<WeightDTO>(){

			@Override
			public String getValue(WeightDTO object) {
				return Double.toString(object.getMS());
			}

		};
		TextColumn<WeightDTO> oID = new TextColumn<WeightDTO>(){

			@Override
			public String getValue(WeightDTO object) {
				return Integer.toString(object.getopID());
			}

		};

		table.addColumn(wID, "Weight ID");
		table.addColumn(meas, "Measurements");
		table.addColumn(oID, "Operator ID");

		ListDataProvider<WeightDTO> dataProvider = new ListDataProvider<WeightDTO>();

		dataProvider.addDataDisplay(table);

		List<WeightDTO> list = new ArrayList<WeightDTO>();
		list = dataProvider.getList();
		for (WeightDTO mm : measurements) {
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
}
