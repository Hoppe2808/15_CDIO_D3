package tempName.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import tempName.shared.dto.ProduktBatchDTO;
import tempName.shared.dto.RaavareBatchDTO;
import tempName.shared.dto.RaavareDTO;
import tempName.shared.dto.ReceptDTO;
import tempName.shared.dto.WeightDTO;

public class VaerksfoererMenu implements VaerkAdminInt{
	
	private VerticalPanel container;
	private ArrayList<RaavareBatchDTO> raavareBatch;
	private ArrayList<ProduktBatchDTO> produktBatch;
	private MainGUI mainGUI;
	
	public VaerksfoererMenu(VerticalPanel container, MainGUI mainGUI){
		
		this.container = container;
		this.mainGUI = mainGUI;
		
	}
	
	public void foremanMenu(){
		final Label foremanHeader = new Label("Foreman Menu");
		foremanHeader.addStyleName("HeaderLabel");
		foremanHeader.getElement().setAttribute("align", "center");
		final Button raavareMenu = new Button("Check råvarer");
		final Button produktBatchMenu = new Button("Check produktbatch");
		final Button logout = new Button("Logout");
		container.clear();
		container.setSpacing(9);
		container.add(foremanHeader);
		container.add(raavareMenu);
		container.add(produktBatchMenu);
		container.add(logout);

		raavareMenu.addClickHandler(new ClickHandler() {
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
				foremanMenu();
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
				foremanMenu();
			}
		});
		
	}
	public void updateRaavareBatch(ArrayList<RaavareBatchDTO> raavareBatch){
		this.raavareBatch = raavareBatch;
		RaavareBatchMenu raavareBatchMenu = new RaavareBatchMenu(container, raavareBatch, 4, mainGUI);
		raavareBatchMenu.raavareBatch();
	}
	public void updateProduktBatch(ArrayList<ProduktBatchDTO> produktBatch){
		this.produktBatch = produktBatch;
		ProduktBatchMenu produktBatchMenu = new ProduktBatchMenu(container, produktBatch, 4, mainGUI);
		produktBatchMenu.produktBatch();
	}

}
