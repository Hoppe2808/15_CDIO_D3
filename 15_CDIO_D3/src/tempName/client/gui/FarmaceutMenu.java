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

import tempName.server.data.dto.RaavareDTO;
import tempName.server.data.dto.ReceptDTO;
import tempName.server.data.dto.WeightDTO;

public class FarmaceutMenu implements FarmaAdminInt{
	private VerticalPanel container;
	private ArrayList<WeightDTO> measurements;
	private ArrayList<RaavareDTO> raavare;
	private ArrayList<ReceptDTO> recept;
	private MainGUI mainGUI;

	public FarmaceutMenu(VerticalPanel container, ArrayList<WeightDTO> measurements, MainGUI mainGUI){
		this.container = container;
		this.measurements = measurements;
		this.mainGUI = mainGUI;
	}
	
	public void farmaMenu(){
		final Label farmaHeader = new Label("Farmaceut Menu");
		final Button measurements = new Button("Check measurements");
		final Button logout = new Button("Logout");
		container.clear();
		container.setSpacing(9);
		container.add(farmaHeader);
		container.add(measurements);
		container.add(logout);

		measurements.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
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
		logout.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.loginScreen2();
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
				return Integer.toString(object.getRec_Id());
			}

		};
		TextColumn<ReceptDTO> rName = new TextColumn<ReceptDTO>(){

			@Override
			public String getValue(ReceptDTO object) {
				return object.getRec_navn();
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
	public void updateRaavare(ArrayList<RaavareDTO> raavare){
		this.raavare = raavare;
	}
	public void updateRecept(ArrayList<ReceptDTO> recept){
		this.recept = recept;
	}

}
