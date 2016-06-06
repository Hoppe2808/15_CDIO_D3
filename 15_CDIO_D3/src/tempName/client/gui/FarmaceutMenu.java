package tempName.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.view.client.ListDataProvider;

import tempName.server.data.dto.WeightDTO;

public class FarmaceutMenu implements FarmaAdminInt{

	public FarmaceutMenu(){
		
	}
	
	public void farmaMenu(){
		
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
				farmaMenu();
			}
		});
	}
}
