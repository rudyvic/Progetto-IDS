package Controller;

import Model.ModelAdminHome;
import View.ViewAdminHome;

import java.util.*;

import javax.swing.JPanel;

public class ControllerAdminHome implements Observer {
	private ModelAdminHome model;
	private ViewAdminHome view;
	private ApplicationController controller = ApplicationController.getInstance();

	public ControllerAdminHome() {
		this.model = new ModelAdminHome();
		this.view = new ViewAdminHome(model);
		view.fillCatalogRunningOut();
		view.addObserver(this);
	}
	
	public JPanel getPanel() {
		return view.getPanel();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String) {
			if("add disc".equals((String)arg)){
				controller.showAdminAddNewDisc();
			} else if("edit discs quantity".equals((String)arg)){
				controller.showAdminEditDiscQuantity();
			}
		} else if(arg instanceof Integer) {
			controller.showAdminEditDiscQuantity((int)arg);
		}
	}

}
