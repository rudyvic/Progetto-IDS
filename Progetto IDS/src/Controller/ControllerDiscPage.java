package Controller;

import Model.Disc;
import Model.ModelDiscPage;
import View.ViewDiscPage;

import java.util.*;

import javax.swing.JPanel;

public class ControllerDiscPage implements Observer {
	private ModelDiscPage model;
	private ViewDiscPage view;
	private ApplicationController controller = ApplicationController.getInstance();

	public ControllerDiscPage(Disc disc) {
		this.model = new ModelDiscPage(disc);
		this.view = new ViewDiscPage(model);
		view.addObserver(this);
	}
	
	public JPanel getPanel() {
		return view.getPanel();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
	}

}
