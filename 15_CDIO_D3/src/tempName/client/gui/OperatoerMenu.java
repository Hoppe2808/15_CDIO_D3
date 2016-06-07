package tempName.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import tempName.client.service.ServiceClientImpl;

public class OperatoerMenu {
	private VerticalPanel container;
	private ServiceClientImpl serviceImpl;
	private MainGUI mainGUI;
	private int id;
	
	public OperatoerMenu(VerticalPanel container, ServiceClientImpl serviceImpl, MainGUI mainGUI, int id){
		this.container = container;
		this.serviceImpl = serviceImpl;
		this.mainGUI = mainGUI;
		this.id = id;
	}
	
	public void opMenu(){
		final Label meas = new Label("Indtast din måling");
		final DoubleBox measText = new DoubleBox();
		final Button submit = new Button("Send");
		final Button logout = new Button("Logout");
		container.clear();
		container.setSpacing(9);
		container.add(meas);
		container.add(measText);
		container.add(submit);
		container.add(logout);

		submit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				try{
					serviceImpl.addMeasurement(Double.parseDouble(measText.getText()), id); 
					Window.alert("Measurement has been successfully added");					
				}catch(Exception e){
					Window.alert("You have to imput a number as a measurement");
				}
			}
		});
		logout.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainGUI.loginScreen2();
			}
		});
	}
}
