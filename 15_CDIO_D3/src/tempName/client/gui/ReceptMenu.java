package tempName.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import tempName.shared.dto.RaavareDTO;
import tempName.shared.dto.ReceptDTO;

public class ReceptMenu {
	
	private VerticalPanel container;
	private int prevMenu;
	private ArrayList<ReceptDTO> recept;
	private MainGUI mainGUI;
	private Label headerLabel = new Label();

	public ReceptMenu(VerticalPanel container, ArrayList<ReceptDTO> recept, int prevMenu, MainGUI mainGUI){
		this.container = container;
		this.prevMenu = prevMenu;
		this.recept = recept;
		this.mainGUI = mainGUI;

}
	public void recept(){
		headerLabel.setText("Recept");;
		headerLabel.addStyleName("HeaderLabel");
		final Button inspectButton = new Button("Se Recept");
		final Button addButton = new Button("Tilføj Recept");
		final Button updateButton = new Button("Rediger Recept");
		final Button back = new Button("<- Back");
		container.clear();
		container.add(headerLabel);
		container.add(inspectButton);
		container.add(addButton);
		container.add(updateButton);
		container.add(back);
		
		inspectButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				inspectRecept();
			}
		});
		
		addButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addRecept();
			}
		});
		updateButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				updateRecept();
			}
		});
		
		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.adminCheck(prevMenu);
			}
		});
	}
	
	public void inspectRecept() {
		CellTable<ReceptDTO> table = new CellTable<ReceptDTO>();
		TextColumn<ReceptDTO> receptId = new TextColumn<ReceptDTO>(){

			@Override
			public String getValue(ReceptDTO object) {
				return Integer.toString(object.getReceptId());
			}

		};
		TextColumn<ReceptDTO> receptName = new TextColumn<ReceptDTO>(){

			@Override
			public String getValue(ReceptDTO object) {
				return object.getReceptName();
			}

		};
	

		table.addColumn(receptId, "Recept Id");
		table.addColumn(receptName, "Recept Name");


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
				recept();
			}
		});
	}
	public void addRecept(){
		headerLabel.setText("Add new Recept");
		final Label navnLbl = new Label("Please enter recept name:");
		final TextBox navn = new TextBox();
		final Button submit = new Button("Create");
		final Button back = new Button("<- back");
		final Label message = new Label();
		container.clear();
		container.add(headerLabel);
		container.add(navnLbl);
		container.add(navn);
		container.add(submit);
		container.add(back);
		container.add(message);
		
		
		submit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.serviceImpl.addRecept(navn.getText();
				mainGUI.adminCheck(prevMenu);
				message.setText("Recept added");
			}
		});
		
		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				recept();
			}
		});
	}
	public void updateRecept(){
		headerLabel.setText("Update Recept");
		final Label idLabel = new Label("Enter ID for Recept:");
		final ListBox lb = new ListBox();
		final Label navnLabel = new Label("Enter Recept Name:");
		final TextBox navn = new TextBox();
		final Button submit = new Button("Update");
		final Button delete = new Button("Delete");
		final Button back = new Button("<- Back");
		for (int i = 0; i < recept.size(); i++){
			lb.addItem(recept.get(i).getReceptName());
		}
		lb.setVisibleItemCount(1);
		container.clear();
		container.add(headerLabel);
		container.add(idLabel);
		container.add(lb);
		container.add(navnLabel);
		container.add(navn);
		container.add(submit);
		container.add(delete);
		container.add(back);
		
		
	
		delete.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				for (int i = 0; i < recept.size(); i++){
					if (recept.get(i).getReceptName().equals(lb.getSelectedItemText())){
						mainGUI.serviceImpl.deleteRecept(i+1);
					}
				}
				mainGUI.adminCheck(prevMenu);
			}
		});
		
		submit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				for (int i = 0; i < recept.size(); i++){
					if (recept.get(i).getReceptName().equals(lb.getSelectedItemText())){
						mainGUI.serviceImpl.updateRecept(i+1, navn.getText());
					}
				}
				mainGUI.adminCheck(prevMenu);
			}
		});
		
		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				recept();
			}
		});
	}
}

	}
