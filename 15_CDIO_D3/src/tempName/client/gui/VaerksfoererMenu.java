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

import tempName.server.data.dto.RaavareBatchDTO;
import tempName.server.data.dto.RaavareDTO;
import tempName.server.data.dto.ReceptDTO;
import tempName.server.data.dto.WeightDTO;

public class VaerksfoererMenu implements VaerkAdminInt{
	
	private VerticalPanel container;
	private ArrayList<WeightDTO> measurements;
	private ArrayList<RaavareBatchDTO> raavareBatch;
	private MainGUI mainGUI;
	
	public VaerksfoererMenu(VerticalPanel container, ArrayList<WeightDTO> measurements, MainGUI mainGUI){
		
		this.container = container;
		this.measurements = measurements;
		this.mainGUI = mainGUI;
		
	}
	
	public void foremanMenu(){
		final Label foremanHeader = new Label("Foreman Menu");
		foremanHeader.addStyleName("HeaderLabel");
		final Button measurements = new Button("Check råvarer");
		final Button logout = new Button("Logout");
		container.clear();
		container.setSpacing(9);
		container.add(foremanHeader);
		container.add(measurements);
		container.add(logout);

		measurements.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
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
		logout.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.loginScreen2();
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
	public void updateRaavareBatch(ArrayList<RaavareBatchDTO> raavareBatch){
		this.raavareBatch = raavareBatch;
	}

	@Override
	public void produktBatch() {
		// TODO Auto-generated method stub
		
	}

}
