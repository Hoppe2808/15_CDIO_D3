package tempName.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import tempName.server.data.dto.WeightDTO;

public class VaerksfoererMenu implements FarmaAdminInt {
	
	private VerticalPanel container;
	private ArrayList<WeightDTO> measurements;
	private MainGUI mainGUI;
	
	public VaerksfoererMenu(VerticalPanel container, ArrayList<WeightDTO> measurements, MainGUI mainGUI){
		
		this.container = container;
		this.measurements = measurements;
		this.mainGUI = mainGUI;
		
	}
	
	public void foremanMenu(){
		final Label ForemanHeader = new Label("Foreman Menu");
		final Button measurements = new Button("Check measurements");
		final Button logout = new Button("Logout");
		container.clear();
		container.setSpacing(9);
		container.add(measurements);
		container.add(logout);

		measurements.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				measurements();
			}
		});
		logout.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.loginScreen2();
			}

		});
	}

	@Override
	public void measurements() {
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
				foremanMenu();
			}
		});
	}

}
