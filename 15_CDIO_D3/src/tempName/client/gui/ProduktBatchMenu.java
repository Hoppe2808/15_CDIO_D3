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

import tempName.shared.dto.ProduktBatchDTO;

public class ProduktBatchMenu {
	private VerticalPanel container;
	private int prevMenu;
	private ArrayList<ProduktBatchDTO> produktBatch;
	private MainGUI mainGUI;
	private Label headerLabel = new Label();

	public ProduktBatchMenu(VerticalPanel container, ArrayList<ProduktBatchDTO> produktBatch, int prevMenu, MainGUI mainGUI){
		this.container = container;
		this.prevMenu = prevMenu;
		this.produktBatch = produktBatch;
		this.mainGUI = mainGUI;
	}

	public void produktBatch(){
		headerLabel.setText("ProduktBatch");;
		headerLabel.addStyleName("HeaderLabel");
		final Button inspectButton = new Button("Se Produktbatches");
		final Button addButton = new Button("Tilføj Produktbatch");
		final Button updateButton = new Button("Rediger Produktbatch");
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
				addProduktBatch();
			}
		});
		updateButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				updateProduktBatch();
			}
		});

		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.adminCheck(prevMenu);
			}
		});
	}

	public void inspectBatch() {
		CellTable<ProduktBatchDTO> table = new CellTable<ProduktBatchDTO>();
		TextColumn<ProduktBatchDTO> rbID = new TextColumn<ProduktBatchDTO>(){

			@Override
			public String getValue(ProduktBatchDTO object) {
				return Integer.toString(object.getPbId());
			}

		};
		TextColumn<ProduktBatchDTO> rID = new TextColumn<ProduktBatchDTO>(){

			@Override
			public String getValue(ProduktBatchDTO object) {
				return Integer.toString(object.getStatus());
			}

		};
		TextColumn<ProduktBatchDTO> maengde = new TextColumn<ProduktBatchDTO>(){

			@Override
			public String getValue(ProduktBatchDTO object) {
				return Double.toString(object.getReceptId());
			}

		};

		table.addColumn(rbID, "ProduktBatch ID");
		table.addColumn(rID, "Status");
		table.addColumn(maengde, "Recept ID");

		ListDataProvider<ProduktBatchDTO> dataProvider = new ListDataProvider<ProduktBatchDTO>();

		dataProvider.addDataDisplay(table);

		List<ProduktBatchDTO> list = new ArrayList<ProduktBatchDTO>();
		list = dataProvider.getList();
		for (ProduktBatchDTO mm : produktBatch) {
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
				produktBatch();
			}
		});
	}
	public void addProduktBatch(){
		headerLabel.setText("Tilføj ny ProduktBatch");
		final Label rIDLbl = new Label("Indtast status for produktbatchen:");
		final TextBox rID = new TextBox();
		final Label maengdeLbl = new Label("Indtast recept ID:");
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
					mainGUI.serviceImpl.addProduktBatch(Integer.parseInt(rID.getText()), Integer.parseInt(maengde.getText()));
					mainGUI.adminCheck(prevMenu);
				}catch (NumberFormatException e){
					Window.alert("Venligst udfyld felterne med tal");
				}
			}
		});

		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				produktBatch();
			}
		});
	}
	public void updateProduktBatch(){
		headerLabel.setText("Opdater ProduktBatch");
		final Label idLabel = new Label("Vælg ID for produktbatchen:");
		final ListBox lb = new ListBox();
		final Label statusLabel = new Label("Indtast status:");
		final TextBox status = new TextBox();
		final Label receptLabel = new Label("Indtast recept ID:");
		final TextBox receptID = new TextBox();
		final Button submit = new Button("Opdater");
		final Button delete = new Button("Slet");
		final Button back = new Button("<- Tilbage");
		for (int i = 0; i < produktBatch.size(); i++){
			lb.addItem(Integer.toString(produktBatch.get(i).getPbId()));
		}
		lb.setVisibleItemCount(1);
		container.clear();
		container.add(headerLabel);
		container.add(idLabel);
		container.add(lb);
		container.add(statusLabel);
		container.add(status);
		container.add(receptLabel);
		container.add(receptID);
		container.add(submit);
		container.add(delete);
		container.add(back);


		status.setText(Integer.toString(produktBatch.get(0).getStatus()));
		receptID.setText(Double.toString(produktBatch.get(0).getReceptId()));
		lb.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				for (int i = 0; i < produktBatch.size(); i++){
					String rbID = Integer.toString(produktBatch.get(i).getPbId());
					if (rbID.equals(lb.getSelectedItemText())){
						status.setText(Integer.toString(produktBatch.get(i).getStatus()));
						receptID.setText(Double.toString(produktBatch.get(i).getReceptId()));
					}
				}
			}
		});

		delete.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				for (int i = 0; i < produktBatch.size(); i++){
					String rbID = Integer.toString(produktBatch.get(i).getPbId());
					if (rbID.equals(lb.getSelectedItemText())){
						mainGUI.serviceImpl.deleteProduktBatch(Integer.parseInt(rbID));
					}
				}
				mainGUI.adminCheck(prevMenu);
			}
		});

		submit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				for (int i = 0; i < produktBatch.size(); i++){
					String rbID = Integer.toString(produktBatch.get(i).getPbId());
					if (rbID.equals(lb.getSelectedItemText())){
						try{
							mainGUI.serviceImpl.updateProduktBatch(Integer.parseInt(rbID), Integer.parseInt(status.getText()), Integer.parseInt(receptID.getText()));
						}catch (NumberFormatException e){
							Window.alert("Venligst udfyld felterne med tal");
						}
					}
				}
				mainGUI.adminCheck(prevMenu);
			}
		});

		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				produktBatch();
			}
		});
	}
}
