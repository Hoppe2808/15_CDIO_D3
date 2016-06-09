package tempName.client.gui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import tempName.shared.dto.RaavareBatchDTO;

public class RaavareBatchMenu {
	private VerticalPanel container;
	private int prevMenu;
	private ArrayList<RaavareBatchDTO> raavareBatch;
	private MainGUI mainGUI;
	private Label headerLabel = new Label();

	public RaavareBatchMenu(VerticalPanel container, ArrayList<RaavareBatchDTO> raavareBatch, int prevMenu, MainGUI mainGUI){
		this.container = container;
		this.prevMenu = prevMenu;
		this.raavareBatch = raavareBatch;
		this.mainGUI = mainGUI;
	}

	public void raavareBatch(){
		headerLabel.setText("RåvarerBatch");;
		headerLabel.addStyleName("HeaderLabel");
		final Button inspectButton = new Button("Se Råvarebatches");
		final Button addButton = new Button("Tilføj Råvarebatch");
		final Button updateButton = new Button("Rediger Råvarebatch");
		final Button back = new Button("<- Tilbage");
		container.clear();
		container.add(headerLabel);
		container.add(inspectButton);
		container.add(addButton);
		container.add(updateButton);
		container.add(back);

		inspectButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				inspectBatch();
			}
		});
		addButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addRaavareBatch();
			}
		});
		updateButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				updateRaavareBatch();
			}
		});

		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.adminCheck(prevMenu);
			}
		});
	}

	public void inspectBatch() {
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
		final ScrollPanel sPanel = new ScrollPanel();
		final Button back = new Button("<- Tilbage");
		table.setPageSize(500);
		sPanel.add(table);
		container.clear();
		container.setSpacing(9);
		container.add(sPanel);
		container.add(back);

		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				raavareBatch();
			}
		});
	}
	public void addRaavareBatch(){
		headerLabel.setText("Tilføj ny råvarebatch");
		final Label rIDLbl = new Label("Indtast ID for råvaren:");
		final TextBox rID = new TextBox();
		final Label maengdeLbl = new Label("Indtast mængden:");
		final TextBox maengde = new TextBox();
		final Button submit = new Button("Opret");
		final Button back = new Button("<- Tilbage");
		final Label message = new Label();
		container.clear();
		container.add(headerLabel);
		container.add(rIDLbl);
		container.add(rID);
		container.add(maengdeLbl);
		container.add(maengde);
		container.add(submit);
		container.add(back);
		container.add(message);


		submit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				try{
					mainGUI.serviceImpl.addRaavareBatch(Integer.parseInt(rID.getText()), Integer.parseInt(maengde.getText()));
					mainGUI.adminCheck(prevMenu);
				}catch (NumberFormatException e){
					Window.alert("Please enter numbers only");
				}
			}
		});

		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				raavareBatch();
			}
		});
	}
	public void updateRaavareBatch(){
		headerLabel.setText("Opdater råvare");
		final Label idLabel = new Label("Vælg ID for råvarebatchen:");
		final ListBox lb = new ListBox();
		final Label rIDLabel = new Label("Indtast ID for råvaren:");
		final TextBox rID = new TextBox();
		final Label maengdeLabel = new Label("Indtast mængden:");
		final TextBox maengde = new TextBox();
		final Button submit = new Button("Opdater");
		final Button delete = new Button("Slet");
		final Button back = new Button("<- Tilbage");
		for (int i = 0; i < raavareBatch.size(); i++){
			lb.addItem(Integer.toString(raavareBatch.get(i).getRbId()));
		}
		lb.setVisibleItemCount(1);
		container.clear();
		container.add(headerLabel);
		container.add(idLabel);
		container.add(lb);
		container.add(rIDLabel);
		container.add(rID);
		container.add(maengdeLabel);
		container.add(maengde);
		container.add(submit);
		container.add(delete);
		container.add(back);

		rID.setText(Integer.toString(raavareBatch.get(0).getRaavareId()));
		maengde.setText(Double.toString(raavareBatch.get(0).getMaengde()));
		lb.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				for (int i = 0; i < raavareBatch.size(); i++){
					String rbID = Integer.toString(raavareBatch.get(i).getRbId());
					if (rbID.equals(lb.getSelectedItemText())){
						rID.setText(Integer.toString(raavareBatch.get(i).getRaavareId()));
						maengde.setText(Double.toString(raavareBatch.get(i).getMaengde()));
					}
				}
			}
		});
		delete.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				for (int i = 0; i < raavareBatch.size(); i++){
					String rbID = Integer.toString(raavareBatch.get(i).getRbId());
					if (rbID.equals(lb.getSelectedItemText())){
						mainGUI.serviceImpl.deleteRaavareBatch(Integer.parseInt(rbID));
					}
				}
				mainGUI.adminCheck(prevMenu);
			}
		});
		submit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				for (int i = 0; i < raavareBatch.size(); i++){
					String rbID = Integer.toString(raavareBatch.get(i).getRbId());
					if (rbID.equals(lb.getSelectedItemText())){
						try{
							mainGUI.serviceImpl.updateRaavareBatch(Integer.parseInt(rbID), Integer.parseInt(rID.getText()), Integer.parseInt(maengde.getText()));
						}catch (NumberFormatException e){
							Window.alert("Please enter numbers only");
						}
					}
				}
				mainGUI.adminCheck(prevMenu);
			}
		});
		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				raavareBatch();
			}
		});
	}
}
