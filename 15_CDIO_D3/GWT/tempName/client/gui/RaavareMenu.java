package tempName.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import tempName.shared.dto.RaavareDTO;

public class RaavareMenu{
	private VerticalPanel container;
	private int prevMenu;
	private ArrayList<RaavareDTO> raavare;
	private MainGUI mainGUI;
	private Label headerLabel = new Label();
	private int id;

	public RaavareMenu(VerticalPanel container, ArrayList<RaavareDTO> raavare, int prevMenu, MainGUI mainGUI){
		this.container = container;
		this.prevMenu = prevMenu;
		this.raavare = raavare;
		this.mainGUI = mainGUI;
	}

	public void raavare(){
		headerLabel.setText("Råvarer");;
		headerLabel.addStyleName("HeaderLabel");
		final Button inspectButton = new Button("Se Råvarer");
		final Button addButton = new Button("Tilføj Råvare");
		final Button updateButton = new Button("Rediger Råvare");
		final Button back = new Button("<- Tilbage");
		container.clear();
		container.add(headerLabel);
		container.add(inspectButton);
		container.add(addButton);
		container.add(updateButton);
		container.add(back);
		
		inspectButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				inspectRaavare();
			}
		});
		addButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addRaavare();
			}
		});
		updateButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				updateRaavare();
			}
		});
		
		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.adminCheck(prevMenu);
			}
		});
	}
	
	public void inspectRaavare() {
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

		table.addColumn(rID, "Råvare ID");
		table.addColumn(rName, "Råvare Navn");
		table.addColumn(deliverer, "Leverandør");

		ListDataProvider<RaavareDTO> dataProvider = new ListDataProvider<RaavareDTO>();

		dataProvider.addDataDisplay(table);

		List<RaavareDTO> list = new ArrayList<RaavareDTO>();
		list = dataProvider.getList();
		for (RaavareDTO mm : raavare) {
			list.add(mm);
		}
		final ScrollPanel sPanel = new ScrollPanel();
		final Button back = new Button("<- Tilbage");
		table.setPageSize(500);
		sPanel.add(table);
		container.clear();
		container.setSpacing(9);
		container.add(table);
		container.add(back);

		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				raavare();
			}
		});
	}
	public void addRaavare(){
		headerLabel.setText("Tilføj ny råvare");
		final Label idLbl = new Label("Indtast din råvares ID:");
		final TextBox id = new TextBox();
		final Label navnLbl = new Label("Indtast navnet på råvaren:");
		final TextBox navn = new TextBox();
		final Label leveLbl = new Label("Indtast leverandøren på råvaren:");
		final TextBox leverandoer = new TextBox();
		final Button submit = new Button("Opret");
		final Button back = new Button("<- Tilbage");
		container.clear();
		container.add(headerLabel);
		container.add(idLbl);
		container.add(id);
		container.add(navnLbl);
		container.add(navn);
		container.add(leveLbl);
		container.add(leverandoer);
		container.add(submit);
		container.add(back);		
		
		submit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (navn.getText().isEmpty()){
					Window.alert("En råvare skal have et navn");
				} else if (leverandoer.getText().isEmpty()){
					Window.alert("En råvare skal have en leverandør");
				} else if (!(id.getText().matches("[a-zA-Z ]*\\d+.*"))){
					Window.alert("ID skal være et tal");
				} else{
					mainGUI.serviceImpl.addRaavare(Integer.parseInt(id.getText()), navn.getText(), leverandoer.getText());
					mainGUI.adminCheck(prevMenu);
				}
			}
		});
		
		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				raavare();
			}
		});
	}
	public void updateRaavare(){
		headerLabel.setText("Opdater råvare");
		final Label idLabel = new Label("Vælg råvare der skal redigeres:");
		final ListBox lb = new ListBox();
		final Label navnLabel = new Label("Indtast navn:");
		final TextBox navn = new TextBox();
		final Label leveLabel = new Label("Indtast leverandør:");
		final TextBox leverandoer = new TextBox();
		final Button submit = new Button("Opdater");
		final Button delete = new Button("Slet");
		final Button back = new Button("<- Tilbage");
		for (int i = 0; i < raavare.size(); i++){
			String mix = raavare.get(i).getrID() + " - " + raavare.get(i).getrName();
			lb.addItem(mix);
		}
		lb.setVisibleItemCount(1);
		container.clear();
		container.add(headerLabel);
		container.add(idLabel);
		container.add(lb);
		container.add(navnLabel);
		container.add(navn);
		container.add(leveLabel);
		container.add(leverandoer);
		container.add(submit);
		container.add(delete);
		container.add(back);
		
		
		navn.setText(lb.getSelectedItemText().substring(lb.getSelectedItemText().lastIndexOf(" ")+1));
		leverandoer.setText(raavare.get(0).getDeliverer());
		lb.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				for (int i = 0; i < raavare.size(); i++){
					String id = lb.getSelectedItemText().substring(0, lb.getSelectedItemText().indexOf(" "));
					navn.setText(lb.getSelectedItemText().substring(lb.getSelectedItemText().lastIndexOf(" ")+1));
					if (Integer.parseInt(id) == raavare.get(i).getrID()){
						leverandoer.setText(raavare.get(i).getDeliverer());
					}
				}
			}
		});
		
		delete.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				for (int i = 0; i < raavare.size(); i++){
					String id = lb.getSelectedItemText().substring(0, lb.getSelectedItemText().indexOf(" "));
					if (Integer.parseInt(id) == raavare.get(i).getrID()){
						mainGUI.serviceImpl.deleteRaavare(raavare.get(i).getrID());
					}
				}
				mainGUI.adminCheck(prevMenu);
			}
		});
		
		submit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (navn.getText().isEmpty()){
					Window.alert("En råvare skal have et navn");
				} else if (leverandoer.getText().isEmpty()){
					Window.alert("En råvare skal have en leverandør");
				} else{
				for (int i = 0; i < raavare.size(); i++){
					String id = lb.getSelectedItemText().substring(0, lb.getSelectedItemText().indexOf(" "));
					if (Integer.parseInt(id) == raavare.get(i).getrID()){
						mainGUI.serviceImpl.updateRaavare(raavare.get(i).getrID(), navn.getText(), leverandoer.getText());
					}
				}
				mainGUI.adminCheck(prevMenu);
				}
			}
		});
		
		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				raavare();
			}
		});
	}
}
