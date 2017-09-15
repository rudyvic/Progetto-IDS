package Controller;

import Model.ModelAdminEditDiscQuantity;
import View.ViewAdminEditDiscQuantity;

import java.util.*;

import javax.swing.JPanel;

public class ControllerAdminEditDiscQuantity implements Observer {
	private ModelAdminEditDiscQuantity model;
	private ViewAdminEditDiscQuantity view;
	private ApplicationController controller = ApplicationController.getInstance();

	public ControllerAdminEditDiscQuantity() {
		this(-1);
	}
	
	public ControllerAdminEditDiscQuantity(int discCode) {
		this.model = new ModelAdminEditDiscQuantity(discCode);
		this.view = new ViewAdminEditDiscQuantity(model);
		view.addObserver(this);
	}
	
	public JPanel getPanel() {
		return view.getPanel();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if("add disc".equals((String)arg)){
			controller.showAdminAddNewDisc();
		} else if("edit discs quantity".equals((String)arg)){
			controller.showAdminEditDiscQuantity();
		} 
	}

}
