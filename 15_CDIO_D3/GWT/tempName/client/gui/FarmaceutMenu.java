package tempName.client.gui;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import tempName.shared.dto.ProduktBatchDTO;
import tempName.shared.dto.ProduktBatchKomponentDTO;
import tempName.shared.dto.RaavareBatchDTO;
import tempName.shared.dto.RaavareDTO;
import tempName.shared.dto.ReceptDTO;
import tempName.shared.dto.ReceptKomponentDTO;

public class FarmaceutMenu{
	private VerticalPanel container;
	private MainGUI mainGUI;

	public FarmaceutMenu(VerticalPanel container, MainGUI mainGUI){
		this.container = container;
		this.mainGUI = mainGUI;
	}
	
	public void farmaMenu(){
		final Label farmaHeader = new Label("Farmaceut Menu");
		farmaHeader.addStyleName("HeaderLabel");
		farmaHeader.getElement().setAttribute("align", "center");
		final Button raavaremenu = new Button("Råvarer");
		final Button receptmenu = new Button("Recepter");
		final Button raavareBatchMenu = new Button("Råvarebatch");
		final Button produktBatchMenu = new Button("Produktbatch");
		final Button produktKompMenu = new Button("Produktbatchkomponent");
		final Button receptKompMenu = new Button("Receptkomponent");
		final Button logout = new Button("Log ud");
		container.clear();
		container.setSpacing(9);
		container.add(farmaHeader);
		container.add(raavaremenu);
		container.add(receptmenu);
		container.add(raavareBatchMenu);
		container.add(produktBatchMenu);
		container.add(produktKompMenu);
		container.add(receptKompMenu);
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
		produktKompMenu.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.serviceImpl.getProduktKomp();
			}
		});
		receptKompMenu.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.serviceImpl.getReceptKomp();
			}
		});
		logout.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.loginScreen();
			}

		});
	}
	public void updateRaavareBatch(ArrayList<RaavareBatchDTO> raavareBatch){
		RaavareBatchMenu raavareBatchMenu = new RaavareBatchMenu(container, raavareBatch, 3, mainGUI);
		raavareBatchMenu.raavareBatch();
	}
	public void updateProduktBatch(ArrayList<ProduktBatchDTO> produktBatch){
		ProduktBatchMenu produktBatchMenu = new ProduktBatchMenu(container, produktBatch, 3, mainGUI);
		produktBatchMenu.produktBatch();
	}

	public void updateRaavare(ArrayList<RaavareDTO> raavare){
		RaavareMenu raavareMenu = new RaavareMenu(container, raavare, 3, mainGUI);
		raavareMenu.raavare();
	}
	public void updateRecept(ArrayList<ReceptDTO> recept){
		ReceptMenu receptMenu = new ReceptMenu(container, recept, 3, mainGUI);
		receptMenu.recept();
	}
	public void updateProduktKomp(ArrayList<ProduktBatchKomponentDTO> produktKomp){
		ProduktKompMenu produktKompMenu = new ProduktKompMenu(container, produktKomp, 3, mainGUI);
		produktKompMenu.produktKomp();
	}
	public void updateReceptKomp(ArrayList<ReceptKomponentDTO> receptKomp){
		ReceptKompMenu receptKompMenu = new ReceptKompMenu(container, receptKomp, 3, mainGUI);
		receptKompMenu.receptKomp();
	}
}
