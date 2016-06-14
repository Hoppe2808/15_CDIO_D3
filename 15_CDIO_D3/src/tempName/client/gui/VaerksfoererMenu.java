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

public class VaerksfoererMenu{
	
	private VerticalPanel container;
	private MainGUI mainGUI;
	
	public VaerksfoererMenu(VerticalPanel container, MainGUI mainGUI){
		
		this.container = container;
		this.mainGUI = mainGUI;
		
	}
	public void foremanMenu(){
		final Label foremanHeader = new Label("Værksfører Menu");
		foremanHeader.addStyleName("HeaderLabel");
		foremanHeader.getElement().setAttribute("align", "center");
		final Button raavareMenu = new Button("Råvarebatch");
		final Button produktBatchMenu = new Button("Produktbatch");
		final Button logout = new Button("Log ud");
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
	public void updateRaavareBatch(ArrayList<RaavareBatchDTO> raavareBatch){
		RaavareBatchMenu raavareBatchMenu = new RaavareBatchMenu(container, raavareBatch, 4, mainGUI);
		raavareBatchMenu.raavareBatch();
	}
	public void updateProduktBatch(ArrayList<ProduktBatchDTO> produktBatch){
		ProduktBatchMenu produktBatchMenu = new ProduktBatchMenu(container, produktBatch, 4, mainGUI);
		produktBatchMenu.produktBatch();
	}

}
