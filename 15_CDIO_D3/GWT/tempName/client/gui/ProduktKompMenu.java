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

import tempName.shared.dto.ProduktBatchKomponentDTO;

public class ProduktKompMenu {
	private VerticalPanel container;
	private int prevMenu;
	private ArrayList<ProduktBatchKomponentDTO> produktBatchKomponent;
	private MainGUI mainGUI;
	private Label headerLabel = new Label();

	public ProduktKompMenu(VerticalPanel container, ArrayList<ProduktBatchKomponentDTO> produktBatchKomponent, int prevMenu, MainGUI mainGUI){
		this.container = container;
		this.prevMenu = prevMenu;
		this.produktBatchKomponent = produktBatchKomponent;
		this.mainGUI = mainGUI;
	}

	public void produktKomp(){
		headerLabel.setText("Produktbatchkomponent");;
		headerLabel.addStyleName("HeaderLabel");
		final Button inspectButton = new Button("Se Produktbatchkomponenter");
		final Button updateButton = new Button("Rediger Produktbatchkomponent");
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
				updateProduktKomp();
			}
		});

		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.adminCheck(prevMenu);
			}
		});
	}
	public void inspectKomp() {
		CellTable<ProduktBatchKomponentDTO> table = new CellTable<ProduktBatchKomponentDTO>();
		TextColumn<ProduktBatchKomponentDTO> pbID = new TextColumn<ProduktBatchKomponentDTO>(){

			@Override
			public String getValue(ProduktBatchKomponentDTO object) {
				return Integer.toString(object.getPbId());
			}

		};
		TextColumn<ProduktBatchKomponentDTO> rbID = new TextColumn<ProduktBatchKomponentDTO>(){

			@Override
			public String getValue(ProduktBatchKomponentDTO object) {
				return Integer.toString(object.getRbId());
			}

		};
		TextColumn<ProduktBatchKomponentDTO> tara = new TextColumn<ProduktBatchKomponentDTO>(){

			@Override
			public String getValue(ProduktBatchKomponentDTO object) {
				return Double.toString(object.getTara());
			}

		};
		TextColumn<ProduktBatchKomponentDTO> netto = new TextColumn<ProduktBatchKomponentDTO>(){

			@Override
			public String getValue(ProduktBatchKomponentDTO object) {
				return Double.toString(object.getNetto());
			}

		};
		TextColumn<ProduktBatchKomponentDTO> oprID = new TextColumn<ProduktBatchKomponentDTO>(){

			@Override
			public String getValue(ProduktBatchKomponentDTO object) {
				return Integer.toString(object.getOprId());
			}

		};

		table.addColumn(pbID, "Produktbatch ID");
		table.addColumn(rbID, "Råvarebatch ID");
		table.addColumn(tara, "Tara");
		table.addColumn(netto, "Netto");
		table.addColumn(oprID, "Operatør ID");

		ListDataProvider<ProduktBatchKomponentDTO> dataProvider = new ListDataProvider<ProduktBatchKomponentDTO>();

		dataProvider.addDataDisplay(table);

		List<ProduktBatchKomponentDTO> list = new ArrayList<ProduktBatchKomponentDTO>();
		list = dataProvider.getList();
		for (ProduktBatchKomponentDTO mm : produktBatchKomponent) {
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
				produktKomp();
			}
		});
	}
	public void updateProduktKomp(){
		headerLabel.setText("Opdater Produktbatchkomponent");
		final Label lbLabel = new Label("Vælg produktbatchkomponent (Produktbatch ID - Råvarebatch ID):");
		final ListBox lb = new ListBox();
		final Label pbLabel = new Label("Indtast produktbatch ID:");
		final TextBox pb = new TextBox();
		final Label rbLabel = new Label("Indtast råvarebatch ID:");
		final TextBox rb = new TextBox();
		final Label taraLabel = new Label("Indtast taravægt:");
		final TextBox tara = new TextBox();
		final Label nettoLabel = new Label("Indtast nettovægt:");
		final TextBox netto = new TextBox();
		final Label oprLabel = new Label("Indtast operatør ID:");
		final TextBox opr = new TextBox();
		final Button submit = new Button("Opdater");
		final Button delete = new Button("Slet");
		final Button back = new Button("<- Tilbage");
		for (int i = 0; i < produktBatchKomponent.size(); i++){
			lb.addItem(produktBatchKomponent.get(i).getPbId() + " - " + produktBatchKomponent.get(i).getRbId());
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
		container.add(taraLabel);
		container.add(tara);
		container.add(nettoLabel);
		container.add(netto);
		container.add(oprLabel);
		container.add(opr);
		container.add(submit);
		container.add(delete);
		container.add(back);


		pb.setText(Integer.toString(produktBatchKomponent.get(0).getPbId()));
		rb.setText(Integer.toString(produktBatchKomponent.get(0).getRbId()));
		tara.setText(Double.toString(produktBatchKomponent.get(0).getTara()));
		netto.setText(Double.toString(produktBatchKomponent.get(0).getNetto()));
		opr.setText(Integer.toString(produktBatchKomponent.get(0).getOprId()));

		lb.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				for (int i = 0; i < produktBatchKomponent.size(); i++){
					String pbID = Integer.toString(produktBatchKomponent.get(i).getPbId());
					String rbID = Integer.toString(produktBatchKomponent.get(i).getRbId());
					String selectedPB = lb.getSelectedItemText().substring(0, lb.getSelectedItemText().indexOf(" "));
					String selectedRB = lb.getSelectedItemText().substring(lb.getSelectedItemText().lastIndexOf(" ")+1);
					if (pbID.equals(selectedPB) && rbID.equals(selectedRB)){
						pb.setText(Integer.toString(produktBatchKomponent.get(i).getPbId()));
						rb.setText(Integer.toString(produktBatchKomponent.get(i).getRbId()));
						tara.setText(Double.toString(produktBatchKomponent.get(i).getTara()));
						netto.setText(Double.toString(produktBatchKomponent.get(i).getNetto()));
						opr.setText(Integer.toString(produktBatchKomponent.get(i).getOprId()));
					}
				}
			}
		});

		delete.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				for (int i = 0; i < produktBatchKomponent.size(); i++){
					String pbID = Integer.toString(produktBatchKomponent.get(i).getPbId());
					String rbID = Integer.toString(produktBatchKomponent.get(i).getRbId());
					String selectedPB = lb.getSelectedItemText().substring(0, lb.getSelectedItemText().indexOf(" "));
					String selectedRB = lb.getSelectedItemText().substring(lb.getSelectedItemText().lastIndexOf(" ")+1);
					if (pbID.equals(selectedPB) && rbID.equals(selectedRB)){
						mainGUI.serviceImpl.deleteProduktKomp(Integer.parseInt(pbID), Integer.parseInt(rbID));
					}
				}
				mainGUI.adminCheck(prevMenu);
			}
		});

		submit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				for (int i = 0; i < produktBatchKomponent.size(); i++){
					String pbID = Integer.toString(produktBatchKomponent.get(i).getPbId());
					String rbID = Integer.toString(produktBatchKomponent.get(i).getRbId());
					String selectedPB = lb.getSelectedItemText().substring(0, lb.getSelectedItemText().indexOf(" "));
					String selectedRB = lb.getSelectedItemText().substring(lb.getSelectedItemText().lastIndexOf(" ")+1);
					if (pbID.equals(selectedPB) && rbID.equals(selectedRB)){
						try{
							mainGUI.serviceImpl.updateProduktKomp(Integer.parseInt(pbID), Integer.parseInt(rbID), 
									Double.parseDouble(tara.getText()), Double.parseDouble(netto.getText()), Integer.parseInt(opr.getText()));
						}catch (NumberFormatException e){
							Window.alert("Venligst udfyld felterne med tal");
						}
					}
				}
				mainGUI.adminCheck(prevMenu);
			}
		});

		back.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				produktKomp();
			}
		});
	}
}
