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

public class FarmaceutMenu implements FarmaAdminInt{
	private VerticalPanel container;
	private ArrayList<RaavareDTO> raavare;
	private ArrayList<ReceptDTO> recept;
	private ArrayList<RaavareBatchDTO> raavareBatch;
	private ArrayList<ProduktBatchDTO> produktBatch;
	private MainGUI mainGUI;

	public FarmaceutMenu(VerticalPanel container, MainGUI mainGUI){
		this.container = container;
		this.mainGUI = mainGUI;
	}
	
	public void farmaMenu(){
		final Label farmaHeader = new Label("Farmaceut Menu");
		farmaHeader.addStyleName("HeaderLabel");
		final Button raavaremenu = new Button("Check råvarer");
		final Button receptmenu = new Button("Check recepter");
		final Button raavareBatchMenu = new Button("Check råvare batch");
		final Button produktBatchMenu = new Button("Check produkt batch");
		final Button logout = new Button("Logout");
		container.clear();
		container.setSpacing(9);
		container.add(farmaHeader);
		container.add(raavaremenu);
		container.add(receptmenu);
		container.add(raavareBatchMenu);
		container.add(produktBatchMenu);
		container.add(logout);

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
				farmaMenu();
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
				farmaMenu();
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
				farmaMenu();
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
				farmaMenu();
			}
		});
		
	}
	public void updateRaavareBatch(ArrayList<RaavareBatchDTO> raavareBatch){
		this.raavareBatch = raavareBatch;
		raavareBatch();
	}
	public void updateProduktBatch(ArrayList<ProduktBatchDTO> produktBatch){
		this.produktBatch = produktBatch;
		produktBatch();
	}

	public void updateRaavare(ArrayList<RaavareDTO> raavare){
		this.raavare = raavare;
		raavare();
	}
	public void updateRecept(ArrayList<ReceptDTO> recept){
		this.recept = recept;
		recept();
	}
}
