package tempName.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import tempName.shared.dto.ReceptKomponentDTO;
import tempName.shared.dto.ReceptKomponentDTO;

public class ReceptKompMenu {
	private VerticalPanel container;
	private int prevMenu;
	private ArrayList<ReceptKomponentDTO> receptKomponent;
	private MainGUI mainGUI;
	private Label headerLabel = new Label();

	public ReceptKompMenu(VerticalPanel container, ArrayList<ReceptKomponentDTO> receptKomponent, int prevMenu, MainGUI mainGUI){
		this.container = container;
		this.prevMenu = prevMenu;
		this.receptKomponent = receptKomponent;
		this.mainGUI = mainGUI;
	}
	public void receptKomp(){
		headerLabel.setText("Receptbatchkomponent");;
		headerLabel.addStyleName("HeaderLabel");
		final Button inspectButton = new Button("Se Receptkomponenter");
		final Button updateButton = new Button("Rediger Receptkomponent");
		final Button back = new Button("<- Tilbage");
		container.clear();
		container.add(headerLabel);
		container.add(inspectButton);
		container.add(updateButton);
		container.add(back);

		inspectButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				inspectKomp();
			}
		});
		updateButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//updateProduktKomp();
			}
		});

		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.adminCheck(prevMenu);
			}
		});
	}
	public void inspectKomp() {
		CellTable<ReceptKomponentDTO> table = new CellTable<ReceptKomponentDTO>();
		TextColumn<ReceptKomponentDTO> receptID = new TextColumn<ReceptKomponentDTO>(){

			@Override
			public String getValue(ReceptKomponentDTO object) {
				return Integer.toString(object.getRecept_Id());
			}

		};
		TextColumn<ReceptKomponentDTO> raavareID = new TextColumn<ReceptKomponentDTO>(){

			@Override
			public String getValue(ReceptKomponentDTO object) {
				return Integer.toString(object.getRaavare_id());
			}

		};
		TextColumn<ReceptKomponentDTO> nomNetto = new TextColumn<ReceptKomponentDTO>(){

			@Override
			public String getValue(ReceptKomponentDTO object) {
				return Double.toString(object.getNom_netto());
			}

		};
		TextColumn<ReceptKomponentDTO> tolerance = new TextColumn<ReceptKomponentDTO>(){

			@Override
			public String getValue(ReceptKomponentDTO object) {
				return Double.toString(object.getTolerance());
			}

		};

		table.addColumn(receptID, "Recept ID");
		table.addColumn(raavareID, "Råvare ID");
		table.addColumn(nomNetto, "Nom-netto");
		table.addColumn(tolerance, "Tolerance");

		ListDataProvider<ReceptKomponentDTO> dataProvider = new ListDataProvider<ReceptKomponentDTO>();

		dataProvider.addDataDisplay(table);

		List<ReceptKomponentDTO> list = new ArrayList<ReceptKomponentDTO>();
		list = dataProvider.getList();
		for (ReceptKomponentDTO mm : receptKomponent) {
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
				receptKomp();
			}
		});
	}
}
