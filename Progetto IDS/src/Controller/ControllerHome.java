package Controller;

import Model.DatabaseQuery;
import Model.ModelHome;
import View.ViewHome;

import java.util.*;

import javax.swing.JPanel;

public class ControllerHome implements Observer {
	private ModelHome model;
	private ViewHome view;
	
	private DatabaseQuery db = DatabaseQuery.getInstance();
	
	private ApplicationController controller = ApplicationController.getInstance();

	public ControllerHome() {
		this.model = new ModelHome();
		this.view = new ViewHome(model);
		view.addObserver(this);
		
		/*
		if(controller.isLogin()) {
			view.showSuggested();
		}
		*/
	}
	
	public JPanel getPanel() {
		return view.getPanel();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String) {
			if("catalogWith".equals(arg)) {
  				controller.showCatalog(view.getFindText(),null,Double.valueOf("0.00"),db.getMaxPrice());
			}
		}
	}

}
