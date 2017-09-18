package Controller;

import Model.DatabaseQuery;
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
		if("cancel".equals((String)arg)){
			controller.showAdminHome();
		} else if("apply".equals((String)arg)){
			DatabaseQuery db = DatabaseQuery.getInstance();
			db.editStoreQuantity(view.getSelectedDisc(), view.getNewQuantity());
			view.updateQuantity();
		} else if("disc change".equals((String)arg)){
			view.updateQuantity();
		} 
	}

}
