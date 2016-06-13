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
				updateReceptKomp();
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
	public void updateReceptKomp(){
		headerLabel.setText("Opdater Receptkomponent");
		final Label lbLabel = new Label("Vælg Receptkomponent (Recept ID - Råvare ID):");
		final ListBox lb = new ListBox();
		final Label pbLabel = new Label("Indtast recept ID:");
		final TextBox pb = new TextBox();
		final Label rbLabel = new Label("Indtast råvare ID:");
		final TextBox rb = new TextBox();
		final Label nomNettoLabel = new Label("Indtast nom-nettovægt:");
		final TextBox nomNetto = new TextBox();
		final Label toleranceLabel = new Label("Indtast tolerance:");
		final TextBox tolerance = new TextBox();
		final Button submit = new Button("Opdater");
		final Button delete = new Button("Slet");
		final Button back = new Button("<- Tilbage");
		for (int i = 0; i < receptKomponent.size(); i++){
			lb.addItem(receptKomponent.get(i).getRecept_Id() + " - " + receptKomponent.get(i).getRaavare_id());
		}
		lb.setVisibleItemCount(1);
		container.clear();
		container.add(headerLabel);
		container.add(lbLabel);
		container.add(lb);
		container.add(pbLabel);
		container.add(pb);
		container.add(rbLabel);
		container.add(rb);
		container.add(nomNettoLabel);
		container.add(nomNetto);
		container.add(toleranceLabel);
		container.add(tolerance);
		container.add(submit);
		container.add(delete);
		container.add(back);

		pb.setText(Integer.toString(receptKomponent.get(0).getRecept_Id()));
		rb.setText(Integer.toString(receptKomponent.get(0).getRaavare_id()));
		nomNetto.setText(Double.toString(receptKomponent.get(0).getNom_netto()));
		tolerance.setText(Double.toString(receptKomponent.get(0).getTolerance()));

		lb.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				for (int i = 0; i < receptKomponent.size(); i++){
					String pbID = Integer.toString(receptKomponent.get(i).getRecept_Id());
					String rbID = Integer.toString(receptKomponent.get(i).getRaavare_id());
					String selectedPB = lb.getSelectedItemText().substring(0, lb.getSelectedItemText().indexOf(" "));
					String selectedRB = lb.getSelectedItemText().substring(lb.getSelectedItemText().lastIndexOf(" ")+1);
					if (pbID.equals(selectedPB) && rbID.equals(selectedRB)){
						pb.setText(Integer.toString(receptKomponent.get(i).getRecept_Id()));
						rb.setText(Integer.toString(receptKomponent.get(i).getRaavare_id()));
						nomNetto.setText(Double.toString(receptKomponent.get(i).getNom_netto()));
						tolerance.setText(Double.toString(receptKomponent.get(i).getTolerance()));
					}
				}
			}
		});

		delete.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				for (int i = 0; i < receptKomponent.size(); i++){
					String pbID = Integer.toString(receptKomponent.get(i).getRecept_Id());
					String rbID = Integer.toString(receptKomponent.get(i).getRaavare_id());
					String selectedPB = lb.getSelectedItemText().substring(0, lb.getSelectedItemText().indexOf(" "));
					String selectedRB = lb.getSelectedItemText().substring(lb.getSelectedItemText().lastIndexOf(" ")+1);
					if (pbID.equals(selectedPB) && rbID.equals(selectedRB)){
						mainGUI.serviceImpl.deleteReceptKomp(Integer.parseInt(pbID), Integer.parseInt(rbID));
					}
				}
				mainGUI.adminCheck(prevMenu);
			}
		});

		submit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (nomNetto.getText().length() > 5 || nomNetto.getText().substring(0, nomNetto.getText().indexOf(".")).length() > 2 ||
						nomNetto.getText().substring(nomNetto.getText().lastIndexOf(".")).length() > 2){
					Window.alert("Nom-netto må højst være 4 karakterer, eksclusiv punktum, hvor 2 skal være decimaltal");
				} else if(tolerance.getText().length() > 4 || tolerance.getText().substring(0, tolerance.getText().indexOf(".")).length() > 2 ||
						tolerance.getText().substring(tolerance.getText().lastIndexOf(".")).length() >= 2){
					Window.alert("Tolerancen må højst være 3 karakterer, eksclusiv punktum, hvor 1 skal være decimaltal");
				} else {
					for (int i = 0; i < receptKomponent.size(); i++){
						String pbID = Integer.toString(receptKomponent.get(i).getRecept_Id());
						String rbID = Integer.toString(receptKomponent.get(i).getRaavare_id());
						String selectedPB = lb.getSelectedItemText().substring(0, lb.getSelectedItemText().indexOf(" "));
						String selectedRB = lb.getSelectedItemText().substring(lb.getSelectedItemText().lastIndexOf(" ")+1);
						if (pbID.equals(selectedPB) && rbID.equals(selectedRB)){
							try{
								mainGUI.serviceImpl.updateReceptKomp(Integer.parseInt(pbID), Integer.parseInt(rbID), 
										Double.parseDouble(nomNetto.getText()), Double.parseDouble(tolerance.getText()));
							}catch (NumberFormatException e){
								Window.alert("Venligst udfyld felterne med tal");
							}
						}
					}
					mainGUI.adminCheck(prevMenu);
				}
			}
		});

		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				receptKomp();
			}
		});
	}
}
